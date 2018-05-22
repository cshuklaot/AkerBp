/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestProperty;
import com.emc.documentum.rest.client.sample.model.RestType;
import com.emc.documentum.rest.client.sample.model.RestTypeLifecycleInfo;
import com.emc.documentum.rest.client.sample.model.RestTypeMappingTable;
import com.emc.documentum.rest.client.sample.model.RestTypeScopeConfig;
import com.emc.documentum.rest.client.sample.model.RestTypeValueConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonType extends JsonInlineLinkableBase implements RestType {
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
    private List<com.emc.documentum.rest.client.sample.model.json.JsonProperty> properties = new ArrayList<>();
    @JsonProperty("default-lifecycle")
    private String defaultLifecycle;
    @JsonProperty("default-lifecycle-version")
    private String defaultLifecycleVersion;
    @JsonProperty("lifecycles")
    private Map<String, Map<String, JsonTypeLifecycleInfo>> typeLifecycles;
    @JsonProperty("auditable-system-events")
    private List<String> auditableSystemEvents;
    @JsonProperty("auditable-app-events")
    private List<String> auditableAppEvents;
    @JsonProperty("mapping-tables")
    private List<JsonTypeMappingTable> mappingTables;
    @JsonProperty("scope-configs")
    private List<JsonTypeScopeConfig> scopeConfigs;
    private List<JsonTypeValueConstraint> constraints;
    @JsonProperty("ignore-parent-constraints")
    private boolean ignoreConstraints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getSharedParent() {
        return sharedParent;
    }

    public void setSharedParent(String sharedParent) {
        this.sharedParent = sharedParent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<RestProperty> getProperties() {
        return (List)properties;
    }

    public void setProperties(List<com.emc.documentum.rest.client.sample.model.json.JsonProperty> properties) {
        this.properties = properties;
    }

    public String getDefaultLifecycle() {
        return defaultLifecycle;
    }

    public void setDefaultLifecycle(String defaultLifecycle) {
        this.defaultLifecycle = defaultLifecycle;
    }

    public String getDefaultLifecycleVersion() {
        return defaultLifecycleVersion;
    }

    public void setDefaultLifecycleVersion(String defaultLifecycleVersion) {
        this.defaultLifecycleVersion = defaultLifecycleVersion;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<String, Map<String, RestTypeLifecycleInfo>> getTypeLifecycles() {
        return (Map)typeLifecycles;
    }

    public void setTypeLifecycles(Map<String, Map<String, JsonTypeLifecycleInfo>> typeLifecycles) {
        this.typeLifecycles = typeLifecycles;
    }

    public List<String> getAuditableSystemEvents() {
        return auditableSystemEvents;
    }

    public void setAuditableSystemEvents(List<String> auditableSystemEvents) {
        this.auditableSystemEvents = auditableSystemEvents;
    }

    public List<String> getAuditableAppEvents() {
        return auditableAppEvents;
    }

    public void setAuditableAppEvents(List<String> auditableAppEvents) {
        this.auditableAppEvents = auditableAppEvents;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<RestTypeMappingTable> getMappingTables() {
        return (List)mappingTables;
    }

    public void setMappingTables(List<JsonTypeMappingTable> mappingTables) {
        this.mappingTables = mappingTables;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<RestTypeScopeConfig> getScopeConfigs() {
        return (List)scopeConfigs;
    }

    public void setScopeConfigs(List<JsonTypeScopeConfig> scopeConfigs) {
        this.scopeConfigs = scopeConfigs;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<RestTypeValueConstraint> getConstraints() {
        return (List)constraints;
    }

    public void setConstraints(List<JsonTypeValueConstraint> constraints) {
        this.constraints = constraints;
    }

    public boolean isIgnoreConstraints() {
        return ignoreConstraints;
    }

    public void setIgnoreConstraints(boolean ignoreConstraints) {
        this.ignoreConstraints = ignoreConstraints;
    }

    @Override
    public boolean equals(Object obj) {
        JsonType that = (JsonType)obj;
        return Equals.equal(name, that.name) 
            && Equals.equal(label, that.label)
            && Equals.equal(parent, that.parent)
            && Equals.equal(sharedParent, that.sharedParent)
            && Equals.equal(category, that.category)
            && Equals.equal(properties, that.properties)
            && Equals.equal(helpText, that.helpText)
            && Equals.equal(commentText, that.commentText)
            && Equals.equal(defaultLifecycle, that.defaultLifecycle)
            && Equals.equal(defaultLifecycleVersion, that.defaultLifecycleVersion)
            && Equals.equal(typeLifecycles, that.typeLifecycles)
            && Equals.equal(auditableSystemEvents, that.auditableSystemEvents)
            && Equals.equal(auditableAppEvents, that.auditableAppEvents)
            && Equals.equal(mappingTables, that.mappingTables)
            && Equals.equal(scopeConfigs, that.scopeConfigs)
            && Equals.equal(constraints, that.constraints)
            && Equals.equal(ignoreConstraints, that.ignoreConstraints)
            && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
