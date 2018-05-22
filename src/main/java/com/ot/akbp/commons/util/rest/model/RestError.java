/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

/**
 * represents the error response from REST services
 */
public interface RestError {
    /**
     * @return the http status code
     */
    public int getStatus();
    
    /**
     * @return the REST error code
     */
    public String getCode();
    
    /**
     * @return the error message
     */
    public String getMessage();
    
    /**
     * @return the detailed error message
     */
    public String getDetails();
    
    /**
     * @return the log id to match the server log
     */
    public String getId();
}
