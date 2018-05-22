package com.emc.documentum.rest.client.sample.model.json;

import com.emc.documentum.rest.client.sample.client.DCTMRestClient;
import com.emc.documentum.rest.client.sample.model.batch.SettableAttachment;
import com.emc.documentum.rest.client.sample.model.batch.SettableBatch;
import com.emc.documentum.rest.client.sample.model.batch.SettableHeader;
import com.emc.documentum.rest.client.sample.model.batch.SettableInclude;
import com.emc.documentum.rest.client.sample.model.batch.SettableOperation;
import com.emc.documentum.rest.client.sample.model.batch.SettableRequest;
import com.emc.documentum.rest.client.sample.model.builder.BatchBuilder;

public class JsonBatchBuilder extends BatchBuilder {
    public JsonBatchBuilder(DCTMRestClient client) {
        super(client);
    }

    @Override
    protected SettableBatch createBatch() {
        return new JsonBatch();
    }

    @Override
    protected SettableOperation createOperation() {
        return new JsonBatchOperation();
    }

    @Override
    protected SettableRequest createRequest() {
        return new JsonBatchRequest();
    }

    @Override
    protected SettableHeader createHeader() {
        return new JsonBatchHeader();
    }

    @Override
    protected SettableInclude createInclude() {
        return new JsonBatchInclude();
    }

    @Override
    protected SettableAttachment createAttachment() {
        return new JsonBatchAttachment();
    }
}
