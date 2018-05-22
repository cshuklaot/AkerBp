/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ot.akbp.commons.util.rest.model.Entry;
import com.ot.akbp.commons.util.rest.model.Feed;
import com.ot.akbp.commons.util.rest.model.InlineLinkable;

public class JsonFeed<T extends InlineLinkable> extends JsonFeedBase<T, Entry<T>> implements Feed<T> {
	@JsonProperty
	private List<JsonEntry<T>> entries;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Entry<T>> getEntries() {
		return (List) entries;
	}

	public void setEntries(List<JsonEntry<T>> entries) {
		this.entries = entries;
	}
}
