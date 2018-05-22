/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */

package com.ot.akbp.commons.util.rest.model;

public interface Permission extends Linkable {
    public String getAccessor();
    public String getBasicPermission();
    public String getExtendPermissions();
    public String getApplicationPermission();
}
