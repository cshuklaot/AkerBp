/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.emc.documentum.rest.client.sample.client.util.Collections;
import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestProperty;
import com.emc.documentum.rest.client.sample.model.RestPropertyLifecycleInfo;
import com.emc.documentum.rest.client.sample.model.RestPropertyValueAssist;
import com.emc.documentum.rest.client.sample.model.RestTypeMappingTable;
import com.emc.documentum.rest.client.sample.model.RestTypeValueConstraint;

public class JsonProperty implements RestProperty {
    private String name;
    private String type;
    private String label;
    private int length;
    private boolean repeating;
    private boolean hidden;
    private boolean required;
    @com.fasterxml.jackson.annotation.JsonProperty("notnull")
    private boolean notNull;
    @com.fasterxml.jackson.annotation.JsonProperty("readonly")
    private boolean readOnly;
    private boolean searchable;
    @com.fasterxml.jackson.annotation.JsonProperty("display-hint")
    private int displayHint;
    @com.fasterxml.jackson.annotation.JsonProperty("default-literal")
    private Object defaultLiteral;
    @com.fasterxml.jackson.annotation.JsonProperty("default-expression")
    private Object defaultExpression;
    @com.fasterxml.jackson.annotation.JsonProperty("defaults")
    private List<Map<String, Object>> defaultList;
    private List<String> dependencies;
    @com.fasterxml.jackson.annotation.JsonProperty("value-assist")
    private List<JsonPropertyValueAssist> valueAssist;
    @com.fasterxml.jackson.annotation.JsonProperty("lifecycles")
    private Map<String, Map<String, JsonPropertyLifecycleInfo>> propertyLifecycles;
    @com.fasterxml.jackson.annotation.JsonProperty("notnull-enforce")
    private String notNullEnforce;
    @com.fasterxml.jackson.annotation.JsonProperty("notnull-message")
    private String notNullMessage;
    @com.fasterxml.jackson.annotation.JsonProperty("mapping-tables")
    private List<JsonTypeMappingTable> mappingTables;
    @com.fasterxml.jackson.annotation.JsonProperty("ignore-parent-constraints")
    private boolean ignoreConstraints;
    @com.fasterxml.jackson.annotation.JsonProperty("ignore-immutable")
    private boolean ignoreImmutable;
    private List<JsonTypeValueConstraint> constraints;
    @com.fasterxml.jackson.annotation.JsonProperty("help-text")
    private String helpText;
    @com.fasterxml.jackson.annotation.JsonProperty("comment-text")
    private String commentText;
    @com.fasterxml.jackson.annotation.JsonProperty("category")
    private String category;
    
    public Object getDefaultLiteral() {
        return defaultLiteral;
    }

    public void setDefaultLiteral(Object defaultLiteral) {
        this.defaultLiteral = defaultLiteral;
    }

    public Object getDefaultExpression() {
        return defaultExpression;
    }

    public void setDefaultExpression(Object defaultExpression) {
        this.defaultExpression = defaultExpression;
    }

    public List<Map<String, Object>> getDefaultList() {
        return defaultList;
    }

