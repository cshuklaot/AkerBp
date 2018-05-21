/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest;

import java.util.List;



/**
 * the plain ObjectAspects implementation
 * which is used to attach the aspects to the object only 
 */
public abstract class PlainLinkableBase implements Linkable {
    @Override
    public List<Link> getLinks() {
        return null;
    }

    @Override
    public String getHref(LinkRelation rel) {
        return null;
    }

    @Override
    public String getHref(LinkRelation rel, String title) {
        return null;
    }

    @Override
    public boolean hasHref(LinkRelation rel) {
        return false;
    }

    @Override
    public String self() {
        return null;
    }
}
