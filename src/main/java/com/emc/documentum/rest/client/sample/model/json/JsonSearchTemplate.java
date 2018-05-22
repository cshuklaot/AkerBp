/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.emc.documentum.rest.client.sample.model.Search;
import com.emc.documentum.rest.client.sample.model.SearchTemplate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.emc.documentum.rest.client.sample.client.impl.jackson.DCTMJacksonMapper.marshal;
import static com.emc.documentum.rest.client.sample.client.impl.jackson.DCTMJacksonMapper.unmarshal;

public class JsonSearchTemplate extends JsonObject implements SearchTemplate {
    @JsonProperty("query-document-template")
    private String queryDocumentTemplate;
    @JsonProperty("external-variables")
    private List<JsonExternalVariable<?>> externalVariables;
    @JsonProperty("search-reference")
    private String searchReference;
    
    public JsonSearchTemplate() {
    }

    public JsonSearchTemplate(SearchTemplate template) {
        super(template);
        setSearch(template.getSearch());
        setSearchReference(template.getSearchReference());
    }
    
    public JsonSearchTemplate(String searchReference) {
        this.searchReference = searchReference;
    }

    @Override
    public String getQueryDocumentTemplate() {
        return queryDocumentTemplate;
    }
    
    public void setQueryDocumentTemplate(String queryDocumentTemplate) {
        this.queryDocumentTemplate = queryDocumentTemplate;
    }
    
    @Override
    @JsonIgnore
    public Search getSearch() {
        return queryDocumentTemplate==null?null:unmarshal(queryDocumentTemplate, JsonSearch.class);
    }

    public void setSearch(Search search) {
        try {
            queryDocumentTemplate = search == null?null:marshal(search);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<ExternalVariable<?>> getExternalVariables() {
        return (List)externalVariables;
    }

    public void setExternalVariables(List<JsonExternalVariable<?>> externalVariables) {
        this.externalVariables = externalVariables;
    }

    @Override
    public String getSearchReference() {
        return searchReference;
    }

    public void setSearchReference(String searchReference) {
        this.searchReference = searchReference;
    }
    
    @JsonSubTypes({
        @JsonSubTypes.Type(value = JsonFullTextVariable.class, name = "fulltext-variable"),
        @JsonSubTypes.Type(value = JsonPropertyValueVariable.class, name = "property-variable"),
        @JsonSubTypes.Type(value = JsonRelativeDateVariable.class, name = "relative-date-variable"),
        @JsonSubTypes.Type(value = JsonPropertyListVariable.class, name = "property-list-variable")
    })
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "variable-type")
    public static abstract class JsonExternalVariable<T> implements ExternalVariable<T> {
        @JsonProperty
        private String id;
        @JsonProperty("expression-type")
        private String expressionType;
        @JsonProperty("data-type")
        private String dataType;
        @Override
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        @Override
        public String getExpressionType() {
            return expressionType;
        }
        public void setExpressionType(String expressionType) {
            this.expressionType = expressionType;
        }
        @Override
        public String getDataType() {
            return dataType;
        }
        public void setDataType(String dataType) {
            this.dataType = dataType;
        }
    }
    
    public abstract static class JsonPropertyVariable<T> extends JsonExternalVariable<T> implements PropertyVariable<T> {
        @JsonProperty("property-name")
        private String propertyName;
        @JsonProperty
        private String operator;
        @Override
        public String getPropertyName() {
            return propertyName;
        }
        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }
        @Override
        public String getOperator() {
            return operator;
        }
        public void setOperator(String operator) {
            this.operator = operator;
        }
    }
    
    @JsonPropertyOrder({ "variableType" })
    public static class JsonFullTextVariable extends JsonExternalVariable<String> implements FullTextVariable {
        @JsonProperty("variable-type")
        private final String variableType = "fulltext-variable";
        @JsonProperty("variable-value")
        private String variableValue;
        @Override
        public String getVariableValue() {
            return variableValue;
        }
        public void setVariableValue(String variableValue) {
            this.variableValue = variableValue;
        }
        public String getVariableType() {
            return variableType;
        }
    }
    
    @JsonPropertyOrder({ "variableType" })
    public static class JsonPropertyValueVariable extends JsonPropertyVariable<String> implements PropertyValueVariable {
        @JsonProperty("variable-type")
        private final String variableType = "property-variable";
        @JsonProperty("variable-value")
        private String variableValue;
        @Override
        public String getVariableValue() {
            return variableValue;
        }
        public void setVariableValue(String variableValue) {
            this.variableValue = variableValue;
        }
        public String getVariableType() {
            return variableType;
        }
    }
    
    @JsonPropertyOrder({ "variableType" })
    public static class JsonRelativeDateVariable extends JsonPropertyVariable<String> implements RelativeDateVariable {
        @JsonProperty("variable-type")
        private final String variableType = "relative-date-variable";
        @JsonProperty("variable-value")
        private String variableValue;
        @Override
        public String getVariableValue() {
            return variableValue;
        }
        public void setVariableValue(String variableValue) {
            this.variableValue = variableValue;
        }
        public String getVariableType() {
            return variableType;
        }
    }
    
    @JsonPropertyOrder({ "variableType" })
    public static class JsonPropertyListVariable extends JsonPropertyVariable<List<String>> implements PropertyListVariable {
        @JsonProperty("variable-type")
        private final String variableType = "property-list-variable";
        @JsonProperty("variable-values")
        private List<String> variableValue;
        @Override
        public List<String> getVariableValue() {
            return variableValue;
        }
        public void setVariableValue(List<String> variableValue) {
            this.variableValue = variableValue;
        }
        public String getVariableType() {
            return variableType;
        }
    }
}
