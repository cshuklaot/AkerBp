/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import com.ot.akbp.commons.util.rest.model.JsonObject;
import com.ot.akbp.commons.util.rest.model.JsonRepository;

public class JsonFeeds {
	public static class ObjectFeed extends JsonFeed<JsonObject> {
	}

	public static class RepositoryFeed extends JsonFeed<JsonRepository> {
	}

	public static class FolderLinkFeed extends JsonFeed<JsonFolderLink> {
	}

	public static class SearchFeed extends JsonSearchFeed<JsonObject> {
	}

}
