/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;

public interface Operation {
    public String getId();
    public String getDescription();
    public Batch.Status getState();
    public String getStarted();
    public String getFinished();
    public Request getRequest();
    public Response getResponse();
}
