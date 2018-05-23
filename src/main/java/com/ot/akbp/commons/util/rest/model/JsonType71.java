/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.json.JsonType;

public class JsonType71 extends JsonType {
	private JsonType type;

	public JsonType getType() {
		return type;
	}

	public void setType(JsonType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		JsonType71 that = (JsonType71) obj;
		return Equals.equal(type, that.type) && super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type == null ? 0 : type.getName());
	}
}
