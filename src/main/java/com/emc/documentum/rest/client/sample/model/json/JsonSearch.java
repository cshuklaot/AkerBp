/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Search;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class JsonSearch extends JsonInlineLinkableBase implements Search {
    private List<String> repositories;
    private List<String> types;
    private List<String> columns;
    private List<JsonSort> sorts;
    private List<JsonLocation> locations;
    @JsonProperty("expression-set")
    private JsonExpressionSet expressionSet;
    private List<String> collections;
    @JsonProperty("all-versions")
    private Boolean allVersions;
    @JsonProperty("include-hidden-objects")
    private Boolean includeHiddenObjects;
    @JsonProperty("max-results-for-facets")
    private Integer maxResultsForFacets;
    @JsonProperty("facet-definitions")
    private List<JsonFacetDefinition> facetDefinitions;
    
    public List<String> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<String> repositories) {
        this.repositories = repositories;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Sort> getSorts() {
        return (List)sorts;
    }

    public void setSorts(List<JsonSort> sorts) {
        this.sorts = sorts;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Location> getLocations() {
        return (List)locations;
    }

    public void setLocations(List<JsonLocation> locations) {
        this.locations = locations;
    }

    public ExpressionSet getExpressionSet() {
        return expressionSet;
    }

    public void setExpressionSet(JsonExpressionSet expressionSet) {
        this.expressionSet = expressionSet;
    }

    public List<String> getCollections() {
        return collections;
    }

    public void setCollections(List<String> collections) {
        this.collections = collections;
    }

    public Boolean getAllVersions() {
        return allVersions;
    }

    public void setAllVersions(Boolean allVersions) {
        this.allVersions = allVersions;
    }

    public Boolean getIncludeHiddenObjects() {
        return includeHiddenObjects;
    }

    public void setIncludeHiddenObjects(Boolean includeHiddenObjects) {
        this.includeHiddenObjects = includeHiddenObjects;
    }

    public Integer getMaxResultsForFacets() {
        return maxResultsForFacets;
    }

    public void setMaxResultsForFacets(Integer maxResultsForFacets) {
        this.maxResultsForFacets = maxResultsForFacets;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<FacetDefinition> getFacetDefinitions() {
        return (List)facetDefinitions;
    }

    public void setFacetDefinitions(List<JsonFacetDefinition> facetDefinitions) {
        this.facetDefinitions = facetDefinitions;
    }

    @Override
    public boolean equals(Object obj) {
        JsonSearch that = (JsonSearch) obj;
        return Equals.equal(types, that.types)
                && Equals.equal(columns, that.columns)
                && Equals.equal(sorts, that.sorts)
                && Equals.equal(locations, that.locations)
                && Equals.equal(expressionSet, that.expressionSet)
                && Equals.equal(collections, that.collections)
                && Equals.equal(allVersions, that.allVersions)
                && Equals.equal(includeHiddenObjects, that.includeHiddenObjects)
                && Equals.equal(maxResultsForFacets, that.maxResultsForFacets)
                && Equals.equal(facetDefinitions, that.facetDefinitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(types, expressionSet, locations, sorts, columns, facetDefinitions);
    }

    public static class JsonSort implements Sort {
        private String property;
        private Boolean ascending;
        private String lang;
        private Boolean ascii;
        public JsonSort() {
        }
        public JsonSort(String property) {
            this.property = property;
        }
        public JsonSort(String property, Boolean ascending, String lang, Boolean ascii) {
            this.property = property;
            this.ascending = ascending;
            this.lang = lang;
            this.ascii = ascii;
        }
        public String getProperty() {
            return property;
        }
        public void setProperty(String property) {
            this.property = property;
        }
        public Boolean getAscending() {
            return ascending;
        }
        public void setAscending(Boolean ascending) {
            this.ascending = ascending;
        }
        public String getLang() {
            return lang;
        }
        public void setLang(String lang) {
            this.lang = lang;
        }
        public Boolean getAscii() {
            return ascii;
        }
        public void setAscii(Boolean ascii) {
            this.ascii = ascii;
        }
        @Override
        public boolean equals(Object obj) {
            JsonSort that = (JsonSort) obj;
            return Equals.equal(property, that.property)
                    && Equals.equal(ascending, that.ascending)
                    && Equals.equal(lang, that.lang)
                    && Equals.equal(ascii, that.ascii);
        }
        @Override
        public int hashCode() {
            return Objects.hash(property, ascending, lang, ascii);
        }
    }
    
    @JsonSubTypes({
        @JsonSubTypes.Type(value = JsonIdLocation.class, name = "id-location"),
        @JsonSubTypes.Type(value = JsonPathLocation.class, name = "path-location")
    })
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "location-type")
    public static abstract class JsonLocation {
        private String repository;
        private Boolean descendent;
        public JsonLocation() {
        }
        public JsonLocation(boolean descendent) {
            this.descendent = descendent;
        }
        public String getRepository() {
            return repository;
        }
        public void setRepository(String repository) {
            this.repository = repository;
        }
        public Boolean getDescendent() {
            return descendent;
        }
        public void setDescendent(Boolean descendent) {
            this.descendent = descendent;
        }
        @Override
        public boolean equals(Object obj) {
            JsonLocation that = (JsonLocation) obj;
            return descendent == that.descendent;
        }
    }
    
    @JsonPropertyOrder({ "locationType" })
    public static class JsonIdLocation extends JsonLocation implements IdLocation {
        private String id;
        @JsonProperty("location-type")
        private final String locationType = "id-location";
        public JsonIdLocation() {
        }
        public JsonIdLocation(String id, boolean descendent) {
            super(descendent);
            this.id = id;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getLocationType() {
            return locationType;
        }
        @Override
        public boolean equals(Object obj) {
            JsonIdLocation that = (JsonIdLocation) obj;
            return super.equals(obj)
                    && Equals.equal(id, that.id);
        }
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
    
    @JsonPropertyOrder({ "locationType" })
    public static class JsonPathLocation extends JsonLocation implements PathLocation {
        private String path;
        @JsonProperty("location-type")
        private final String locationType = "path-location";
        public JsonPathLocation() {
        }
        public JsonPathLocation(String path, boolean descendent) {
            super(descendent);
            this.path = path;
        }
        public String getPath() {
            return path;
        }
        public void setPath(String path) {
            this.path = path;
        }
        public String getLocationType() {
            return locationType;
        }
        @Override
        public boolean equals(Object obj) {
            JsonPathLocation that = (JsonPathLocation) obj;
            return super.equals(that)
                    && Equals.equal(path, that.path);
        }
        @Override
        public int hashCode() {
            return Objects.hash(path);
        }
    }
    
    public static class JsonFacetDefinition implements FacetDefinition {
        private String id;
        private List<String> attributes;
        @JsonProperty("group-by")
        private String groupby;
        private String sort;
        private Map<String, String> properties;
        @JsonProperty("max-values")
        private Integer maxValues;
        @JsonProperty("facet-definition")
        private JsonFacetDefinition nestedFacetDefinition;
        public JsonFacetDefinition() {
        }
        public JsonFacetDefinition(String id, List<String> attributes, String groupby, String sort, int maxValues) {
            this.id = id;
            this.attributes = attributes;
            this.groupby = groupby;
            this.sort = sort;
            this.maxValues = maxValues;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public List<String> getAttributes() {
            return attributes;
        }
        public void setAttributes(List<String> attributes) {
            this.attributes = attributes;
        }
        public String getGroupby() {
            return groupby;
        }
        public void setGroupby(String groupby) {
            this.groupby = groupby;
        }
        public String getSort() {
            return sort;
        }
        public void setSort(String sort) {
            this.sort = sort;
        }
        public Map<String, String> getProperties() {
            return properties;
        }
        public void setProperties(Map<String, String> properties) {
            this.properties = properties;
        }
        public Integer getMaxValues() {
            return maxValues;
        }
        public void setMaxValues(Integer maxValues) {
            this.maxValues = maxValues;
        }
        public FacetDefinition getNestedFacetDefinition() {
            return nestedFacetDefinition;
        }
        public void setNestedFacetDefinition(JsonFacetDefinition nestedFacetDefinition) {
            this.nestedFacetDefinition = nestedFacetDefinition;
        }
        @Override
        public boolean equals(Object obj) {
            JsonFacetDefinition that = (JsonFacetDefinition) obj;
            return Equals.equal(id, that.id)
                    && Equals.equal(attributes, that.attributes)
                    && Equals.equal(groupby, that.groupby)
                    && Equals.equal(sort, that.sort)
                    && Equals.equal(properties, that.properties)
                    && Equals.equal(maxValues, that.maxValues)
                    && Equals.equal(nestedFacetDefinition, that.nestedFacetDefinition);
        }
        @Override
        public int hashCode() {
            return Objects.hash(id, attributes, sort, properties);
        }
    }
    
    @JsonSubTypes({
        @JsonSubTypes.Type(value = JsonExpressionSet.class, name = "expression-set"),
        @JsonSubTypes.Type(value = JsonFullTextExpression.class, name = "fulltext"),
        @JsonSubTypes.Type(value = JsonPropertyExpression.class, name = "property"),
        @JsonSubTypes.Type(value = JsonPropertyListExpression.class, name = "property-list"),
        @JsonSubTypes.Type(value = JsonPropertyRangeExpression.class, name = "property-range"),
        @JsonSubTypes.Type(value = JsonRelativeDateExpression.class, name = "relative-date")
    })
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "expression-type")
    public static abstract class JsonExpression implements Expression {
        @JsonProperty("expression-type")
        private final String expressionType;
        @JsonProperty
        private Boolean template;
        public JsonExpression(String expressionType) {
            this.expressionType = expressionType;
        }
        public String getExpressionType() {
            return expressionType;
        }
        public Boolean getTemplate() {
            return template;
        }
        public void setTemplate(Boolean template) {
            this.template = template;
        }
    }
    
    @JsonPropertyOrder({ "expression-type" })
    public static class JsonExpressionSet extends JsonExpression implements ExpressionSet{
        private String operator;
        private List<JsonExpression> expressions;
        public JsonExpressionSet() {
            this(null, null);
        }
        public JsonExpressionSet(List<JsonExpression> expressions) {
            this(null, expressions);
        }
        public JsonExpressionSet(String operator, List<JsonExpression> expressions) {
            super("expression-set");
            this.operator = operator;
            this.expressions = expressions;
        }
        public String getOperator() {
            return operator;
        }
        public void setOperator(String operator) {
            this.operator = operator;
        }
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public List<Expression> getExpressions() {
            return (List)expressions;
        }
        public void setExpressions(List<JsonExpression> expressions) {
            this.expressions = expressions;
        }
        @Override
        public boolean equals(Object obj) {
            JsonExpressionSet that = (JsonExpressionSet) obj;
            return Equals.equal(operator, that.operator)
                    && Equals.equal(expressions, that.expressions);
        }
        @Override
        public int hashCode() {
            return Objects.hash(operator, expressions);
        }
    }
    
    @JsonPropertyOrder({ "expressionType" })
    public static class JsonPropertyExpression extends JsonExpression implements PropertyExpression {
        @JsonProperty("expression-type")
        private final String expressionType = "property";
        private String name;
        private String value;
        private String operator;
        @JsonProperty("exact-match")
        private Boolean exactMatch;
        private Boolean repeating;
        @JsonProperty("case-sensitive")
        private Boolean caseSensitive;
        private Boolean fuzzy;
        public JsonPropertyExpression() {
            this(null, null, null, null, null, null, null);
        }
        public JsonPropertyExpression(String name, String value, String operator) {
            this(name, value, operator, null, null, null, null);
        }
        public JsonPropertyExpression(String name, String value, String operator,
                Boolean exactMatch, Boolean repeating, Boolean caseSensitive, Boolean fuzzy) {
            super("property");
            this.name = name;
            this.value = value;
            this.operator = operator;
            this.exactMatch = exactMatch;
            this.repeating = repeating;
            this.caseSensitive = caseSensitive;
            this.fuzzy = fuzzy;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getOperator() {
            return operator;
        }
        public void setOperator(String operator) {
            this.operator = operator;
        }
        public Boolean getExactMatch() {
            return exactMatch;
        }
        public void setExactMatch(Boolean exactMatch) {
            this.exactMatch = exactMatch;
        }
        public Boolean getRepeating() {
            return repeating;
        }
        public void setRepeating(Boolean repeating) {
            this.repeating = repeating;
        }
        public Boolean getCaseSensitive() {
            return caseSensitive;
        }
        public void setCaseSensitive(Boolean caseSensitive) {
            this.caseSensitive = caseSensitive;
        }
        public Boolean getFuzzy() {
            return fuzzy;
        }
        public void setFuzzy(Boolean fuzzy) {
            this.fuzzy = fuzzy;
        }
        public String getExpressionType() {
            return expressionType;
        }
        @Override
        public boolean equals(Object obj) {
            JsonPropertyExpression that = (JsonPropertyExpression) obj;
            return Equals.equal(name, that.name)
                    && Equals.equal(value, that.value)
                    && Equals.equal(operator, that.operator)
                    && Equals.equal(exactMatch, that.exactMatch)
                    && Equals.equal(repeating, that.repeating)
                    && Equals.equal(caseSensitive, that.caseSensitive)
                    && Equals.equal(fuzzy, that.fuzzy);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name, value, operator);
        }
    }
    
    @JsonPropertyOrder({ "expressionType" })
    public static class JsonFullTextExpression extends JsonExpression implements FullTextExpression {
        private Boolean fuzzy;
        private String value;
        public JsonFullTextExpression() {
            this(null, null);
        }
        public JsonFullTextExpression(String value, Boolean fuzzy) {
            super("fulltext");
            this.value = value;
            this.fuzzy = fuzzy;
        }
        public Boolean getFuzzy() {
            return fuzzy;
        }
        public void setFuzzy(Boolean fuzzy) {
            this.fuzzy = fuzzy;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            JsonFullTextExpression that = (JsonFullTextExpression) obj;
            return Equals.equal(fuzzy, that.fuzzy)
                    && Equals.equal(value, that.value);
        }
        @Override
        public int hashCode() {
            return Objects.hash(fuzzy, value);
        }
    }
    
    @JsonPropertyOrder({ "expressionType" })
    public static class JsonRelativeDateExpression extends JsonExpression implements RelativeDateExpression {
        private String name;
        private int value;
        @JsonProperty("time-unit")
        private String timeUnit;
        private String operator;
        private Boolean repeating;
        public JsonRelativeDateExpression() {
            this(null, 0, null, null);
        }
        public JsonRelativeDateExpression(String name, int value, String timeUnit, String operator) {
            super("relative-date");
            this.name = name;
            this.value = value;
            this.timeUnit = timeUnit;
            this.operator = operator;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
        public String getTimeUnit() {
            return timeUnit;
        }
        public void setTimeUnit(String timeUnit) {
            this.timeUnit = timeUnit;
        }
        public String getOperator() {
            return operator;
        }
        public void setOperator(String operator) {
            this.operator = operator;
        }
        public Boolean getRepeating() {
            return repeating;
        }
        public void setRepeating(Boolean repeating) {
            this.repeating = repeating;
        }
        @Override
        public boolean equals(Object obj) {
            JsonRelativeDateExpression that = (JsonRelativeDateExpression) obj;
            return Equals.equal(name, that.name)
                    && Equals.equal(value, that.value)
                    && Equals.equal(timeUnit, that.timeUnit)
                    && Equals.equal(operator, that.operator);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name, value, timeUnit, operator);
        }
    }
    
    @JsonPropertyOrder({ "expressionType" })
    public static class JsonPropertyListExpression extends JsonExpression implements PropertyListExpression {
        private String name;
        private String operator;
        private List<String> values;
        private Boolean repeating;
        public JsonPropertyListExpression() {
            this(null, null, null, null);
        }
        public JsonPropertyListExpression(String name, List<String> values) {
            this(name, values, null, null);
        }
        public JsonPropertyListExpression(String name, List<String> values, String operator, Boolean repeating) {
            super("property-list");
            this.name = name;
            this.values = values;
            this.operator = operator;
            this.repeating = repeating;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getOperator() {
            return operator;
        }
        public void setOperator(String operator) {
            this.operator = operator;
        }
        public List<String> getValues() {
            return values;
        }
        public void setValues(List<String> values) {
            this.values = values;
        }
        public Boolean getRepeating() {
            return repeating;
        }
        public void setRepeating(Boolean repeating) {
            this.repeating = repeating;
        }
        @Override
        public boolean equals(Object obj) {
            JsonPropertyListExpression that = (JsonPropertyListExpression) obj;
            return Equals.equal(name, that.name)
                    && Equals.equal(operator, that.operator)
                    && Equals.equal(values, that.values)
                    && Equals.equal(repeating, that.repeating);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name, operator, values);
        }
    }
    
    @JsonPropertyOrder({ "expressionType" })
    public static class JsonPropertyRangeExpression extends JsonExpression implements PropertyRangeExpression {
        private String name;
        private String from;
        private String to;
        private Boolean repeating;

        public JsonPropertyRangeExpression() {
            this(null, null, null);
        }
        public JsonPropertyRangeExpression(String name, String from, String to) {
            super("property-range");
            this.name = name;
            this.from = from;
            this.to = to;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getOperator() {
            return OPERATOR_BETWEEN;
        }
        public String getFrom() {
            return from;
        }
        public void setFrom(String from) {
            this.from = from;
        }
        public String getTo() {
            return to;
        }
        public void setTo(String to) {
            this.to = to;
        }
        public Boolean getRepeating() {
            return repeating;
        }
        public void setRepeating(Boolean repeating) {
            this.repeating = repeating;
        }
        @Override
        public boolean equals(Object obj) {
            JsonPropertyRangeExpression that = (JsonPropertyRangeExpression) obj;
            return Equals.equal(name, that.name)
                    && Equals.equal(from, that.from)
                    && Equals.equal(to, that.to)
                    && Equals.equal(repeating, that.repeating);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name, from, to);
        }
    }
}