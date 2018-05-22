/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.AvailableAuditEvents;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonAvailableAuditEvents extends JsonLinkableBase implements AvailableAuditEvents {
    private List<String> events;

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object obj) {
        JsonAvailableAuditEvents that = (JsonAvailableAuditEvents) obj;
        return Equals.equal(events, that.events)
                && super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(events);
    }
}
