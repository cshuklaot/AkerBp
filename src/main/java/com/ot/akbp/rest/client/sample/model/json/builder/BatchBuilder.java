/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json.builder;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.ot.akbp.commons.util.rest.annotation.NotBatchable;
import com.ot.akbp.rest.client.batch.Attachment;
import com.ot.akbp.rest.client.batch.Batch;
import com.ot.akbp.rest.client.batch.SettableAttachment;
import com.ot.akbp.rest.client.batch.SettableBatch;
import com.ot.akbp.rest.client.batch.SettableHeader;
import com.ot.akbp.rest.client.batch.SettableInclude;
import com.ot.akbp.rest.client.batch.SettableOperation;
import com.ot.akbp.rest.client.batch.SettableRequest;
import com.ot.akbp.rest.client.batch.Batch.OnError;
import com.ot.akbp.rest.client.sample.client.impl.AbstractRestTemplateClient;
import com.ot.akbp.rest.client.sample.client.impl.AbstractRestTemplateClient.RequestProcessor;
import com.ot.akbp.rest.client.sample.client.util.SupportedMediaTypes;
import com.ot.d2rest.client.DCTMRestClient;

public abstract class BatchBuilder {
	protected final AbstractRestTemplateClient client;
	protected final DCTMRestClient dctmClient;
	private final SettableBatch batch;
	private int operationId = 1;

	public static BatchBuilder builder(DCTMRestClient client) {
		if (!(client instanceof AbstractRestTemplateClient)) {
			throw new UnsupportedOperationException(client.getClass().getName());
		}
		client.getRepository();

		return new JsonBatchBuilder(client);

	}

	protected BatchBuilder(DCTMRestClient client) {
		batch = createBatch();
		this.client = (AbstractRestTemplateClient) client;
		dctmClient = new ClinetHandler().client();
	}

	public DCTMRestClient operation() {
		return dctmClient;
	}

	public BatchBuilder description(String description) {
		batch.setDescription(description);
		return this;
	}

	public BatchBuilder transactional(boolean transactional) {
		batch.setTransactional(transactional);
		return this;
	}

	public BatchBuilder sequential(boolean sequential) {
		batch.setSequential(sequential);
		return this;
	}

	public BatchBuilder onError(OnError onError) {
		batch.setOnError(onError);
		return this;
	}

	public BatchBuilder returnRequest(boolean returnRequest) {
		batch.setReturnRequest(returnRequest);
		return this;
	}

	public Batch build() {
		return batch;
	}

	protected String nextOperationId() {
		return "op" + operationId++;
	}

	protected abstract SettableBatch createBatch();

	protected abstract SettableOperation createOperation();

	protected abstract SettableRequest createRequest();

	protected abstract SettableHeader createHeader();

	protected abstract SettableInclude createInclude();

	protected abstract SettableAttachment createAttachment();

	private class BatchRequestProcessor implements RequestProcessor {
		@SuppressWarnings("unchecked")
		@Override
		public <T> ResponseEntity<T> process(String url, HttpMethod method, HttpEntity<?> requestEntity,
				Class<T> responseType) {
			SettableRequest request = createRequest();
			request.setMethod(method.name());
			request.setUri(url);
			for (Entry<String, List<String>> e : requestEntity.getHeaders().entrySet()) {
				if (!e.getKey().equalsIgnoreCase(HttpHeaders.AUTHORIZATION)) {
					for (String s : e.getValue()) {
						SettableHeader header = createHeader();
						header.setName(e.getKey());
						header.setValue(s);
						request.addHeader(header);
					}
				}
			}
			if (requestEntity.getBody() != null) {
				if (requestEntity.getBody() instanceof Map) {
					if (((Map<?, ?>) requestEntity.getBody()).containsKey("metadata")) {
						HttpEntity<?> metadataEntity = ((List<HttpEntity<?>>) ((Map<?, ?>) requestEntity.getBody())
								.get("metadata")).get(0);
						request.setEntity(toEntity(metadataEntity.getBody()));
						SettableHeader header = createHeader();
						header.setName(HttpHeaders.CONTENT_TYPE);
						header.setValue(client.isXml() ? SupportedMediaTypes.APPLICATION_VND_DCTM_XML_VALUE
								: SupportedMediaTypes.APPLICATION_VND_DCTM_JSON_VALUE);
						request.setHeader(header);
					}
					if (((Map<?, ?>) requestEntity.getBody()).containsKey("binary")) {
						List<HttpEntity<?>> binaries = ((List<HttpEntity<?>>) ((Map<?, ?>) requestEntity.getBody())
								.get("binary"));
						if (client.getMajorVersion() >= 7.3) {
							for (HttpEntity<?> binaryEntity : binaries) {
								request.addAttachment(toAttachment(binaryEntity));
							}
						} else {
							if (binaries.size() > 1) {
								throw new IllegalArgumentException(
										"The batch for the rest services " + client.getMajorVersion()
												+ " only supports one attachment for each operation");
							}
							request.setAttachment(toAttachment(binaries.get(0)));
						}
					}
				} else {
					request.setEntity(toEntity(requestEntity.getBody()));
				}
			}
			SettableOperation operation = createOperation();
			operation.setDescription(method.name() + " " + url);
			operation.setId(nextOperationId());
			operation.setRequest(request);
			batch.addOperation(operation);
			return null;
		}

		private Attachment toAttachment(HttpEntity<?> binaryEntity) {
			SettableInclude include = createInclude();
			include.setHref(UUID.randomUUID().toString());
			SettableAttachment attachment = createAttachment();
			attachment.setInclude(include);
			attachment.setContentStream((InputStream) binaryEntity.getBody());
			if (binaryEntity.getHeaders().getContentType() != null) {
				attachment.setContentType(binaryEntity.getHeaders().getContentType().toString());
			}
			return attachment;
		}

		private String toEntity(Object o) {
			if (o == null) {
				return null;
			}
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream(50 * 1024);
				client.serialize(o, os);
				return os.toString("UTF-8");
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	private class ClinetHandler implements InvocationHandler {
		private final RequestProcessor processor = new BatchRequestProcessor();

		DCTMRestClient client() {
			return (DCTMRestClient) Proxy.newProxyInstance(DCTMRestClient.class.getClassLoader(),
					new Class[] { DCTMRestClient.class }, this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.isAnnotationPresent(NotBatchable.class)) {
				throw new UnsupportedOperationException(method.getName());
			}
			return method.invoke(client.setRequestProcessor(processor), args);
		}
	}
}
