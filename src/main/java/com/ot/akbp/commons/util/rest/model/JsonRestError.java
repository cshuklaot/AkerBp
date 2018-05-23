/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.Objects;

import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonRestError implements RestError {
	private int status;
	private String code;
	private String message;
	private String details;
	private String id;

	@Override
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		JsonRestError that = (JsonRestError) obj;
		return Equals.equal(id, that.id) && Equals.equal(status, that.status) && Equals.equal(code, that.code)
				&& Equals.equal(message, that.message) && Equals.equal(details, that.details);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status, code, message);
	}
}
