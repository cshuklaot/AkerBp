/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;

import java.util.List;

public interface Batch {
    public String getId();
    public String getDescription();
    public String getStarted();
    public String getFinished();
    public Boolean getTransactional();
    public Boolean getSequential();
    public OnError getOnError();
    public Boolean getReturnRequest();
    public Status getState();
    public Status getSubstate();
    public String getSubmitted();
    public String getOwner();
    public List<Operation> getOperations();
    public boolean hasAttachment();
    
    public enum OnError {
        FAIL, CONTINUE;
    }
    
    public enum Status {
        INITIAL,
        RUNNING,
        HALTED,
        PARTIALLY_HALTED,
        FINISHED,
        FAILED,
        FINISHED_WITH_ERROR
    }
}
