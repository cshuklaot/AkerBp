/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;

import java.io.InputStream;

public interface Attachment {
    public Include getInclude();
    public String getContentType();
    public InputStream getContentStream();
}
