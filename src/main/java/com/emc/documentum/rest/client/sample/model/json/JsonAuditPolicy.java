/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Map;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.AuditPolicy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAuditPolicy extends JsonInlineLinkableBase implements AuditPolicy {
    @JsonProperty("object-id")
    private String objectId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is-group")
    private boolean isGroup;
    @JsonProperty("accessor-name")
    private String accessorName;
    @JsonProperty(value = "attribute-rules")
    private Map<String, String> attributeRules;
    
    public JsonAuditPolicy(AuditPolicy auditPolicy) {
        objectId = auditPolicy.getObjectId();
        name = auditPolicy.getName();
        accessorName = auditPolicy.getAccessorName();
        isGroup = auditPolicy.isGroup();
        attributeRules = auditPolicy.getAttributeRules();
    }
    
    public JsonAuditPolicy() {
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAccessorName() {
        return accessorName;
    }

    public void setAccessorName(String accessorName) {
        this.accessorName = accessorName;
    }

    @Override
    @JsonIgnore
    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    @Override
    public Map<String, String> getAttributeRules() {
        return attributeRules;
    }

    public void setAttributeRules(Map<String, String> attributeRules) {
        this.attributeRules = attributeRules;
    }

    @Override
    public boolean equals(Object obj) {
        JsonAuditPolicy that = (JsonAuditPolicy) obj;
        return Equals.equal(objectId, that.objectId)
                && Equals.equal(name, that.name)
                && Equals.equal(accessorName, that.accessorName)
                && Equals.equal(isGroup, that.isGroup)
                && Equals.equal(attributeRules, that.attributeRules)
                && super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(objectId, name, accessorName);
    }
}
