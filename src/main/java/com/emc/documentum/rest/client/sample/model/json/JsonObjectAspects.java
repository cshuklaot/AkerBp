/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.ObjectAspects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonObjectAspects extends JsonLinkableBase implements ObjectAspects {
    @JsonProperty
    private List<String> aspects;
    
    public JsonObjectAspects() {
    }
    
    public JsonObjectAspects(String... aspects) {
        this.aspects = Arrays.asList(aspects);
    }
    
    public JsonObjectAspects(ObjectAspects aspects) {
        this.aspects = aspects.getAspects();
    }

    @Override
    public List<String> getAspects() {
        return aspects;
    }

    public void setAspects(List<String> aspects) {
        this.aspects = aspects;
    }

    @Override
    public boolean equals(Object obj) {
        JsonObjectAspects that = (JsonObjectAspects)obj;
        return Equals.equal(aspects, that.aspects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aspects);
    }
}
