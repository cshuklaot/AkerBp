/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Map;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestPropertyLifecycleInfo;
import com.emc.documentum.rest.client.sample.model.RestPropertyValueAssist;
import com.emc.documentum.rest.client.sample.model.RestTypeValueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPropertyLifecycleInfo implements RestPropertyLifecycleInfo {
    private String label;
    private boolean hidden;
    private boolean required;
    @JsonProperty("notnull")
    private boolean notNull;
    @JsonProperty("readonly")
    private boolean readOnly;
    private boolean searchable;
    private List<String> dependencies;
    @JsonProperty("value-assist")
    private List<JsonPropertyValueAssist> valueAssist;

    @JsonIgnore//todo
    private List<Map<String, Object>> vaDefinitions;
    
    @JsonProperty("ignore-parent-constraints")
    private boolean ignoreConstraints;
    @JsonProperty("ignore-immutable")
    private boolean ignoreImmutable;
    private List<JsonTypeValueConstraint> constraints;
    @JsonProperty("notnull-enforce")
    private String notNullEnforce;
    @JsonProperty("ignore-immutable")
    private String notNullMessage;

    public List<Map<String, Object>> getVaDefinitions() {
        return vaDefinitions;
    }

    public void setVaDefinitions(List<Map<String, Object>> vaDefinitions) {
        this.vaDefinitions = vaDefinitions;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public void setIgnoreConstraints(boolean ignoreConstraints) {
        this.ignoreConstraints = ignoreConstraints;
    }

    public void setIgnoreImmutable(boolean ignoreImmutable) {
        this.ignoreImmutable = ignoreImmutable;
    }

    public void setConstraints(List<JsonTypeValueConstraint> constraints) {
        this.constraints = constraints;
    }

    public void setNotNullEnforce(String notNullEnforce) {
        this.notNullEnforce = notNullEnforce;
    }

    public void setNotNullMessage(String notNullMessage) {
        this.notNullMessage = notNullMessage;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isNotNull() {
        return notNull;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public boolean isSearchable() {
        return searchable;
    }

    @Override
    public List<String> getDependencies() {
        return dependencies;
    }

    public List<Map<String, Object>> getVADefinitions() {
        return vaDefinitions;
    }
    
    @Override
    public boolean isIgnoreConstraints() {
        return ignoreConstraints;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<RestTypeValueConstraint> getConstraints() {
        return (List)constraints;
    }

    @Override
    public boolean isIgnoreImmutable() {
        return ignoreImmutable;
    }

    @Override
    public String getNotNullEnforce() {
        return notNullEnforce;
    }

    @Override
    public String getNotNullMessage() {
        return notNullMessage;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<RestPropertyValueAssist> getValueAssist() {
        return (List)valueAssist;
    }

    public void setValueAssist(List<JsonPropertyValueAssist> valueAssist) {
        this.valueAssist = valueAssist;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonPropertyLifecycleInfo that = (JsonPropertyLifecycleInfo)obj;
        return Equals.equal(hidden, that.hidden) 
            && Equals.equal(label, that.label)
            && Equals.equal(required, that.required)
            && Equals.equal(notNull, that.notNull)
            && Equals.equal(readOnly, that.readOnly)
            && Equals.equal(searchable, that.searchable)
            && Equals.equal(dependencies, that.dependencies)
            && Equals.equal(vaDefinitions, that.vaDefinitions)
            && Equals.equal(ignoreConstraints, that.ignoreConstraints)
            && Equals.equal(ignoreImmutable, that.ignoreImmutable)
            && Equals.equal(constraints, that.constraints)
            && Equals.equal(notNullEnforce, that.notNullEnforce)
            && Equals.equal(notNullMessage, that.notNullMessage)
            && Equals.equal(valueAssist, that.valueAssist);
    }
}