    public void setDefaultList(List<Map<String, Object>> defaultList) {
        this.defaultList = defaultList;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<DefaultValue> getDefault() {
        if(Collections.isNotEmpty(defaultList)) {
            List<JsonDefaultValue> defaultValue = new ArrayList<>();
            for(Map<String, Object> m : defaultList) {
                if(m.containsKey("default-literal")) {
                    defaultValue.add(new JsonDefaultValue(m.get("default-literal"), false));
                }
                if(m.containsKey("default-expression")) {
                    defaultValue.add(new JsonDefaultValue(m.get("default-expression"), true));
                }
            }
            return (List)defaultValue;
        } else if(StringUtils.isEmpty(defaultExpression)) {
            return Arrays.asList((DefaultValue)new JsonDefaultValue(defaultExpression, true));
        } else if(StringUtils.isEmpty(defaultLiteral)) {
            return Arrays.asList((DefaultValue)new JsonDefaultValue(defaultLiteral, false));
        }
        return null;
    }

    public void setDefault(List<JsonDefaultValue> defaultValue) {
        if(Collections.isNotEmpty(defaultValue)) {
            if(defaultValue.size() == 1) {
                if(defaultValue.get(0).isExpression()) {
                    defaultExpression  = defaultValue.get(0).getExpression();
                } else {
                    defaultLiteral = defaultValue.get(0).getExpression();
                }
            } else {
                defaultList = new ArrayList<>(defaultValue.size());
                for(JsonDefaultValue d : defaultValue) {
                    if(d.isExpression()) {
                        defaultList.add(java.util.Collections.singletonMap("default-expression", d.getExpression()));
                    } else {
                        defaultList.add(java.util.Collections.singletonMap("default-literal", d.getExpression()));
                    }
                }
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public void setDisplayHint(int displayHint) {
        this.displayHint = displayHint;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public void setPropertyLifecycles(Map<String, Map<String, JsonPropertyLifecycleInfo>> propertyLifecycles) {
        this.propertyLifecycles = propertyLifecycles;
    }

    public void setNotNullEnforce(String notNullEnforce) {
        this.notNullEnforce = notNullEnforce;
    }

    public void setNotNullMessage(String notNullMessage) {
        this.notNullMessage = notNullMessage;
    }

    public void setMappingTables(List<JsonTypeMappingTable> mappingTables) {
        this.mappingTables = mappingTables;
    }

    public void setIgnoreConstraints(boolean ignoreConstraints) {
        this.ignoreConstraints = ignoreConstraints;
    }

    public void setIgnoreImmutable(boolean ignoreImmutable) {
        this.ignoreImmutable = ignoreImmutable;
    }

    public void setConstraints(List<JsonTypeValueConstraint> constraints) {
        this.constraints = constraints;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public boolean isRepeating() {
        return repeating;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isNotNull() {
        return notNull;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public boolean isSearchable() {
        return searchable;
    }

    @Override
    public int getDisplayHint() {
        return displayHint;
    }

    @Override
    public List<String> getDependencies() {
        return dependencies;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map<String, Map<String, RestPropertyLifecycleInfo>> getPropertyLifecycles() {
        return (Map)propertyLifecycles;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<RestTypeMappingTable> getMappingTables() {
        return (List)mappingTables;
    }

    @Override
    public boolean isIgnoreConstraints() {
        return ignoreConstraints;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<RestTypeValueConstraint> getConstraints() {
        return (List)constraints;
    }

    @Override
    public String getHelpText() {
        return helpText;
    }

    @Override
    public String getCommentText() {
        return commentText;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public boolean isIgnoreImmutable() {
        return ignoreImmutable;
    }

    @Override
    public String getNotNullEnforce() {
        return notNullEnforce;
    }

    @Override
    public String getNotNullMessage() {
        return notNullMessage;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<RestPropertyValueAssist> getValueAssist() {
        return (List)valueAssist;
    }

    public void setValueAssist(List<JsonPropertyValueAssist> valueAssist) {
        this.valueAssist = valueAssist;
    }

    @Override
    public boolean equals(Object obj) {
        JsonProperty that = (JsonProperty)obj;
        return Equals.equal(name, that.name) 
            && Equals.equal(type, that.type)
            && Equals.equal(label, that.label)
            && Equals.equal(length, that.length)
            && Equals.equal(repeating, that.repeating)
            && Equals.equal(hidden, that.hidden)
            && Equals.equal(required, that.required)
            && Equals.equal(notNull, that.notNull)
            && Equals.equal(readOnly, that.readOnly)
            && Equals.equal(searchable, that.searchable)
            && Equals.equal(displayHint, that.displayHint)
            && Equals.equal(dependencies, that.dependencies)
            && Equals.equal(defaultExpression, that.defaultExpression)
            && Equals.equal(defaultLiteral, that.defaultLiteral)
            && Equals.equal(defaultList, that.defaultList)
            && Equals.equal(propertyLifecycles, that.propertyLifecycles)
            && Equals.equal(notNullEnforce, that.notNullEnforce)
            && Equals.equal(notNullMessage, that.notNullMessage)
            && Equals.equal(mappingTables, that.mappingTables)
            && Equals.equal(ignoreConstraints, that.ignoreConstraints)
            && Equals.equal(ignoreImmutable, that.ignoreImmutable)
            && Equals.equal(helpText, that.helpText)
            && Equals.equal(commentText, that.commentText)
            && Equals.equal(category, that.category)
            && Equals.equal(constraints, that.constraints)
            && Equals.equal(valueAssist, that.valueAssist);
    }

    public static class JsonDefaultValue implements DefaultValue {
        private boolean isExpression;
        private Object expression;

        public JsonDefaultValue(Object expression, boolean isExpression) {
            this.isExpression = isExpression;
            this.expression = expression;
        }

        public Object getExpression() {
            return expression;
        }

        public boolean isExpression() {
            return isExpression;
        }
        
        @Override
        public boolean equals(Object obj) {
            JsonDefaultValue that = (JsonDefaultValue)obj;
            return Equals.equal(isExpression, that.isExpression) 
                && Equals.equal(expression, that.expression);
        }
    }
}
