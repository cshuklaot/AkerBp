/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Preference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPreference extends JsonInlineLinkableBase implements Preference {
    private String client;
    @JsonProperty("owner-name")
    private String owner;
    private String title;
    private String subject;
    private List<String> keywords;
    @JsonProperty("creation-date")
    private String creationDate;
    @JsonProperty("modify-date")
    private String modifyDate;
    @JsonProperty("preference-content")
    private String preference;

    public JsonPreference() {
    }
    
    public JsonPreference(Preference p) {
        this.client = p.getClient();
        this.owner = p.getOwner();
        this.title = p.getTitle();
        this.subject = p.getSubject();
        this.keywords = p.getKeywords();
        this.creationDate = p.getCreationDate();
        this.modifyDate = p.getModifyDate();
        this.preference = p.getPreference();
    }

    @Override
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    @Override
    public boolean equals(Object obj) {
        JsonPreference that = (JsonPreference)obj;
        return Equals.equal(client, that.client)
            && Equals.equal(owner, that.owner)
            && Equals.equal(title, that.title)
            && Equals.equal(subject, that.subject)
            && Equals.equal(keywords, that.keywords)
            && Equals.equal(creationDate, that.creationDate)
            && Equals.equal(modifyDate, that.modifyDate)
            && Equals.equal(preference, that.preference)
            && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, owner, title, subject, keywords, modifyDate, creationDate, getSrc(), getContentType());
    }
}
