/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Lifecycle.EntryCriteria;
import com.emc.documentum.rest.client.sample.model.Lifecycle.LifecycleState;
import com.emc.documentum.rest.client.sample.model.Lifecycle.Module;
import com.emc.documentum.rest.client.sample.model.Lifecycle.Procedure;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonLifecycleState implements LifecycleState {
    private String name;
    private String type;
    private String description;
    private boolean exceptional;
    @JsonProperty("exception-state")
    private String exceptionState;
    @JsonProperty("allow-attach")
    private boolean allowAttach;
    @JsonProperty("allow-schedule")
    private boolean allowSchedule;
    @JsonProperty("allow-return-to-base")
    private boolean allowReturnToBase;
    @JsonProperty("allow-demote")
    private boolean allowDemote;
    private int no;
    private int index;
    @JsonProperty("return-conditions")
    private List<String> returnConditions;
    @JsonProperty("entry-criteria")
    private JsonEntryCriteria entryCriteria;
    @JsonProperty("user-criteria")
    private JsonProcedure userCriteria;
    @JsonProperty("action")
    private JsonProcedure action;
    @JsonProperty("user-action")
    private JsonProcedure userAction;
    @JsonProperty("user-post-action")
    private JsonProcedure userPostAction;
    @JsonProperty("user-criteria-service")
    private JsonModule userCriteriaService;
    @JsonProperty("user-action-service")
    private JsonModule userActionService;
    @JsonProperty("user-post-service")
    private JsonModule userPostService;
    @JsonProperty("system-action")
    private JsonModule systemAction;
    @JsonProperty("type-override-id")
    private String typeOverrideId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExceptional() {
        return exceptional;
    }

    public void setExceptional(boolean exceptional) {
        this.exceptional = exceptional;
    }

    public String getExceptionState() {
        return exceptionState;
    }

    public void setExceptionState(String exceptionState) {
        this.exceptionState = exceptionState;
    }

    public boolean isAllowAttach() {
        return allowAttach;
    }

    public void setAllowAttach(boolean allowAttach) {
        this.allowAttach = allowAttach;
    }

    public boolean isAllowSchedule() {
        return allowSchedule;
    }

    public void setAllowSchedule(boolean allowSchedule) {
        this.allowSchedule = allowSchedule;
    }

    public boolean isAllowReturnToBase() {
        return allowReturnToBase;
    }

    public void setAllowReturnToBase(boolean allowReturnToBase) {
        this.allowReturnToBase = allowReturnToBase;
    }

    public boolean isAllowDemote() {
        return allowDemote;
    }

    public void setAllowDemote(boolean allowDemote) {
        this.allowDemote = allowDemote;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getReturnConditions() {
        return returnConditions;
    }

    public void setReturnConditions(List<String> returnConditions) {
        this.returnConditions = returnConditions;
    }

    public EntryCriteria getEntryCriteria() {
        return entryCriteria;
    }

    public void setEntryCriteria(JsonEntryCriteria entryCriteria) {
        this.entryCriteria = entryCriteria;
    }

    public Procedure getUserCriteria() {
        return userCriteria;
    }

    public void setUserCriteria(JsonProcedure userCriteria) {
        this.userCriteria = userCriteria;
    }

    public Procedure getAction() {
        return action;
    }

    public void setAction(JsonProcedure action) {
        this.action = action;
    }

    public Procedure getUserAction() {
        return userAction;
    }

    public void setUserAction(JsonProcedure userAction) {
        this.userAction = userAction;
    }

    public Procedure getUserPostAction() {
        return userPostAction;
    }

    public void setUserPostAction(JsonProcedure userPostAction) {
        this.userPostAction = userPostAction;
    }

    public Module getUserCriteriaService() {
        return userCriteriaService;
    }

    public void setUserCriteriaService(JsonModule userCriteriaService) {
        this.userCriteriaService = userCriteriaService;
    }

    public Module getUserActionService() {
        return userActionService;
    }

    public void setUserActionService(JsonModule userActionService) {
        this.userActionService = userActionService;
    }

    public Module getUserPostService() {
        return userPostService;
    }

    public void setUserPostService(JsonModule userPostService) {
        this.userPostService = userPostService;
    }

    public Module getSystemAction() {
        return systemAction;
    }

    public void setSystemAction(JsonModule systemAction) {
        this.systemAction = systemAction;
    }

    public String getTypeOverrideId() {
        return typeOverrideId;
    }

    public void setTypeOverrideId(String typeOverrideId) {
        this.typeOverrideId = typeOverrideId;
    }

    @Override
    public boolean equals(Object obj) {
        JsonLifecycleState that = (JsonLifecycleState) obj;
        return Equals.equal(name, that.name)
                && Equals.equal(description, that.description)
                && Equals.equal(type, that.type)
                && Equals.equal(exceptional, that.exceptional)
                && Equals.equal(exceptionState, that.exceptionState)
                && Equals.equal(allowAttach, that.allowAttach)
                && Equals.equal(allowSchedule, that.allowSchedule)
                && Equals.equal(allowReturnToBase, that.allowReturnToBase)
                && Equals.equal(allowDemote, that.allowDemote)
                && Equals.equal(no, that.no)
                && Equals.equal(index, that.index)
                && Equals.equal(returnConditions, that.returnConditions)
                && Equals.equal(entryCriteria, that.entryCriteria)
                && Equals.equal(userCriteria, that.userCriteria)
                && Equals.equal(action, that.action)
                && Equals.equal(userAction, that.userAction)
                && Equals.equal(userPostAction, that.userPostAction)
                && Equals.equal(userCriteriaService, that.userCriteriaService)
                && Equals.equal(userActionService, that.userActionService)
                && Equals.equal(userPostService, that.userPostService)
                && Equals.equal(systemAction, that.systemAction)
                && Equals.equal(typeOverrideId, that.typeOverrideId)
                && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exceptional, exceptionState, no, entryCriteria);
    }


    public static class JsonEntryCriteria implements EntryCriteria {
        private String expression;
        private String id;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getExpression() {
            return expression;
        }
        public void setExpression(String expression) {
            this.expression = expression;
        }
        @Override
        public boolean equals(Object obj) {
            JsonEntryCriteria that = (JsonEntryCriteria) obj;
            return Equals.equal(id, that.id)
                    && Equals.equal(expression, that.expression);
        }
        @Override
        public int hashCode() {
            return Objects.hash(id, expression);
        }
    }
    
    public static class JsonProcedure implements Procedure {
        private String name;
        private String version;
        private String id;
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
        public String getVersion() {
            return version;
        }
        public void setVersion(String version) {
            this.version = version;
        }
        @Override
        public boolean equals(Object obj) {
            JsonProcedure that = (JsonProcedure) obj;
            return Equals.equal(id, that.id)
                    && Equals.equal(name, that.name)
                    && Equals.equal(version, that.version);
        }
        @Override
        public int hashCode() {
            return Objects.hash(id, name, version);
        }
    }
    
    public static class JsonModule implements Module {
        private String id;
        private String name;
        @JsonProperty("primary-class")
        private String primaryClass;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPrimaryClass() {
            return primaryClass;
        }
        public void setPrimaryClass(String primaryClass) {
            this.primaryClass = primaryClass;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        @Override
        public boolean equals(Object obj) {
            JsonModule that = (JsonModule) obj;
            return Equals.equal(id, that.id)
                    && Equals.equal(name, that.name)
                    && Equals.equal(primaryClass, that.primaryClass);
        }
        @Override
        public int hashCode() {
            return Objects.hash(id, name, primaryClass);
        }
    }
}