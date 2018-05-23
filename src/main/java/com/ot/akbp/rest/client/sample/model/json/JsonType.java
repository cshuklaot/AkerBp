/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ot.akbp.commons.util.rest.model.JsonInlineLinkableBase;
import com.ot.akbp.commons.util.rest.model.RestTypeMappingTable;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonType extends JsonInlineLinkableBase implements com.ot.akbp.commons.util.rest.model.RestType {
	private String name;
	private String label;
	private String parent;
	@JsonProperty("shared-parent")
	private String sharedParent;
	private String category;
	@JsonProperty("help-text")
	private String helpText;
	@JsonProperty("comment-text")
	private String commentText;
	private List<JsonProperty> properties = new ArrayList<>();
	@JsonProperty("default-lifecycle")
	private String defaultLifecycle;
	@JsonProperty("default-lifecycle-version")
	private String defaultLifecycleVersion;
	@JsonProperty("auditable-system-events")
	private List<String> auditableSystemEvents;
	@JsonProperty("auditable-app-events")
	private List<String> auditableAppEvents;
	@JsonProperty("mapping-tables")
	private List<JsonTypeMappingTable> mappingTables;
	@JsonProperty("ignore-parent-constraints")
	private boolean ignoreConstraints;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Override
	public String getSharedParent() {
		return sharedParent;
	}

	public void setSharedParent(String sharedParent) {
		this.sharedParent = sharedParent;
	}

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	@Override
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RestProperty> getProperties() {
		return (List) properties;
	}

	public void setProperties(List<JsonProperty> properties) {
		this.properties = properties;
	}

	@Override
	public String getDefaultLifecycle() {
		return defaultLifecycle;
	}

	public void setDefaultLifecycle(String defaultLifecycle) {
		this.defaultLifecycle = defaultLifecycle;
	}

	@Override
	public String getDefaultLifecycleVersion() {
		return defaultLifecycleVersion;
	}

	public void setDefaultLifecycleVersion(String defaultLifecycleVersion) {
		this.defaultLifecycleVersion = defaultLifecycleVersion;
	}

	@Override
	public List<String> getAuditableSystemEvents() {
		return auditableSystemEvents;
	}

	public void setAuditableSystemEvents(List<String> auditableSystemEvents) {
		this.auditableSystemEvents = auditableSystemEvents;
	}

	@Override
	public List<String> getAuditableAppEvents() {
		return auditableAppEvents;
	}

	public void setAuditableAppEvents(List<String> auditableAppEvents) {
		this.auditableAppEvents = auditableAppEvents;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RestTypeMappingTable> getMappingTables() {
		return (List) mappingTables;
	}

	public void setMappingTables(List<JsonTypeMappingTable> mappingTables) {
		this.mappingTables = mappingTables;
	}

	public boolean isIgnoreConstraints() {
		return ignoreConstraints;
	}

	public void setIgnoreConstraints(boolean ignoreConstraints) {
		this.ignoreConstraints = ignoreConstraints;
	}

	@Override
	public boolean equals(Object obj) {
		JsonType that = (JsonType) obj;
		return Equals.equal(name, that.name) && Equals.equal(label, that.label) && Equals.equal(parent, that.parent)
				&& Equals.equal(sharedParent, that.sharedParent) && Equals.equal(category, that.category)
				&& Equals.equal(properties, that.properties) && Equals.equal(helpText, that.helpText)
				&& Equals.equal(commentText, that.commentText) && Equals.equal(defaultLifecycle, that.defaultLifecycle)
				&& Equals.equal(defaultLifecycleVersion, that.defaultLifecycleVersion)
				&& Equals.equal(auditableSystemEvents, that.auditableSystemEvents)
				&& Equals.equal(auditableAppEvents, that.auditableAppEvents)
				&& Equals.equal(mappingTables, that.mappingTables)
				&& Equals.equal(ignoreConstraints, that.ignoreConstraints) && super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
