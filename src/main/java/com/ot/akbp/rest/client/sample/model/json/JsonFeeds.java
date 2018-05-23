/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.model.json;

import com.ot.akbp.commons.util.rest.model.JsonObject;
import com.ot.akbp.commons.util.rest.model.JsonRepository;
import com.ot.akbp.commons.util.rest.model.JsonType71;

public class JsonFeeds {
	public static class ObjectFeed extends JsonFeed<JsonObject> {
	}

	public static class RepositoryFeed extends JsonFeed<JsonRepository> {
	}

	public static class FolderLinkFeed extends JsonFeed<JsonFolderLink> {
	}

	public static class TypeFeed extends JsonFeed<JsonType> {
	}

	public static class TypeFeed71 extends JsonFeed<JsonType71> {
	}
}
