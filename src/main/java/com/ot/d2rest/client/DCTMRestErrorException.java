/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.d2rest.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.ot.akbp.commons.util.rest.model.RestError;

/**
 * the exception class, which wraps the error response from REST server
 */
public class DCTMRestErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final com.ot.akbp.commons.util.rest.model.RestError error;
	private final HttpHeaders headers;
	private final HttpStatus status;

	public DCTMRestErrorException(HttpHeaders headers, HttpStatus status, RestError error) {
		this.headers = headers;
		this.status = status;
		this.error = error;
	}

	public RestError getError() {
		return error;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
