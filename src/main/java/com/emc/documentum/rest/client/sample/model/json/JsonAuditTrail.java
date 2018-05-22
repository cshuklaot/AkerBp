/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.emc.documentum.rest.client.sample.model.AuditTrail;
import com.emc.documentum.rest.client.sample.model.RestObject;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAuditTrail extends JsonObject implements AuditTrail {
    @JsonProperty("audit-data-list")
    private List<JsonAuditData> auditDataList;
    
    public JsonAuditTrail() {
        super();
    }

    public JsonAuditTrail(RestObject object) {
        super(object);
    }

    @Override
    public String getName() {
        return "audit-trail";
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<AuditData> getAuditDataList() {
        return (List)auditDataList;
    }

    public void setAuditDataList(List<JsonAuditData> auditDataList) {
        this.auditDataList = auditDataList;
    }

    public static class JsonAuditData implements AuditData {
        @JsonProperty("attr-name")
        private String attrName;
        @JsonProperty("value-pairs")
        private List<JsonValuePair> valuePairs;
        @Override
        public String getAttrName() {
            return attrName;
        }
        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }
        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public List<ValuePair> getValuePairs() {
            return (List)valuePairs;
        }
        public void setValuePairs(List<JsonValuePair> valuePairs) {
            this.valuePairs = valuePairs;
        }
    }
    
    @XmlRootElement(name="value-pair")
    public static class JsonValuePair implements ValuePair {
        @JsonProperty("old-value")
        private String oldValue;
        @JsonProperty("new-value")
        private String newValue;
        @Override
        public String getOldValue() {
            return oldValue;
        }
        public void setOldValue(String oldValue) {
            this.oldValue = oldValue;
        }
        @Override
        public String getNewValue() {
            return newValue;
        }
        public void setNewValue(String newValue) {
            this.newValue = newValue;
        }
    }
}
