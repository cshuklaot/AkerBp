/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.InlineLinkable;
import com.emc.documentum.rest.client.sample.model.SearchEntry;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSearchEntry<T extends InlineLinkable> extends JsonEntry<T> implements SearchEntry<T> {
    @JsonProperty
    private String score;
    @JsonProperty
    private List<String> terms;
    
    @Override
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    @Override
    public boolean equals(Object obj) {
        JsonSearchEntry<?> that = (JsonSearchEntry<?>)obj;
        return super.equals(that) 
            && Equals.equal(terms, that.terms)
            && Equals.equal(score, that.score);
    }
}