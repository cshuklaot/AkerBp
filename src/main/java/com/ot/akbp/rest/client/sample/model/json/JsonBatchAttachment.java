/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import java.io.InputStream;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ot.akbp.rest.client.batch.Include;
import com.ot.akbp.rest.client.batch.SettableAttachment;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonBatchAttachment implements SettableAttachment {
	@JsonProperty("Include")
	private JsonBatchInclude include;
	@JsonIgnore
	private String contentType;
	@JsonIgnore
	private InputStream contentStream;

	public JsonBatchAttachment() {
	}

	public JsonBatchAttachment(JsonBatchInclude include) {
		this.include = include;
	}

	@Override
	public JsonBatchInclude getInclude() {
		return include;
	}

	@Override
	public void setInclude(Include include) {
		this.include = (JsonBatchInclude) include;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public InputStream getContentStream() {
		return contentStream;
	}

	@Override
	public void setContentStream(InputStream contentStream) {
		this.contentStream = contentStream;
	}

	@Override
	public boolean equals(Object obj) {
		JsonBatchAttachment that = (JsonBatchAttachment) obj;
		return Equals.equal(include, that.include);
	}

	@Override
	public int hashCode() {
		return Objects.hash(include);
	}
}
