/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RestServiceVersion
@Inherited
public @interface RestServiceSample {
    String value();
}
