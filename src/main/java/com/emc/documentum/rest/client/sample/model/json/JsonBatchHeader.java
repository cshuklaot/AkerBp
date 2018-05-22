/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;

public class JsonBatchHeader implements com.emc.documentum.rest.client.batch.SettableHeader {
	private String name;
	private String value;

	public JsonBatchHeader() {
	}

	public JsonBatchHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		JsonBatchHeader that = (JsonBatchHeader) obj;
		return Equals.equal(name, that.name) && Equals.equal(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value);
	}

	@Override
	public String toString() {
		return name + "=" + value;
	}
}
