/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.batch.SettableInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JsonBatchInclude implements SettableInclude {
    private String href;

    public JsonBatchInclude() {
    }
    
    public JsonBatchInclude(String href) {
        setHref(href);
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public void setHref(String href) {
        if(href.startsWith("cid:")) {
            this.href = href;
        } else {
            this.href = "cid:" + href;   
        }
    }

    @Override
    @JsonIgnore
    public String getRawHref() {
        return href == null ? null : href.substring("cid:".length());
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonBatchInclude that = (JsonBatchInclude)obj;
        return Equals.equal(href, that.href);
    }

    @Override
    public int hashCode() {
        return Objects.hash(href);
    }
}
