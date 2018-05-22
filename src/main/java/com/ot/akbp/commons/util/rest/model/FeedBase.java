/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.List;

/**
 * the class represents a feed collection
 */
public interface FeedBase<T extends Linkable, E extends Entry<T>> extends Linkable {
    /**
     * @return feed id
     */
    String getId();
    
    /**
     * @return feed title
     */
    String getTitle();
    
    /**
     * @return feed updated time
     */
    String getUpdated();
    
    /**
     * @return feed authors
     */
    List<Author> getAuthors();
    
    /**
     * @return feed summary
     */
    String getSummary();

    /**
     * @return Entry list of the feed
     */
    List<E> getEntries();
    
    /**
     * @return total count of the request feed, including all page of feed
     */
    Integer getTotal();
    
    /**
     * @return current page
     */
    Integer getPage();
    
    /**
     * @return items per page
     */
    Integer getItemsPerPage();
}
