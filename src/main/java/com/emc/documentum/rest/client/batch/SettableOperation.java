/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;

import com.emc.documentum.rest.client.batch.Batch.Status;

public interface SettableOperation extends Operation {
    public void setId(String id);
    public void setDescription(String description);
    public void setState(Status state);
    public void setStarted(String started);
    public void setFinished(String finished);
    public void setRequest(Request request);
    public void setResponse(Response response);
}
