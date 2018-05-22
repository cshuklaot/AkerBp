/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.batch;

import java.io.InputStream;

public interface SettableAttachment extends Attachment {
    public void setInclude(Include include);
    public void setContentType(String contentType);
    public void setContentStream(InputStream contentStream);
}
