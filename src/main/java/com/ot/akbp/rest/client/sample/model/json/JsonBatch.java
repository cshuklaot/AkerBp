/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ot.akbp.rest.client.batch.Operation;
import com.ot.akbp.rest.client.batch.SettableBatch;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonBatch implements SettableBatch {
	private String id;
	private String description;
	private String started;
	private String finished;
	private String submitted;
	private String owner;
	private Status state;
	private Status substate;
	private Boolean transactional;
	private Boolean sequential;
	@JsonProperty("return-request")
	private Boolean returnRequest;
	@JsonProperty("on-error")
	public OnError onError;
	public List<JsonBatchOperation> operations = new ArrayList<>();

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
	public String getSubmitted() {
		return submitted;
	}

	@Override
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	@Override
	public String getOwner() {
		return owner;
	}

	@Override
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public Status getState() {
		return state;
	}

	@Override
	public void setState(Status state) {
		this.state = state;
	}

	@Override
	public Status getSubstate() {
		return substate;
	}

	@Override
	public void setSubstate(Status substate) {
		this.substate = substate;
	}

	@Override
	public Boolean getTransactional() {
		return transactional;
	}

	@Override
	public void setTransactional(Boolean transactional) {
		this.transactional = transactional;
	}

	@Override
	public Boolean getSequential() {
		return sequential;
	}

	@Override
	public void setSequential(Boolean sequential) {
		this.sequential = sequential;
	}

	@Override
	public Boolean getReturnRequest() {
		return returnRequest;
	}

	@Override
	public void setReturnRequest(Boolean returnRequest) {
		this.returnRequest = returnRequest;
	}

	@Override
	public OnError getOnError() {
		return onError;
	}

	@Override
	public void setOnError(OnError onError) {
		this.onError = onError;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Operation> getOperations() {
		return (List) operations;
	}

	public void setOperations(List<JsonBatchOperation> operations) {
		this.operations = operations;
	}

	@Override
	public void addOperation(Operation op) {
		operations.add((JsonBatchOperation) op);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasAttachment() {
		for (Operation o : operations) {
			if (o.getRequest().getAttachment() != null) {
				return true;
			}
			if (o.getRequest().getAttachments() != null && !o.getRequest().getAttachments().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		JsonBatch that = (JsonBatch) obj;
		return Equals.equal(id, that.id) && Equals.equal(description, that.description)
				&& Equals.equal(started, that.started) && Equals.equal(finished, that.finished)
				&& Equals.equal(submitted, that.submitted) && Equals.equal(owner, that.owner)
				&& Equals.equal(state, that.state) && Equals.equal(substate, that.substate)
				&& Equals.equal(transactional, that.transactional) && Equals.equal(sequential, that.sequential)
				&& Equals.equal(returnRequest, that.returnRequest) && Equals.equal(operations, that.operations)
				&& Equals.equal(onError, that.onError);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, state, substate, transactional, sequential, onError, operations);
	}
}
