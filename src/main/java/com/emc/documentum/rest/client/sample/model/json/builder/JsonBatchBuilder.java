package com.emc.documentum.rest.client.sample.model.json.builder;

import com.emc.documentum.rest.client.batch.SettableAttachment;
import com.emc.documentum.rest.client.batch.SettableBatch;
import com.emc.documentum.rest.client.batch.SettableHeader;
import com.emc.documentum.rest.client.batch.SettableInclude;
import com.emc.documentum.rest.client.batch.SettableOperation;
import com.emc.documentum.rest.client.batch.SettableRequest;
import com.emc.documentum.rest.client.sample.model.json.JsonBatch;
import com.emc.documentum.rest.client.sample.model.json.JsonBatchAttachment;
import com.emc.documentum.rest.client.sample.model.json.JsonBatchHeader;
import com.emc.documentum.rest.client.sample.model.json.JsonBatchInclude;
import com.emc.documentum.rest.client.sample.model.json.JsonBatchOperation;
import com.emc.documentum.rest.client.sample.model.json.JsonBatchRequest;
import com.ot.d2rest.client.DCTMRestClient;

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
