/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.FolderLink;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonFolderLink extends JsonInlineLinkableBase implements FolderLink {
    @JsonProperty
    private String href;
    @JsonProperty("parent-id")
    private String parentId;
    @JsonProperty("child-id")
    private String childId;
    
    public JsonFolderLink() {
    }
    
    public JsonFolderLink(FolderLink folderLink) {
        this.href = folderLink.getHref();
        this.parentId = folderLink.getParentId();
        this.childId = folderLink.getChildId();
    }

    @Override
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    @Override
    public boolean equals(Object obj) {
        JsonFolderLink that = (JsonFolderLink)obj;
        return Equals.equal(href, that.href) &&
               Equals.equal(parentId, that.parentId) &&
               Equals.equal(childId, that.childId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(href, parentId, childId);
    }
}
