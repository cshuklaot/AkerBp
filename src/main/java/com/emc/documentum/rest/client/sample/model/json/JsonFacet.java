/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Facet;
import com.emc.documentum.rest.client.sample.model.FacetValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonFacet extends JsonInlineLinkableBase implements Facet {
    @JsonProperty("facet-id")
    private String id;
    @JsonProperty("facet-label")
    private String label;
    @JsonProperty("facet-value")
    private List<JsonFacetValue> values;
    
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<FacetValue> getValues() {
        return (List)values;
    }

    public void setValues(List<JsonFacetValue> values) {
        this.values = values;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonFacet that = (JsonFacet) obj;
        return Equals.equal(id, that.id) && Equals.equal(label, that.label)
                && Equals.equal(values, that.values);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, label, values);
    }
}
