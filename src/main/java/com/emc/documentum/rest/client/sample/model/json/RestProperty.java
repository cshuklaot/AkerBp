/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;

import com.ot.akbp.commons.util.rest.model.RestTypeMappingTable;

public interface RestProperty {
	public String getName();

	public String getType();

	public String getLabel();

	public int getLength();

	public boolean isRepeating();

	public boolean isHidden();

	public boolean isRequired();

	public boolean isNotNull();

	public boolean isReadOnly();

	public boolean isSearchable();

	public int getDisplayHint();

	public List<DefaultValue> getDefault();

	public List<String> getDependencies();

	public String getNotNullEnforce();

	public String getNotNullMessage();

	public List<RestTypeMappingTable> getMappingTables();

	public boolean isIgnoreConstraints();

	public boolean isIgnoreImmutable();

	public String getHelpText();

	public String getCommentText();

	public String getCategory();

	public interface DefaultValue {
		public Object getExpression();

		public boolean isExpression();
	}
}
