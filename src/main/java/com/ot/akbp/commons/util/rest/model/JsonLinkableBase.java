package com.ot.akbp.commons.util.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class JsonLinkableBase extends LinkableBase {
	@JsonProperty
	protected List<JsonLink> links;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Link> getLinks() {
		return (List) links;
	}

	public void setLinks(List<JsonLink> links) {
		this.links = links;
	}
}
