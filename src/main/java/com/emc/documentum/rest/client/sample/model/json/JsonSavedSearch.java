/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import com.emc.documentum.rest.client.sample.model.SavedSearch;
import com.emc.documentum.rest.client.sample.model.Search;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.emc.documentum.rest.client.sample.client.impl.jackson.DCTMJacksonMapper.marshal;
import static com.emc.documentum.rest.client.sample.client.impl.jackson.DCTMJacksonMapper.unmarshal;

public class JsonSavedSearch extends JsonObject implements SavedSearch {
    @JsonProperty("query-document")
    private String queryDocument;
    
    public JsonSavedSearch() {
    }

    public JsonSavedSearch(SavedSearch savedSearch) {
        super(savedSearch);
        setSearch(savedSearch.getSearch());
    }

    @Override
    public String getQueryDocument() {
        return queryDocument;
    }
    
    public void setQueryDocument(String queryDocument) {
        this.queryDocument = queryDocument;
    }
    
    @Override
    @JsonIgnore
    public Search getSearch() {
        return queryDocument==null?null:unmarshal(queryDocument, JsonSearch.class);
    }

    public void setSearch(Search search) {
        try {
            queryDocument = search == null?null:marshal(search);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
