/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestTypeDisplayConfig.RestPropertyAttributeHint;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTypeAttributeHint implements RestPropertyAttributeHint {
    private String attribute;
    @JsonProperty("display-hint")
    private int displayHint;

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setDisplayHint(int displayHint) {
        this.displayHint = displayHint;
    }

    @Override
    public String getAttribute() {
        return attribute;
    }

    @Override
    public int getDisplayHint() {
        return displayHint;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonTypeAttributeHint that = (JsonTypeAttributeHint)obj;
        return Equals.equal(attribute, that.attribute) 
            && Equals.equal(displayHint, that.displayHint);
    }
}