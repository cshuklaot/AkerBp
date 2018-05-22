/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.client.impl.jackson;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * the class to unmarshal the xml
 */
public final class DCTMJacksonMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    
    public static <T> T unmarshal(String json, Class<T> clazz) {
        T obj = null;
        try {
            obj = MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return obj;
    }
    
    public static void marshal(OutputStream os, Object object) {
        try {
            MAPPER.writeValue(os, object);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public static String marshal(Object object) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        marshal(os, object);
        return os.toString();
    }
}
