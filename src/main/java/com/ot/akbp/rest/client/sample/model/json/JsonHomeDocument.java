/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ot.akbp.commons.util.rest.model.HomeDocument;
import com.ot.akbp.commons.util.rest.model.JsonLink;
import com.ot.akbp.commons.util.rest.model.Link;
import com.ot.akbp.commons.util.rest.model.LinkableBase;
import com.ot.akbp.rest.client.sample.client.util.Equals;

public class JsonHomeDocument extends LinkableBase implements HomeDocument {
	private Map<String, Map<String, Object>> resources;

	public Map<String, Map<String, Object>> getResources() {
		return resources;
	}

	public void setResources(Map<String, Map<String, Object>> resources) {
		this.resources = resources;
	}

	@Override
	public boolean equals(Object obj) {
		JsonHomeDocument that = (JsonHomeDocument) obj;
		return Equals.equal(resources, that.resources);
	}

	@Override
	public int hashCode() {
		return Objects.hash(resources);
	}

	@Override
	public List<Link> getLinks() {
		List<Link> links = null;
		if (resources != null) {
			links = new ArrayList<Link>(resources.size());
			for (Map.Entry<String, Map<String, Object>> entry : resources.entrySet()) {
				JsonLink l = new JsonLink();
				l.setHref((String) entry.getValue().get("href"));
				l.setRel(entry.getKey());
				links.add(l);
			}
		}
		return links;
	}
}
