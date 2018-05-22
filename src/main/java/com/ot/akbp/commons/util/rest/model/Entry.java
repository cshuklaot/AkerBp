/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.List;

/**
 * the class represents the entry of a feed
 */
public interface Entry<T extends Linkable> extends Linkable {

	/**
	 * @return entry id
	 */
	String getId();

	/**
	 * @return entry title
	 */
	String getTitle();

	/**
	 * @return entry updated time
	 */
	String getUpdated();

	/**
	 * @return entry summary
	 */
	String getSummary();

	/**
	 * @return entry authors
	 */
	List<Author> getAuthors();

	/**
	 * for entry without inline content
	 * 
	 * @return the content source uri
	 */
	String getContentSrc();

	/**
	 * for entry without inline content
	 * 
	 * @return the content type of the source uri
	 */
	String getContentType();

	/**
	 * @return entry publish time
	 */
	String getPublished();

	/**
	 * for entry with inline content
	 * 
	 * @return the T object embedded in the entry
	 */
	T getContentObject();
}
