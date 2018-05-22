/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.ValueAssistant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonValueAssistance extends JsonLinkableBase implements ValueAssistant {
    @JsonIgnore
    private List<Attribute> properties;
    @SuppressWarnings("rawtypes")
    @JsonProperty("properties")
    private Map<String, Map> propertiesMap;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Attribute> getProperties() {
        if(properties == null && propertiesMap != null) {
            properties = new ArrayList<>();
            for(Map.Entry<String, Map> entry : propertiesMap.entrySet()) {
                JsonAssistantAttribute a = new JsonAssistantAttribute(entry.getKey());
                a.allowUserValues((boolean)entry.getValue().get("allow-user-values"));
                for(Map<String, String> v : (List<Map<String, String>>)entry.getValue().get("values")) {
                    a.addValue(new JsonValue(String.valueOf(v.get("value")), v.get("label")));
                }
                properties.add(a);
            }
        }
        return properties;
    }

    @SuppressWarnings("rawtypes")
    public void setPropertiesMap(Map<String, Map> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }
    
    private static class JsonAssistantAttribute implements Attribute {
        private final String name;
        private boolean allowUserValues;
        private List<Value> values = new ArrayList<>();

        JsonAssistantAttribute(String name) {
            this.name = name;
        }
        
        @Override
        public String name() {
            return name;
        }
        
        void allowUserValues(boolean allowUserValues) {
            this.allowUserValues = allowUserValues;
        }
        
        @Override
        public boolean allowUserValues() {
            return allowUserValues;
        }
        
        @Override
        public List<Value> values() {
            return values;
        }
        
        private void addValue(Value value) {
            values.add(value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, allowUserValues, values);
        }

        @Override
        public boolean equals(Object obj) {
            JsonAssistantAttribute that = (JsonAssistantAttribute)obj;
            return Equals.equal(name, that.name)
                && Equals.equal(allowUserValues, that.allowUserValues)
                && Equals.equal(values, that.values);
        }
    }
    
    private static class JsonValue implements Value {
        private final String value;
        private final String label;
        
        public JsonValue(String value, String label) {
            this.value = value;
            this.label = label;
        }
        
        @Override
        public String value() {
            return value;
        }
        
        @Override
        public String label() {
            return label;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, label);
        }

        @Override
        public boolean equals(Object obj) {
            JsonValue that = (JsonValue)obj;
            return Equals.equal(value, that.value)
                && Equals.equal(label, that.label);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonValueAssistance that = (JsonValueAssistance) obj;
        return Equals.equal(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }
}
