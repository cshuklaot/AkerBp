/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.commons.util.rest.model;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRepository extends JsonInlineLinkableBase implements Repository {
	private int id;
	private String name;
	private String description;
	@JsonProperty
	private List<JsonServer> servers;

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Server> getServers() {
		return (List) servers;
	}

	public void setServers(List<JsonServer> servers) {
		this.servers = servers;
	}

	public static class JsonServer implements Repository.Server {
		private String name;
		private String host;
		private String version;
		private String docbroker;

		@Override
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		@Override
		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public String getDocbroker() {
			return docbroker;
		}

		public void setDocbroker(String docbroker) {
			this.docbroker = docbroker;
		}

		@Override
		public boolean equals(Object obj) {
			JsonServer that = (JsonServer) obj;
			return Equals.equal(name, that.name) && Equals.equal(host, that.host) && Equals.equal(version, that.version)
					&& Equals.equal(docbroker, that.docbroker);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, host, version, docbroker);
		}
	}

	@Override
	public boolean equals(Object obj) {
		JsonRepository that = (JsonRepository) obj;
		return Equals.equal(id, that.id) && Equals.equal(name, that.name) && Equals.equal(description, that.description)
				&& Equals.equal(servers, that.servers) && super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, servers, getSrc(), getContentType());
	}
}