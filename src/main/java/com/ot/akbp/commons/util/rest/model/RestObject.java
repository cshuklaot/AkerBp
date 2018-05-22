/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.Map;

/**
 * the class represents the object of the REST services
 */
public interface RestObject extends Linkable {
    /**
     * @return item type, eg. dm_document
     */
    String getType();
    
    /**
     * @return object root name
     */
    String getName();
    
    /**
     * @return object definition type
     */
    String getDefinition();
    
    /**
     * @return href the href of the object
     */
    String getHref();
    
    /**
     * @return the object id
     */
    String getObjectId();
    
    /**
     * @return the object name
     */
    String getObjectName();
    
    /**
     * @return the object type
     */
    String getObjectType();
    
    /**
     * @return all attributes of item
     */
    Map<String, Object> getProperties();
    
    /**
     * @return the xsi:type properties, valid for xml only
     */
    String getPropertiesType();
    
   
}
