/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class LinkableBase implements Linkable {
    @XmlTransient
    @JsonIgnore
    private MultiValueMap<String, Link> linksMap;
    
    @Override
    public String getHref(LinkRelation rel) {
        return getValue(rel, null);
    }
    
    @Override
    public String getHref(LinkRelation rel, String title) {
        return getValue(rel, title);
    }

    @Override
    public String self() {
        return getHref(LinkRelation.SELF);
    }

    @Override
    public boolean hasHref(LinkRelation rel) {
        return getHref(rel) != null;
    }

    private String getValue(LinkRelation rel, String title) {
        if(linksMap == null && getLinks() != null) {
            linksMap = new LinkedMultiValueMap<String, Link>();
            for(Link l : getLinks()) {
                linksMap.add(l.getRel(), l);
            }
        }
        String href = null;
        if(linksMap != null) {
            List<Link> list = linksMap.get(rel.rel());
            if(list != null) {
                Link link = null;
                if(title == null) {
                    link = list.get(0);
                } else {
                    for(Link lk : list) {
                        if(title.equals(lk.getTitle())) {
                            link = lk;
                            break;
                        }
                    }
                }
                if(link != null) {
                    href = link.isTemplate()?trimHrefTemplate(link.getHreftemplate()):link.getHref();
                }
            }
        }
        return href;
    }
    
    private static String trimHrefTemplate(String template) {
        return template.replaceFirst("\\{.+\\}$", "");
    }
}
