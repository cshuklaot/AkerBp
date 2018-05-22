/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Subscribers;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSubscribers implements Subscribers {
    @JsonProperty("subscribers")
    private List<String> subscribers;
    
    public JsonSubscribers(String... subscribers) {
        this.subscribers = Arrays.asList(subscribers);
    }
    
    public JsonSubscribers() {
    }

    @Override
    public List<String> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<String> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public boolean equals(Object obj) {
        JsonSubscribers that = (JsonSubscribers) obj;
        return Equals.equal(subscribers, that.subscribers);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(subscribers);
    }
}
