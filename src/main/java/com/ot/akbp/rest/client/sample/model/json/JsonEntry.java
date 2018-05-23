/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ot.akbp.commons.util.rest.model.Entry;
import com.ot.akbp.commons.util.rest.model.InlineLinkable;
import com.ot.akbp.commons.util.rest.model.JsonLinkableBase;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonEntry<T extends InlineLinkable> extends JsonLinkableBase implements Entry<T> {
	@JsonProperty
	private String id;
	@JsonProperty
	private String title;
	@JsonProperty
	private String updated;
	@JsonProperty
	private String summary;
	@JsonProperty
	private T content;
	@JsonProperty
	private String published;

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

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String getContentSrc() {
		return content == null ? null : content.getSrc();
	}

	@Override
	public String getContentType() {
		return content == null ? null : content.getContentType();
	}

	@Override
	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	@Override
	public T getContentObject() {
		return content;
	}

	@Override
	public boolean equals(Object obj) {
		JsonEntry<?> that = (JsonEntry<?>) obj;
		return Equals.equal(id, that.id) && Equals.equal(title, that.title) && Equals.equal(updated, that.updated)
				&& Equals.equal(summary, that.summary) && Equals.equal(content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, updated, summary, content);
	}
}