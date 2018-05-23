/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.client.util;

import java.util.Collection;

public class Collections {
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return c != null && !c.isEmpty();
    }
    
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }
}
