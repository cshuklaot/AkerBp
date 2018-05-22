/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Lifecycle;
import com.emc.documentum.rest.client.sample.model.json.JsonLifecycleState.JsonModule;
import com.emc.documentum.rest.client.sample.model.json.JsonLifecycleState.JsonProcedure;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonLifecycle extends JsonInlineLinkableBase implements Lifecycle {
    private String id;
    private String name;
    private String title;
    private String subject;
    private List<String> keywords;
    private String owner;
    private Date created;
    private Date modified;
    @JsonProperty("implementation-type")
    private String implementationType;
    @JsonProperty("version-labels")
    private List<String> versionLabels;
    @JsonProperty("alias-sets")
    private List<String> aliasSets;
    @JsonProperty("type-inclusions")
    private List<JsonTypeInclusion> typeInclusions;
    @JsonProperty("states")
    private List<JsonLifecycleState> states;
    @JsonProperty("user-validation-service")
    private JsonModule userValidationService;
    @JsonProperty("app-validation")
    private JsonProcedure appValidation;
    private String status;
    private String statusMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getImplementationType() {
        return implementationType;
    }

    public void setImplementationType(String implementationType) {
        this.implementationType = implementationType;
    }

    public List<String> getVersionLabels() {
        return versionLabels;
    }

    public void setVersionLabels(List<String> versionLabels) {
        this.versionLabels = versionLabels;
    }

    public List<String> getAliasSets() {
        return aliasSets;
    }

    public void setAliasSets(List<String> aliasSets) {
        this.aliasSets = aliasSets;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<TypeInclusion> getTypeInclusions() {
        return (List)typeInclusions;
    }

    public void setTypeInclusions(List<JsonTypeInclusion> typeInclusions) {
        this.typeInclusions = typeInclusions;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<LifecycleState> getStates() {
        return (List)states;
    }

    public void setStates(List<JsonLifecycleState> states) {
        this.states = states;
    }

    public Module getUserValidationService() {
        return userValidationService;
    }

    public void setUserValidationService(JsonModule userValidationService) {
        this.userValidationService = userValidationService;
    }

    public Procedure getAppValidation() {
        return appValidation;
    }

    public void setAppValidation(JsonProcedure appValidation) {
        this.appValidation = appValidation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public boolean equals(Object obj) {
        JsonLifecycle that = (JsonLifecycle) obj;
        return Equals.equal(id, that.id)
                && Equals.equal(name, that.name)
                && Equals.equal(title, that.title)
                && Equals.equal(subject, that.subject)
                && Equals.equal(keywords, that.keywords)
                && Equals.equal(owner, that.owner)
                && Equals.equal(created, that.created)
                && Equals.equal(modified, that.modified)
                && Equals.equal(implementationType, that.implementationType)
                && Equals.equal(versionLabels, that.versionLabels)
                && Equals.equal(aliasSets, that.aliasSets)
                && Equals.equal(typeInclusions, that.typeInclusions)
                && Equals.equal(states, that.states)
                && Equals.equal(userValidationService, that.userValidationService)
                && Equals.equal(appValidation, that.appValidation)
                && Equals.equal(status, that.status)
                && Equals.equal(statusMessage, that.statusMessage)
                && super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, states, getSrc(), getContentType());
    }
    
    public static class JsonTypeInclusion implements TypeInclusion {
        private String type;
        @JsonProperty("include-subtypes")
        private boolean includeSubtypes;
        
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public boolean isIncludeSubtypes() {
            return includeSubtypes;
        }
        public void setIncludeSubtypes(boolean includeSubtypes) {
            this.includeSubtypes = includeSubtypes;
        }
        @Override
        public boolean equals(Object obj) {
            JsonTypeInclusion that = (JsonTypeInclusion) obj;
            return Equals.equal(type, that.type)
                    && Equals.equal(includeSubtypes, that.includeSubtypes);
        }
        @Override
        public int hashCode() {
            return Objects.hash(type, includeSubtypes);
        }
    }
    
}