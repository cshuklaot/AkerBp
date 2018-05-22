/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.ObjectLifecycle;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonObjectLifecycle extends JsonInlineLinkableBase implements ObjectLifecycle {
    @JsonProperty("lifecycle-id")
    private String lifecycleId;
    @JsonProperty("lifecycle-name")
    private String lifecycleName;
    @JsonProperty("aliasset-name")
    private String aliassetName;
    @JsonProperty("current-state")
    private String currentState;
    @JsonProperty("previous-state")
    private String previousState;
    @JsonProperty("next-state")
    private String nextState;
    @JsonProperty("resume-state")
    private String resumeState;
    @JsonProperty("able-to-demote")
    private boolean ableToDemote;
    @JsonProperty("able-to-promote")
    private boolean ableToPromote;
    @JsonProperty("able-to-resume")
    private boolean ableToResume;
    @JsonProperty("able-to-suspend")
    private boolean ableToSuspend;
    @JsonProperty("schedules")
    private List<Schedule> schedules;
    
    public JsonObjectLifecycle(ObjectLifecycle objectLifecycle) {
        lifecycleId = objectLifecycle.getLifecycleId();
        lifecycleName = objectLifecycle.getLifecycleName();
        aliassetName = objectLifecycle.getAliassetName();
        currentState = objectLifecycle.getCurrentState();
        previousState = objectLifecycle.getPreviousState();
        nextState = objectLifecycle.getNextState();
        resumeState = objectLifecycle.getResumeState();
        ableToDemote = objectLifecycle.isAbleToDemote();
        ableToPromote = objectLifecycle.isAbleToPromote();
        ableToResume = objectLifecycle.isAbleToResume();
        ableToSuspend = objectLifecycle.isAbleToSuspend();
        this.schedules = objectLifecycle.getSchedules();
    }
    
    public JsonObjectLifecycle() {
    }

    @Override
    public String getLifecycleId() {
        return lifecycleId;
    }

    public void setLifecycleId(String lifecycleId) {
        this.lifecycleId = lifecycleId;
    }

    @Override
    public String getLifecycleName() {
        return lifecycleName;
    }

    public void setLifecycleName(String lifecycleName) {
        this.lifecycleName = lifecycleName;
    }

    @Override
    public String getAliassetName() {
        return aliassetName;
    }

    public void setAliassetName(String aliassetName) {
        this.aliassetName = aliassetName;
    }

    @Override
    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @Override
    public String getPreviousState() {
        return previousState;
    }

    public void setPreviousState(String previousState) {
        this.previousState = previousState;
    }

    @Override
    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    @Override
    public String getResumeState() {
        return resumeState;
    }

    public void setResumeState(String resumeState) {
        this.resumeState = resumeState;
    }

    @Override
    public boolean isAbleToDemote() {
        return ableToDemote;
    }

    public void setAbleToDemote(boolean ableToDemote) {
        this.ableToDemote = ableToDemote;
    }

    @Override
    public boolean isAbleToPromote() {
        return ableToPromote;
    }

    public void setAbleToPromote(boolean ableToPromote) {
        this.ableToPromote = ableToPromote;
    }

    @Override
    public boolean isAbleToResume() {
        return ableToResume;
    }

    public void setAbleToResume(boolean ableToResume) {
        this.ableToResume = ableToResume;
    }

    @Override
    public boolean isAbleToSuspend() {
        return ableToSuspend;
    }

    public void setAbleToSuspend(boolean ableToSuspend) {
        this.ableToSuspend = ableToSuspend;
    }

    @Override
    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object obj) {
        JsonObjectLifecycle that = (JsonObjectLifecycle) obj;
        return Equals.equal(lifecycleId, that.lifecycleId)
                && Equals.equal(lifecycleName, that.lifecycleName)
                && Equals.equal(aliassetName, that.aliassetName)
                && Equals.equal(currentState, that.currentState)
                && Equals.equal(previousState, that.previousState)
                && Equals.equal(nextState, that.nextState)
                && Equals.equal(resumeState, that.resumeState)
                && Equals.equal(ableToDemote, that.ableToDemote)
                && Equals.equal(ableToPromote, that.ableToPromote)
                && Equals.equal(ableToResume, that.ableToResume)
                && Equals.equal(ableToSuspend, that.ableToSuspend)
                && Equals.equal(schedules, that.schedules)
                && super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(lifecycleId, lifecycleName, currentState);
    }
    
    public static class JsonSchedule implements Schedule {
        private String action;
        private String schedule;
        private String from;
        private String to;
        @Override
        public String getAction() {
            return action;
        }
        public void setAction(String action) {
            this.action = action;
        }
        @Override
        public String getSchedule() {
            return schedule;
        }
        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }
        @Override
        public String getFrom() {
            return from;
        }
        public void setFrom(String from) {
            this.from = from;
        }
        @Override
        public String getTo() {
            return to;
        }
        public void setTo(String to) {
            this.to = to;
        }
        @Override
        public boolean equals(Object obj) {
            JsonSchedule that = (JsonSchedule) obj;
            return Equals.equal(action, that.action)
                    && Equals.equal(schedule, that.schedule)
                    && Equals.equal(from, that.from)
                    && Equals.equal(to, that.to);
        }
    }
}
