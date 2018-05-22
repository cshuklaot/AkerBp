/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestTypeValueConstraint;

public class JsonTypeValueConstraint implements RestTypeValueConstraint {
    private String expression;
    private String enforce;
    private String dependency;
    private String message;
    
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setEnforce(String enforce) {
        this.enforce = enforce;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public String getEnforce() {
        return enforce;
    }

    @Override
    public String getDependency() {
        return dependency;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonTypeValueConstraint that = (JsonTypeValueConstraint)obj;
        return Equals.equal(expression, that.expression) 
            && Equals.equal(enforce, that.enforce)
            && Equals.equal(message, that.message)
            && Equals.equal(dependency, that.dependency);
    }
}