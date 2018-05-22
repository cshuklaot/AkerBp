/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public interface FutureModel {
    public boolean isModelReady();
    public Object getModel();
    public Class<?> getModelClass();
    public Exception getException();
    public HttpHeaders getHeaders();
    public HttpStatus getStatus();
}
