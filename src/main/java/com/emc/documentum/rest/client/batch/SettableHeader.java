/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;

public interface SettableHeader extends Header {
    public void setName(String name);
    public void setValue(String value);
}
