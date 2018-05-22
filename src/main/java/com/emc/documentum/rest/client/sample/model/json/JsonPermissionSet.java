/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Permission;
import com.emc.documentum.rest.client.sample.model.PermissionSet;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPermissionSet extends JsonInlineLinkableBase implements PermissionSet {
    @JsonProperty
    private List<JsonPermission> permitted;
    @JsonProperty
    private List<JsonPermission> restricted;
    @JsonProperty
    private List<String> requiredGroup;
    @JsonProperty
    private List<String> requiredGroupSet;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Permission> getPermitted() {
        return (List)permitted;
    }

    public void setPermitted(List<JsonPermission> permitted) {
        this.permitted = permitted;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Permission> getRestricted() {
        return (List)restricted;
    }

    public void setRestricted(List<JsonPermission> restricted) {
        this.restricted = restricted;
    }

    @Override
    public List<String> getRequiredGroup() {
        return requiredGroup;
    }

    public void setRequiredGroup(List<String> requiredGroup) {
        this.requiredGroup = requiredGroup;
    }

    @Override
    public List<String> getRequiredGroupSet() {
        return requiredGroupSet;
    }

    public void setRequiredGroupSet(List<String> requiredGroupSet) {
        this.requiredGroupSet = requiredGroupSet;
    }

    @Override
    public boolean equals(Object obj) {
        JsonPermissionSet that = (JsonPermissionSet) obj;
        return Equals.equal(permitted, that.permitted)
                && Equals.equal(restricted, that.restricted)
                && Equals.equal(requiredGroup, that.requiredGroup)
                && Equals.equal(requiredGroupSet, that.requiredGroupSet)
                && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permitted, restricted, requiredGroup, requiredGroupSet);
    }
}