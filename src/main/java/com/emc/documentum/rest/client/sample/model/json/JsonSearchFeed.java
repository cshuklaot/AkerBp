/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.emc.documentum.rest.client.sample.model.Facet;
import com.emc.documentum.rest.client.sample.model.InlineLinkable;
import com.emc.documentum.rest.client.sample.model.SearchEntry;
import com.emc.documentum.rest.client.sample.model.SearchFeed;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSearchFeed<T extends InlineLinkable> extends JsonFeedBase<T, SearchEntry<T>> implements SearchFeed<T> {
    @JsonProperty
    private List<JsonFacet> facets;
    
    @JsonProperty
    private List<JsonSearchEntry<T>> entries;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<SearchEntry<T>> getEntries() {
        return (List)entries;
    }

    public void setEntries(List<JsonSearchEntry<T>> entries) {
        this.entries = entries;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Facet> getFacets() {
        return (List)facets;
    }

    public void setFacets(List<JsonFacet> facets) {
        this.facets = facets;
    }
}
