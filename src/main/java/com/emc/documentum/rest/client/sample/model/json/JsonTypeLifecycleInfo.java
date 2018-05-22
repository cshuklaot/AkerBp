/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestTypeLifecycleInfo;
import com.emc.documentum.rest.client.sample.model.RestTypeValueConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTypeLifecycleInfo implements RestTypeLifecycleInfo {
    private String label;
    @JsonProperty("default-lifecycle")
    private String defaultLifecycle;
    @JsonProperty("default-lifecycle-version")
    private String defaultLifecycleVersion;
    @JsonProperty("ignore-parent-constraints")
    private boolean ignoreConstraints;
    private List<JsonTypeValueConstraint> constraints = null;
    
    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
    public boolean getIgnoreConstraints() {
        return ignoreConstraints;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<RestTypeValueConstraint> getConstraints() {
        return (List)constraints;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonTypeLifecycleInfo that = (JsonTypeLifecycleInfo)obj;
        return Equals.equal(defaultLifecycle, that.defaultLifecycle) 
            && Equals.equal(defaultLifecycleVersion, that.defaultLifecycleVersion)
            && Equals.equal(label, that.label)
            && Equals.equal(ignoreConstraints, that.ignoreConstraints)
            && Equals.equal(constraints, that.constraints);
    }
}