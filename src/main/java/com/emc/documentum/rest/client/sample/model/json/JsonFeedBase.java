/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ot.akbp.commons.util.rest.model.Entry;
import com.ot.akbp.commons.util.rest.model.FeedBase;
import com.ot.akbp.commons.util.rest.model.InlineLinkable;
import com.ot.akbp.commons.util.rest.model.JsonLinkableBase;

public abstract class JsonFeedBase<T extends InlineLinkable, E extends Entry<T>> extends JsonLinkableBase
		implements FeedBase<T, E> {
	private String id;
	private String title;
	private String updated;
	private String summary;
	private Integer page;
	@JsonProperty("items-per-page")
	private Integer itemsPerPage;
	private Integer total;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	@Override
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		JsonFeedBase that = (JsonFeedBase) obj;
		return Equals.equal(id, that.id) && Equals.equal(title, that.title) && Equals.equal(updated, that.updated)
				&& Equals.equal(summary, that.summary) && Equals.equal(page, that.page)
				&& Equals.equal(itemsPerPage, that.itemsPerPage) && Equals.equal(total, that.total)S;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, updated, summary, page, total);
	}
}
