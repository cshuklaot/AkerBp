/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;


public interface SettableBatch extends Batch {
    public void setId(String id);
    public void setDescription(String description);
    public void setStarted(String started);
    public void setFinished(String finished);
    public void setTransactional(Boolean transactional);
    public void setSequential(Boolean sequential);
    public void setOnError(OnError onError);
    public void setReturnRequest(Boolean returnRequest);
    public void setState(Status state);
    public void setSubstate(Status substate);
    public void setSubmitted(String submitted);
    public void setOwner(String owner);
    public void addOperation(Operation operation);
}
