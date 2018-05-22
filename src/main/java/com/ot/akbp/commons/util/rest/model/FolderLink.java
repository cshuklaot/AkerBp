/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

/**
 * represents Folder Link provided by REST server
 */
public interface FolderLink extends Linkable {
    public String getHref();
    public String getParentId();
    public String getChildId();
}
