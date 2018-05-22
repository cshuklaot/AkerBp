/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestTypeDisplayConfig;
import com.emc.documentum.rest.client.sample.model.RestTypeScopeConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTypeScopeConfig implements RestTypeScopeConfig {
    private String id;
    private List<String> scopes;
    private List<String> categories;
    @JsonProperty("display-configs")
    private List<JsonTypeDisplayConfig> displayConfigs;

    public void setId(String id) {
        this.id = id;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setDisplayConfigs(List<JsonTypeDisplayConfig> displayConfigs) {
        this.displayConfigs = displayConfigs;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public List<String> getScopes() {
        return this.scopes;
    }

    @Override
    public List<String> getCategories() {
        return this.categories;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<RestTypeDisplayConfig> getDisplayConfigs() {
        return (List)this.displayConfigs;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonTypeScopeConfig that = (JsonTypeScopeConfig)obj;
        return Equals.equal(id, that.id) 
            && Equals.equal(scopes, that.scopes)
            && Equals.equal(categories, that.categories)
            && Equals.equal(displayConfigs, that.displayConfigs);
    }
}