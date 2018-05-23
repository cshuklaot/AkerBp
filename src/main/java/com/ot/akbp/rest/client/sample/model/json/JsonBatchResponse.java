/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.ot.akbp.rest.client.batch.Header;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonBatchResponse implements com.ot.akbp.rest.client.batch.Response {
	private int status;
	private List<JsonBatchHeader> headers;
	private String entity;

	@Override
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Header> getHeaders() {
		return (List) headers;
	}

	public void setHeaders(List<JsonBatchHeader> headers) {
		this.headers = headers;
	}

	@Override
	public String getHeader(String header) {
		if (headers != null) {
			for (Header h : headers) {
				if (h.getName().equalsIgnoreCase(header)) {
					return h.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Override
	public boolean equals(Object obj) {
		JsonBatchResponse that = (JsonBatchResponse) obj;
		return Equals.equal(status, that.status) && Equals.equal(headers, that.headers)
				&& Equals.equal(entity, that.entity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(entity, status, headers);
	}
}
