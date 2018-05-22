/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.batch.Batch;
import com.emc.documentum.rest.client.batch.Request;
import com.emc.documentum.rest.client.batch.Response;
import com.emc.documentum.rest.client.batch.SettableOperation;
import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonBatchOperation implements SettableOperation {
	private String id;
	private String description;
	private com.emc.documentum.rest.client.batch.Batch.Status state;
	private String started;
	private String finished;
	@JsonProperty("request")
	private JsonBatchRequest batchRequest;
	@JsonProperty("response")
	private JsonBatchResponse batchResponse;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Batch.Status getState() {
		return state;
	}

	@Override
	public void setState(Batch.Status state) {
		this.state = state;
	}

	@Override
	public String getStarted() {
		return started;
	}

	@Override
	public void setStarted(String started) {
		this.started = started;
	}

	@Override
	public String getFinished() {
		return finished;
	}

	@Override
	public void setFinished(String finished) {
		this.finished = finished;
	}

	@Override
	@JsonIgnore
	public JsonBatchRequest getRequest() {
		return batchRequest;
	}

	@Override
	public void setRequest(Request request) {
		this.batchRequest = (JsonBatchRequest) request;
	}

	public void setBatchRequest(JsonBatchRequest request) {
		this.batchRequest = request;
	}

	public JsonBatchRequest getBatchRequest() {
		return batchRequest;
	}

	@Override
	@JsonIgnore
	public JsonBatchResponse getResponse() {
		return batchResponse;
	}

	@Override
	public void setResponse(Response response) {
		this.batchResponse = (JsonBatchResponse) response;
	}

	public JsonBatchResponse getBatchResponse() {
		return batchResponse;
	}

	public void setBatchResponse(JsonBatchResponse response) {
		this.batchResponse = response;
	}

	@Override
	public boolean equals(Object obj) {
		JsonBatchOperation that = (JsonBatchOperation) obj;
		return Equals.equal(id, that.id) && Equals.equal(description, that.description)
				&& Equals.equal(state, that.state) && Equals.equal(started, that.started)
				&& Equals.equal(finished, that.finished) && Equals.equal(batchRequest, that.batchRequest)
				&& Equals.equal(batchResponse, that.batchResponse);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, state, batchRequest, batchResponse);
	}
}
