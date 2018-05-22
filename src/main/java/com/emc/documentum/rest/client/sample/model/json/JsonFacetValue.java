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

public class JsonFacetValue extends JsonInlineLinkableBase implements FacetValue {
    @JsonProperty("facet-id")
    private String facetId;
    @JsonProperty("facet-value-id")
    private String id;
    @JsonProperty("facet-value-count")
    private int count;
    @JsonProperty("facet-value-constraint")
    private String constraint;
    @JsonProperty
    private List<JsonFacet> facets;

    @Override
    public String getFacetId() {
        return facetId;
    }

    public void setFacetId(String facetId) {
        this.facetId = facetId;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Facet> getFacets() {
        return (List)facets;
    }

    public void setFacets(List<JsonFacet> facets) {
        this.facets = facets;
    }

    @Override
    public boolean equals(Object obj) {
        JsonFacetValue that = (JsonFacetValue) obj;
        return Equals.equal(facetId, that.facetId)
                && Equals.equal(id, that.id)
                && Equals.equal(count, that.count)
                && Equals.equal(constraint, that.constraint);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(facetId, id, count, constraint);
    }
}
