/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonTypeMappingTable implements com.ot.akbp.commons.util.rest.model.RestTypeMappingTable {
	private String value;
	private String display;
	private String description;

	public void setValue(String value) {
		this.value = value;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getDisplay() {
		return display;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj) {
		JsonTypeMappingTable that = (JsonTypeMappingTable) obj;
		return Equals.equal(value, that.value) && Equals.equal(display, that.display)
				&& Equals.equal(description, that.description);
	}
}