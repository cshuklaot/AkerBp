/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;




/**
 * the link of the resources
 */
public interface Link {
    /**
     * @return the link relation of the link
     */
    public String getRel();
    
    /**
     * @return the link href
     */
    public String getHref();
    
    /**
     * @return the specified title of each link, can be used for some links which have same relation
     */
    public String getTitle();
    
    public String getType();
    
    /**
     * @return the href template if the link is a template
     */
    public String getHreftemplate();
    
    /**
     * @return whether the link is a template or not
     */
    public boolean isTemplate();
}
