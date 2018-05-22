/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;

import java.util.List;

import com.ot.akbp.commons.util.rest.model.Linkable;

public interface Capabilities extends Linkable {
	public String getTransactions();

	public String getSequence();

	public String getOnError();

	public List<String> getBatchable();

	public List<String> getNonBatchable();
}
