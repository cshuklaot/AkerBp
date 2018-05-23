/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.client.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.ot.akbp.commons.util.rest.annotation.ClientAsyncOption;
import com.ot.akbp.commons.util.rest.model.FutureModel;
import com.ot.d2rest.client.DCTMRestClient;

/**
 * The async DCTMRestClient based on sync DCTMRestClient. Not all the operations
 * are async supported.
 * 
 * The result of this client, may be {@link FutureModel}, if so, until the
 * {@link FutureModel#isModelReady()} returns true, all other operations will be
 * blocked.
 * 
 * Please note, the async is on the client side only. The server still executes
 * the operation syncly.
 */
public class ClientAsyncRestTemplateClient implements InvocationHandler {
	private final BlockingQueue<DCTMRestClient> clientQueue;
	private final AtomicInteger clientCount;
	private final int maxClientCount;
	private final Executor executor;
	private volatile com.ot.akbp.commons.util.rest.model.FutureModel latestResponse;
	private volatile DCTMRestClient latestClient;

	public ClientAsyncRestTemplateClient(int maxClient, DCTMRestClient client) {
		maxClientCount = maxClient;
		executor = Executors.newCachedThreadPool();
		clientQueue = new ArrayBlockingQueue<>(maxClient);
		clientCount = new AtomicInteger(1);
		clientQueue.add(client);
		client.getRepository();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ("getHeaders".equals(method.getName())) {
			return latestResponse == null ? null : latestResponse.getHeaders();
		}
		if ("getStatus".equals(method.getName())) {
			return latestResponse == null ? null : latestResponse.getStatus();
		}
		ClientAsyncOption asyncOption = AnnotationUtils.getAnnotation(method, ClientAsyncOption.class);
		boolean sync = asyncOption != null && (asyncOption.value() || asyncOption.retainClient());

		DCTMRestClient client = null;
		if (latestClient == null) {
			client = clientQueue.take();
			if (clientQueue.isEmpty()) {
				if (clientCount.incrementAndGet() <= maxClientCount) {
					clientQueue.put(((AbstractRestTemplateClient) client).clone());
				} else {
					clientCount.decrementAndGet();
				}
			}
		} else {
			client = latestClient;
			latestClient = null;
		}
		if (!sync && (method.getReturnType().isInterface() || method.getReturnType().equals(Void.TYPE))) {
			Response response = new Response();
			executor.execute(new Execution(client, method, args, response));
			Set<Class<?>> interfaces = new HashSet<>();
			interfaces.add(FutureModel.class);
			if (!method.getReturnType().equals(Void.TYPE)) {
				interfaces.add(method.getReturnType());
				Type returnType = method.getGenericReturnType();
				if (returnType instanceof TypeVariable) {
					TypeVariable<?> returnTypeV = (TypeVariable<?>) returnType;
					Type[] genericParameterTypes = method.getGenericParameterTypes();
					for (int i = 0; i < genericParameterTypes.length; ++i) {
						if (genericParameterTypes[i] instanceof TypeVariable) {
							TypeVariable<?> paraTypeV = (TypeVariable<?>) genericParameterTypes[i];
							if (paraTypeV.getName().equals(returnTypeV.getName())) {
								fillResponseType(interfaces, args[i]);
								break;
							}
						}
					}
				}
			}
			latestResponse = (FutureModel) Proxy.newProxyInstance(DCTMRestClient.class.getClassLoader(),
					interfaces.toArray(new Class[0]), response);
			return latestResponse;
		} else {
			latestResponse = null;
			try {
				return method.invoke(client, args);
			} finally {
				if (asyncOption != null && asyncOption.retainClient()) {
					latestClient = client;
				} else {
					clientQueue.add(client);
				}
			}
		}
	}

	private void fillResponseType(Set<Class<?>> interfaces, Object obj) {
		Class<?> clazz = obj instanceof FutureModel ? ((FutureModel) obj).getModelClass() : obj.getClass();
		fillResponseType(interfaces, clazz);
	}

	private void fillResponseType(Set<Class<?>> interfaces, Class<?> clazz) {
		for (Class<?> c : clazz.getInterfaces()) {
			interfaces.add(c);
		}
		Class<?> sc = clazz.getSuperclass();
		if (sc != null && !sc.isInterface()) {
			fillResponseType(interfaces, sc);
		}
	}

	private class Execution implements Runnable {
		private final Response response;
		private final DCTMRestClient client;
		private final Method method;
		private final Object[] args;

		Execution(DCTMRestClient client, Method method, Object[] args, Response response) {
			this.response = response;
			this.client = client;
			this.method = method;
			this.args = args;
		}

		@Override
		public void run() {
			try {
				Object result = method.invoke(client, args);
				response.setModel(result == null ? Void.TYPE : result);
			} catch (Exception e) {
				response.setException(e);
			} finally {
				response.setHeaders(client.getHeaders());
				response.setStatus(client.getStatus());
				clientQueue.add(client);
			}
		}
	}

	private static class Response implements InvocationHandler {
		private volatile Object model;
		private volatile Exception exception;
		private volatile HttpHeaders headers;
		private volatile HttpStatus status;

		void setModel(Object model) {
			this.model = model;
			synchronized (this) {
				notify();
			}
		}

		void setException(Exception exception) {
			this.exception = exception;
			synchronized (this) {
				notify();
			}
		}

		void setHeaders(HttpHeaders headers) {
			this.headers = headers;
		}

		void setStatus(HttpStatus status) {
			this.status = status;
		}

		private void waitResponse() {
			try {
				while (!isReady()) {
					synchronized (this) {
						wait(1000);
					}
				}
			} catch (InterruptedException e) {
			}
		}

		private boolean isReady() {
			return model != null || exception != null;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if ("isModelReady".equals(method.getName())) {
				return isReady();
			}
			waitResponse();
			switch (method.getName()) {
			case "getException":
				return exception;
			case "getStatus":
				return status;
			case "getHeaders":
				return headers;
			}
			if (exception != null) {
				throw exception;
			}
			switch (method.getName()) {
			case "getModelClass":
				return model == null ? null : model.getClass();
			case "getModel":
				return model;
			}
			return method.invoke(model, args);
		}
	}
}
