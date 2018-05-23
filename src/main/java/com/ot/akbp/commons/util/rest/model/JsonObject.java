/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonObject extends JsonInlineLinkableBase implements RestObject {
	private String name;
	private String type;
	private String definition;
	private Map<String, Object> properties;
	private String href;

	public JsonObject() {
	}

	public JsonObject(String href) {
		this.href = href;
	}

	public JsonObject(RestObject object) {
		this.name = object.getName();
		this.type = object.getType();
		this.definition = object.getDefinition();
		this.properties = object.getProperties();
		this.href = object.getHref();

	}

	@Override
	@JsonIgnore
	public String getObjectId() {
		return properties == null ? null : (String) properties.get("r_object_id");
	}

	@Override
	@JsonIgnore
	public String getObjectName() {
		return properties == null ? null : (String) properties.get("object_name");
	}

	@Override
	@JsonIgnore
	public String getObjectType() {
		return properties == null ? null : (String) properties.get("r_object_type");
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Override
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	@Override
	public String getPropertiesType() {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		JsonObject that = (JsonObject) obj;
		return Equals.equal(name, that.name) && Equals.equal(type, that.type)
				&& Equals.equal(definition, that.definition) && Equals.equal(properties, that.properties)
				&& Equals.equal(href, that.href) && super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, definition, properties, href, getContentType(), getSrc());
	}
}
