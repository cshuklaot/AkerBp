/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestPropertyValueAssist;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPropertyValueAssist implements RestPropertyValueAssist {
    private String type;
    private String condition;
    @JsonProperty("allow-caching")
    private boolean allowCaching;
    private List<String> values;
    @JsonProperty("allow-user-values")
    private boolean allowUserValues;
    @JsonProperty("query-attribute")
    private String queryAttribute;
    @JsonProperty("query-expression")
    private String queryExpression;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isAllowCaching() {
        return allowCaching;
    }

    public void setAllowCaching(boolean allowCaching) {
        this.allowCaching = allowCaching;
    }

    public boolean isAllowUserValues() {
        return allowUserValues;
    }

    public void setAllowUserValues(boolean allowUserValues) {
        this.allowUserValues = allowUserValues;
    }

    public String getQueryAttribute() {
        return queryAttribute;
    }

    public void setQueryAttribute(String queryAttribute) {
        this.queryAttribute = queryAttribute;
    }

    public String getQueryExpression() {
        return queryExpression;
    }

    public void setQueryExpression(String queryExpression) {
        this.queryExpression = queryExpression;
    }
    
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object obj) {
        JsonPropertyValueAssist that = (JsonPropertyValueAssist)obj;
        return Equals.equal(condition, that.condition)
            && Equals.equal(allowCaching, that.allowCaching)
            && Equals.equal(allowUserValues, that.allowUserValues)
            && Equals.equal(queryAttribute, that.queryAttribute)
            && Equals.equal(queryExpression, that.queryExpression)
            && Equals.equal(values, that.values);
    }
}