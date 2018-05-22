/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */

package com.ot.akbp.commons.util.rest.model;

import java.util.List;

public interface PermissionSet extends Linkable {
    public List<Permission> getPermitted();
    public List<Permission> getRestricted();
    public List<String> getRequiredGroup();
    public List<String> getRequiredGroupSet();
}
