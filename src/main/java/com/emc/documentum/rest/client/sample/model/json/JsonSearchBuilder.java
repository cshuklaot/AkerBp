/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emc.documentum.rest.client.sample.model.Search;
import com.emc.documentum.rest.client.sample.model.Search.Expression;
import com.emc.documentum.rest.client.sample.model.Search.FacetDefinition;
import com.emc.documentum.rest.client.sample.model.Search.Location;
import com.emc.documentum.rest.client.sample.model.Search.Sort;
import com.emc.documentum.rest.client.sample.model.builder.SearchBuilder;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonExpression;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonExpressionSet;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonFacetDefinition;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonFullTextExpression;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonIdLocation;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonLocation;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonPathLocation;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonPropertyExpression;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonPropertyListExpression;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonPropertyRangeExpression;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonRelativeDateExpression;
import com.emc.documentum.rest.client.sample.model.json.JsonSearch.JsonSort;

public class JsonSearchBuilder extends SearchBuilder {
    private JsonSearch search = new JsonSearch();

    @Override
    public Search build() {
        return search;
    }
    
    @Override
    public SearchBuilder reset() {
        search = new JsonSearch();
        return this;
    }
    
    @Override
    public SearchBuilder types(String... types) {
        search.setTypes(Arrays.asList(types));
        return this;
    }
    
    @Override
    public SearchBuilder columns(String... columns) {
        search.setColumns(new ArrayList<>(Arrays.asList(columns)));
        return this;
    }
    
    @Override
    public SearchBuilder sort(String property, Boolean ascending, String lang, Boolean ascii) {
        return sort(new JsonSort(property, ascending, lang, ascii));
    }
    
    @Override
    public SearchBuilder sort(Sort sort) {
        if (search.getSorts() == null) {
            search.setSorts(new ArrayList<JsonSort>());
        }
        search.getSorts().add(sort);
        return this;
    }

    @Override
    public SearchBuilder idLocation(String id, boolean descendent) {
        return location(new JsonIdLocation(id, descendent));
    }

    @Override
    public SearchBuilder pathLocation(String path, boolean descendent) {
        return location(new JsonPathLocation(path, descendent));
    }

    @Override
    public SearchBuilder location(Location location) {
        if (search.getLocations() == null) {
            search.setLocations(new ArrayList<JsonLocation>());
        }
        search.getLocations().add(location);
        return this;
    }
    
    @Override
    public SearchBuilder collections(String... collections) {
        search.setCollections(Arrays.asList(collections));
        return this;
    }

    @Override
    public SearchBuilder allVersions(boolean allVersions) {
        search.setAllVersions(allVersions);
        return this;
    }

    @Override
    public SearchBuilder includeHiddenObjects(boolean includeHiddenObjects) {
        search.setIncludeHiddenObjects(includeHiddenObjects);
        return this;
    }

    @Override
    public SearchBuilder maxResultsForFacets(int maxResultsForFacets) {
        search.setMaxResultsForFacets(maxResultsForFacets);
        return this;
    }

    @Override
    public SearchBuilder facetDefinition(String id, List<String> attributes, String groupby, String sort, int maxValues,
            String... properties) {
        JsonFacetDefinition definition = new JsonFacetDefinition(id, attributes, groupby, sort, maxValues);
        if(properties != null && properties.length > 0) {
            Map<String, String> map = new HashMap<>();
            for(int i=0;i<properties.length-1;i+=2) {
                map.put(properties[i], properties[i+1]);
            }
            definition.setProperties(map);
        }
        return facetDefinition(definition);
    }
    
    @Override
    public SearchBuilder facetDefinition(FacetDefinition facetDefinition) {
        if (search.getFacetDefinitions() == null) {
            search.setFacetDefinitions(new ArrayList<JsonFacetDefinition>());
        }
        search.getFacetDefinitions().add(facetDefinition);
        return this;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public SearchBuilder expressionSet(List<Expression> expressions) {
        search.setExpressionSet(new JsonExpressionSet((List)expressions));
        return this;
    }

    @Override
    public SearchBuilder propertyExpression(String name, String value, String operator,
            boolean exactMatch, boolean repeated, boolean caseSensitive, boolean fuzzy) {
        return expression(new JsonPropertyExpression(name, value, operator, exactMatch, repeated, caseSensitive, fuzzy));
    }

    @Override
    public SearchBuilder fullTextExpression(String value, boolean fuzzy) {
        return expression(new JsonFullTextExpression(value, fuzzy));
    }

    @Override
    public SearchBuilder relativeDateExpression(String name, int value, String timeUnit, String operator) {
        return expression(new JsonRelativeDateExpression(name, value, timeUnit, operator));
    }

    @Override
    public SearchBuilder propertyListExpression(String name, List<String> values, String operator, boolean repeated) {
        return expression(new JsonPropertyListExpression(name, values, operator, repeated));
    }

    @Override
    public SearchBuilder propertyRangeExpression(String name, String from, String to) {
        return expression(new JsonPropertyRangeExpression(name, from, to));
    }

    @Override
    public SearchBuilder expression(Expression expression) {
        if(search.getExpressionSet() == null) {
            JsonExpressionSet expressionSet = new JsonExpressionSet();
            expressionSet.setExpressions(new ArrayList<JsonExpression>());
            search.setExpressionSet(expressionSet);
        }
        search.getExpressionSet().getExpressions().add(expression);
        return this;
    }
    
    @Override
    public SearchBuilder asTemplate() {
        if(search.getExpressionSet() != null && search.getExpressionSet().getExpressions().size() > 0) {
            ((JsonExpression)search.getExpressionSet().getExpressions().get(search.getExpressionSet().getExpressions().size() - 1)).setTemplate(true);
        }
        return this;
    }
}
