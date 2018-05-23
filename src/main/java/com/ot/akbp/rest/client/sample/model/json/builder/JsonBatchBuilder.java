package com.ot.akbp.rest.client.sample.model.json.builder;

import com.ot.akbp.rest.client.batch.SettableAttachment;
import com.ot.akbp.rest.client.batch.SettableBatch;
import com.ot.akbp.rest.client.batch.SettableHeader;
import com.ot.akbp.rest.client.batch.SettableInclude;
import com.ot.akbp.rest.client.batch.SettableOperation;
import com.ot.akbp.rest.client.batch.SettableRequest;
import com.ot.akbp.rest.client.sample.model.json.JsonBatch;
import com.ot.akbp.rest.client.sample.model.json.JsonBatchAttachment;
import com.ot.akbp.rest.client.sample.model.json.JsonBatchHeader;
import com.ot.akbp.rest.client.sample.model.json.JsonBatchInclude;
import com.ot.akbp.rest.client.sample.model.json.JsonBatchOperation;
import com.ot.akbp.rest.client.sample.model.json.JsonBatchRequest;
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
