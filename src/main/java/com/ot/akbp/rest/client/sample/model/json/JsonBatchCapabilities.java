/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ot.akbp.commons.util.rest.model.JsonLinkableBase;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonBatchCapabilities extends JsonLinkableBase
		implements com.ot.akbp.rest.client.batch.Capabilities {
	private String transactions;
	private String sequence;
	@JsonProperty("on-error")
	private String onError;
	@JsonProperty("batchable-resources")
	private List<String> batchable;
	@JsonProperty("non-batchable-resources")
	private List<String> nonBatchable;

	@Override
	public String getTransactions() {
		return transactions;
	}

	public void setTransactions(String transactions) {
		this.transactions = transactions;
	}

	@Override
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	@Override
	public String getOnError() {
		return onError;
	}

	public void setOnError(String onError) {
		this.onError = onError;
	}

	@Override
	public List<String> getBatchable() {
		return batchable;
	}

	public void setBatchable(List<String> batchable) {
		this.batchable = batchable;
	}

	@Override
	public List<String> getNonBatchable() {
		return nonBatchable;
	}

	public void setNonBatchable(List<String> nonBatchable) {
		this.nonBatchable = nonBatchable;
	}

	@Override
	public boolean equals(Object obj) {
		JsonBatchCapabilities that = (JsonBatchCapabilities) obj;
		return Equals.equal(transactions, that.transactions) && Equals.equal(sequence, that.sequence)
				&& Equals.equal(onError, that.onError) && Equals.equal(batchable, that.batchable)
				&& Equals.equal(nonBatchable, that.nonBatchable);
	}

	@Override
	public int hashCode() {
		return Objects.hash(transactions, sequence, onError, batchable, nonBatchable);
	}
}
