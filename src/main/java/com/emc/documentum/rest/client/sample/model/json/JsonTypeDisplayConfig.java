/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestTypeDisplayConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTypeDisplayConfig implements RestTypeDisplayConfig {
    private String id;
    private String name;
    @JsonProperty("attribute-source")
    private String attributeSource;
    @JsonProperty("fixed-display")
    private boolean isFixedDisplay;
    @JsonProperty("attribute-hints")
    private List<JsonTypeAttributeHint> attributeHints;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributeSource(String attributeSource) {
        this.attributeSource = attributeSource;
    }

    public void setFixedDisplay(boolean isFixedDisplay) {
        this.isFixedDisplay = isFixedDisplay;
    }

    public void setAttributeHints(List<JsonTypeAttributeHint> attributeHints) {
        this.attributeHints = attributeHints;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAttributeSource() {
        return this.attributeSource;
    }

    @Override
    public boolean isFixedDisplay() {
        return this.isFixedDisplay;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<RestPropertyAttributeHint> getAttributeHints() {
        return (List)attributeHints;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonTypeDisplayConfig that = (JsonTypeDisplayConfig)obj;
        return Equals.equal(name, that.name) 
            && Equals.equal(id, that.id)
            && Equals.equal(attributeSource, that.attributeSource)
            && Equals.equal(isFixedDisplay, that.isFixedDisplay)
            && Equals.equal(attributeHints, that.attributeHints);
    }
}