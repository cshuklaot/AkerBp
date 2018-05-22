/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Map;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.ValueAssistantRequest;

public class JsonValueAssistantRequest implements ValueAssistantRequest {
    private Map<String, Object> properties;
    
    public JsonValueAssistantRequest() {
    }
    
    public JsonValueAssistantRequest(ValueAssistantRequest object) {
        this.properties = object.getProperties();
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object obj) {
        JsonValueAssistantRequest that = (JsonValueAssistantRequest)obj;
        return Equals.equal(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }
}
