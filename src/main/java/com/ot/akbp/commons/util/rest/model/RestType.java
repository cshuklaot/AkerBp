/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.List;

/**
 * the class represents the type of the REST services
 */
public interface RestType extends Linkable {
	/**
	 * @return type name
	 */
	String getName();

	/**
	 * @return type label
	 */
	String getLabel();

	/**
	 * @return type parent type name
	 */
	String getParent();

	/**
	 * @return lightweight type shared parent type name
	 */
	String getSharedParent();

	/**
	 * @return type category
	 */
	String getCategory();

	/**
	 * @return the type help text
	 */
	public String getHelpText();

	/**
	 * @return the type comment text
	 */
	public String getCommentText();

	/**
	 * @return the default lifecycle id
	 */
	public String getDefaultLifecycle();

	/**
	 * @return the default lifecycle version
	 */
	public String getDefaultLifecycleVersion();

	/**
	 * @return the list of the auditable system events
	 */
	public List<String> getAuditableSystemEvents();

	/**
	 * @return the list of the auditable application events
	 */
	public List<String> getAuditableAppEvents();

}
