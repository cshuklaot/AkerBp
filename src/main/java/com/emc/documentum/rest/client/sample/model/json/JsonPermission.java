/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Permission;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPermission extends JsonInlineLinkableBase implements Permission {
    @JsonProperty
    private String accessor;

    @JsonProperty("basic-permission")
    private String basicPermission;

    @JsonProperty("extend-permissions")
    private String extendPermissions;

    @JsonProperty("application-permission")
    private String applicationPermission;

    @Override
    public String getAccessor() {
        return accessor;
    }

    public void setAccessor(String accessor) {
        this.accessor = accessor;
    }

    @Override
    public String getBasicPermission() {
        return basicPermission;
    }

    public void setBasicPermission(String basicPermission) {
        this.basicPermission = basicPermission;
    }

    @Override
    public String getExtendPermissions() {
        return extendPermissions;
    }

    public void setExtendPermissions(String extendPermissions) {
        this.extendPermissions = extendPermissions;
    }

    @Override
    public String getApplicationPermission() {
        return applicationPermission;
    }

    public void setApplicationPermission(String applicationPermission) {
        this.applicationPermission = applicationPermission;
    }

    @Override
    public boolean equals(Object obj) {
        JsonPermission that = (JsonPermission) obj;
        return Equals.equal(accessor, that.accessor)
                && Equals.equal(applicationPermission, that.applicationPermission)
                && Equals.equal(basicPermission, that.basicPermission)
                && Equals.equal(extendPermissions, that.extendPermissions)
                && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessor, applicationPermission, basicPermission, extendPermissions);
    }
}