/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import org.springframework.util.StringUtils;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.InlineLinkable;
import com.emc.documentum.rest.client.sample.model.Linkable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JsonInlineLinkableBase extends JsonLinkableBase implements InlineLinkable {
    private String src;
    @JsonProperty("type")
    private String contentType;

    @JsonProperty("content-type")
    private String legacyContentType;
    
    public String getSrc() {
        return src;
    }
    
    public void setSrc(String src) {
        this.src = src;
    }
    
    public String getContentType() {
        return StringUtils.isEmpty(contentType) ? legacyContentType : contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
        this.legacyContentType = contentType;
    }
    
    @Override
    @JsonIgnore
    public Linkable getContent() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        JsonInlineLinkableBase that = (JsonInlineLinkableBase)obj;
        return super.equals(obj)
            && Equals.equal(src, that.src) 
            && Equals.equal(contentType, that.contentType)
            && Equals.equal(legacyContentType, that.legacyContentType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(src, contentType, legacyContentType);
    }
}
