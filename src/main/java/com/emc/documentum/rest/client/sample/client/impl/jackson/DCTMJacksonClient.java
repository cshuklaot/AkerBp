/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.client.impl.jackson;

import static com.ot.akbp.commons.util.rest.model.LinkRelation.ABOUT;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.ACLS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.ASPECT_TYPES;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.ASSIS_VALUES;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.ASSOCIATIONS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.BATCH_CAPABILITIES;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.CABINETS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.CANCEL_CHECKOUT;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.CHECKIN_BRANCH_VERSION;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.CHECKIN_NEXT_MAJOR;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.CHECKIN_NEXT_MINOR;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.CHECKOUT;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.CONTENTS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.DEMATERIALIZE;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.DOCUMENTS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.EDIT;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.FOLDERS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.FORMATS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.GROUPS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.MATERIALIZE;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.NETWORK_LOCATIONS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.OBJECTS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.OBJECT_ASPECTS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.PRIMARY_CONTENT;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.RELATIONS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.RELATION_TYPES;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.REPOSITORIES;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.SELF;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.SHARED_PARENT;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.TYPES;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.USERS;
import static com.ot.akbp.commons.util.rest.model.LinkRelation.VERSIONS;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.http.HttpMethod;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.emc.documentum.rest.client.batch.Batch;
import com.emc.documentum.rest.client.batch.Capabilities;
import com.emc.documentum.rest.client.sample.client.impl.AbstractRestTemplateClient;
import com.emc.documentum.rest.client.sample.client.util.Headers;
import com.emc.documentum.rest.client.sample.client.util.UriHelper;
import com.emc.documentum.rest.client.sample.model.json.JsonBatch;
import com.emc.documentum.rest.client.sample.model.json.JsonBatchCapabilities;
import com.emc.documentum.rest.client.sample.model.json.JsonFeeds;
import com.emc.documentum.rest.client.sample.model.json.JsonFolderLink;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ot.akbp.commons.util.rest.model.Feed;
import com.ot.akbp.commons.util.rest.model.FolderLink;
import com.ot.akbp.commons.util.rest.model.HomeDocument;
import com.ot.akbp.commons.util.rest.model.JsonHomeDocument;
import com.ot.akbp.commons.util.rest.model.JsonObject;
import com.ot.akbp.commons.util.rest.model.JsonRepository;
import com.ot.akbp.commons.util.rest.model.LinkRelation;
import com.ot.akbp.commons.util.rest.model.Linkable;
import com.ot.akbp.commons.util.rest.model.Repository;
import com.ot.akbp.commons.util.rest.model.RestObject;
import com.ot.akbp.commons.util.rest.model.RestType;
import com.ot.d2rest.client.DCTMRestClient;

/**
 * the DCTMRestClient implementation by Jackson json support
 */
@NotThreadSafe
public class DCTMJacksonClient extends AbstractRestTemplateClient implements DCTMRestClient, Cloneable {
	public DCTMJacksonClient(String contextRoot, String repositoryName, String username, String password,
			boolean useFormatExtension) {
		super(contextRoot, repositoryName, username, password, useFormatExtension);
	}

	public DCTMJacksonClient(String contextRoot, String repositoryName, String username, String password,
			boolean useFormatExtension, boolean ignoreSslWarning) {
		super(contextRoot, repositoryName, username, password, useFormatExtension, ignoreSslWarning);
	}

	@Override
	public DCTMJacksonClient clone() {
		return clone(new DCTMJacksonClient(contextRoot, repositoryName, username, password, useFormatExtension,
				ignoreSslWarning));
	}

	@Override
	public HomeDocument getHomeDocument() {
		if (homeDocument == null) {
			homeDocument = get(getHomeDocumentUri(), Headers.ACCEPT_JSON_HOME_DOCUMENT, JsonHomeDocument.class);
		}
		return homeDocument;
	}

	@Override
	public RestObject getProductInfo() {
		if (productInfo == null) {
			productInfo = get(getHomeDocument().getHref(ABOUT), false, JsonObject.class);
		}
		return productInfo;
	}

	@Override
	public Feed<Repository> getRepositories() {
		if (repositories == null) {
			repositories = feed(getHomeDocument(), REPOSITORIES, JsonFeeds.RepositoryFeed.class);
		}
		return repositories;
	}

	@Override
	public Repository getRepository() {
		return getRepository(JsonRepository.class);
	}

	@Override
	public Feed<RestObject> dql(String dql, String... params) {
		return objectFeed(SELF, UriHelper.append(params, "dql", dql));
	}

	@SuppressWarnings("unchecked")

	@Override
	public Feed<RestObject> getCabinets(String... params) {
		return objectFeed(CABINETS, params);
	}

	@Override
	public RestObject getCabinet(String cabinet, String... params) {
		return getCabinet(cabinet, JsonObject.class, params);
	}

	@Override
	public RestObject createCabinet(RestObject cabinetToCreate) {
		return post(getRepository().getHref(CABINETS), new JsonObject(cabinetToCreate), JsonObject.class);
	}

	@Override
	public Feed<RestObject> getFolders(Linkable parent, String... params) {
		return objectFeed(parent, FOLDERS, params);
	}

	@Override
	public Feed<RestObject> getObjects(Linkable parent, String... params) {
		return objectFeed(parent, OBJECTS, params);
	}

	@Override
	public Feed<RestObject> getDocuments(Linkable parent, String... params) {
		return objectFeed(parent, DOCUMENTS, params);
	}

	@Override
	public RestObject createFolder(Linkable parent, RestObject newFolder, String... params) {
		return post(parent.getHref(FOLDERS), new JsonObject(newFolder), JsonObject.class, params);
	}

	@Override
	public RestObject getFolder(String folderUri, String... params) {
		return get(folderUri, false, JsonObject.class, params);
	}

	@Override
	public RestObject createObject(Linkable parent, LinkRelation rel, RestObject objectToCreate, Object content,
			String contentMediaType, String... params) {
		return post(parent.getHref(rel), new JsonObject(objectToCreate), content, contentMediaType, JsonObject.class,
				params);
	}

	@Override
	public RestObject createObject(Linkable parent, LinkRelation rel, RestObject objectToCreate, List<Object> contents,
			List<String> contentMediaTypes, String... params) {
		return post(parent.getHref(rel), new JsonObject(objectToCreate), contents, contentMediaTypes, JsonObject.class,
				params);
	}

	@Override
	public RestObject getObject(String objectUri, String... params) {
		return get(objectUri, false, JsonObject.class, params);
	}

	@Override
	public RestObject createDocument(Linkable parent, RestObject objectToCreate, Object content,
			String contentMediaType, String... params) {
		return post(parent.getHref(DOCUMENTS), new JsonObject(objectToCreate), content, contentMediaType,
				JsonObject.class, params);
	}

	@Override
	public RestObject createDocument(Linkable parent, RestObject objectToCreate, List<Object> contents,
			List<String> contentMediaTypes, String... params) {
		return post(parent.getHref(DOCUMENTS), new JsonObject(objectToCreate), contents, contentMediaTypes,
				JsonObject.class, params);
	}

	@Override
	public RestObject getDocument(String documentUri, String... params) {
		return get(documentUri, false, JsonObject.class, params);
	}

	@Override
	public RestObject update(RestObject oldObject, RestObject newObject, String... params) {
		return update(oldObject, EDIT, newObject, HttpMethod.POST, params);
	}

	@Override
	public RestObject createContent(RestObject object, Object content, String mediaType, String... params) {
		return post(object.getHref(CONTENTS), content, mediaType, JsonObject.class, params);
	}

	@Override
	public RestObject getPrimaryContent(RestObject object, String... params) {
		return getContent(object.getHref(PRIMARY_CONTENT), params);
	}

	@Override
	public RestObject getContent(String contentUri, String... params) {
		return get(contentUri, false, JsonObject.class, params);
	}

	@Override
	public Feed<RestObject> getContents(RestObject object, String... params) {
		return objectFeed(object, CONTENTS, params);
	}

	@Override
	public RestObject checkout(RestObject object, String... params) {
		return put(object.getHref(CHECKOUT), JsonObject.class, params);
	}

	@Override
	public void cancelCheckout(RestObject object) {
		delete(object.getHref(CANCEL_CHECKOUT));
	}

	@Override
	public RestObject checkinNextMajor(RestObject oldObject, RestObject newObject, String... params) {
		return post(oldObject.getHref(CHECKIN_NEXT_MAJOR), new JsonObject(newObject), JsonObject.class, params);
	}

	@Override
	public RestObject checkinNextMajor(RestObject oldObject, RestObject newObject, Object content,
			String contentMediaType, String... params) {
		return post(oldObject.getHref(CHECKIN_NEXT_MAJOR), newObject == null ? null : new JsonObject(newObject),
				content, contentMediaType, JsonObject.class, params);
	}

	@Override
	public RestObject checkinNextMajor(RestObject oldObject, RestObject newObject, List<Object> contents,
			List<String> contentMediaTypes, String... params) {
		return post(oldObject.getHref(CHECKIN_NEXT_MAJOR), newObject == null ? null : new JsonObject(newObject),
				contents, contentMediaTypes, JsonObject.class, params);
	}

	@Override
	public RestObject checkinNextMinor(RestObject oldObject, RestObject newObject, String... params) {
		return post(oldObject.getHref(CHECKIN_NEXT_MINOR), new JsonObject(newObject), JsonObject.class, params);
	}

	@Override
	public RestObject checkinNextMinor(RestObject oldObject, RestObject newObject, Object content,
			String contentMediaType, String... params) {
		return post(oldObject.getHref(CHECKIN_NEXT_MINOR), newObject == null ? null : new JsonObject(newObject),
				content, contentMediaType, JsonObject.class, params);
	}

	@Override
	public RestObject checkinNextMinor(RestObject oldObject, RestObject newObject, List<Object> contents,
			List<String> contentMediaTypes, String... params) {
		return post(oldObject.getHref(CHECKIN_NEXT_MINOR), newObject == null ? null : new JsonObject(newObject),
				contents, contentMediaTypes, JsonObject.class, params);
	}

	@Override
	public RestObject checkinBranch(RestObject oldObject, RestObject newObject, String... params) {
		return post(oldObject.getHref(CHECKIN_BRANCH_VERSION), new JsonObject(newObject), JsonObject.class, params);
	}

	@Override
	public RestObject checkinBranch(RestObject oldObject, RestObject newObject, Object content, String contentMediaType,
			String... params) {
		return post(oldObject.getHref(CHECKIN_BRANCH_VERSION), newObject == null ? null : new JsonObject(newObject),
				content, contentMediaType, JsonObject.class, params);
	}

	@Override
	public RestObject checkinBranch(RestObject oldObject, RestObject newObject, List<Object> contents,
			List<String> contentMediaTypes, String... params) {
		return post(oldObject.getHref(CHECKIN_BRANCH_VERSION), newObject == null ? null : new JsonObject(newObject),
				contents, contentMediaTypes, JsonObject.class, params);
	}

	@Override
	public Feed<RestObject> getVersions(RestObject object, String... params) {
		return objectFeed(object, VERSIONS, params);
	}

	@Override
	public RestObject materialize(RestObject oldObject) {
		return put(oldObject.getHref(MATERIALIZE), JsonObject.class);
	}

	@Override
	public void dematerialize(RestObject oldObject) {
		delete(oldObject.getHref(DEMATERIALIZE));
	}

	@Override
	public RestObject reparent(RestObject oldObject, RestObject newParent) {
		return post(oldObject.getHref(SHARED_PARENT), new JsonObject(newParent.getHref(SELF)), JsonObject.class);
	}

	@Override
	public RestType getType(String name, String... params) {
		if (getMajorVersion() > 7.1) {
			return get(getRepository().getHref(TYPES) + "/" + name, false, JsonType.class, params);
		} else {
			JsonType71 type71 = get(getRepository().getHref(TYPES) + "/" + name, false, JsonType71.class, params);
			return type71 == null ? null : type71.getType();
		}
	}

	@Override
	public Feed<RestType> getTypes(String... params) {
		return feed(TYPES, JsonFeeds.TypeFeed.class, params);
	}

	@Override
	public Feed<RestObject> getAspectTypes(String... params) {
		return objectFeed(ASPECT_TYPES, params);
	}

	@Override
	public RestObject getAspectType(String aspectType, String... params) {
		return get(getRepository().getHref(ASPECT_TYPES) + "/" + aspectType, false, JsonObject.class, params);
	}

	@Override
	public ValueAssistant getValueAssistant(RestType type, ValueAssistantRequest request, String... params) {
		return post(type.getHref(ASSIS_VALUES), new JsonValueAssistantRequest(request), JsonValueAssistance.class,
				params);
	}

	@Override
	public ObjectAspects attach(RestObject object, String... aspects) {
		return post(object.getHref(OBJECT_ASPECTS), new JsonObjectAspects(aspects), JsonObjectAspects.class);
	}

	@Override
	public Feed<RestObject> getUsers(String... params) {
		return getUsers(getRepository(), params);
	}

	@Override
	public Feed<RestObject> getUsers(Linkable parent, String... params) {
		return objectFeed(parent, USERS, params);
	}

	@Override
	public Feed<RestObject> getGroups(String... params) {
		return getGroups(getRepository(), params);
	}

	@Override
	public Feed<RestObject> getGroups(Linkable parent, String... params) {
		return objectFeed(parent, GROUPS, params);
	}

	@Override
	public RestObject getCurrentUser(String... params) {
		return get(getRepository().getHref(LinkRelation.CURRENT_USER), false, JsonObject.class, params);
	}

	@Override
	public RestObject getDefaultFolder(String... params) {
		return get(getCurrentUser().getHref(LinkRelation.DEFAULT_FOLDER), false, JsonObject.class, params);
	}

	@Override
	public RestObject getUser(String userUri, String... params) {
		return get(userUri, false, JsonObject.class, params);
	}

	@Override
	public RestObject getGroup(String groupUri, String... params) {
		return get(groupUri, false, JsonObject.class, params);
	}

	@Override
	public RestObject createUser(RestObject userToCreate) {
		return post(getRepository().getHref(USERS), new JsonObject(userToCreate), JsonObject.class);
	}

	@Override
	public RestObject createGroup(RestObject groupToCreate) {
		return post(getRepository().getHref(GROUPS), new JsonObject(groupToCreate), JsonObject.class);
	}

	@Override
	public void addUserToGroup(RestObject group, RestObject user) {
		post(group.getHref(USERS), new JsonObject(user.getHref(SELF)), null);
	}

	@Override
	public void addGroupToGroup(RestObject group, RestObject subGroup) {
		post(group.getHref(GROUPS), new JsonObject(subGroup.getHref(SELF)), null);
	}

	@Override
	public Feed<RestObject> getRelationTypes(String... params) {
		return objectFeed(RELATION_TYPES, params);
	}

	@Override
	public RestObject getRelationType(String uri, String... params) {
		return get(uri, false, JsonObject.class, params);
	}

	@Override
	public Feed<RestObject> getRelations(String... params) {
		return objectFeed(RELATIONS, params);
	}

	@Override
	public RestObject getRelation(String uri, String... params) {
		return get(uri, false, JsonObject.class, params);
	}

	@Override
	public RestObject createRelation(RestObject object) {
		return post(getRepository().getHref(RELATIONS), new JsonObject(object), JsonObject.class);
	}

	@Override
	public Feed<RestObject> getFormats(String... params) {
		return objectFeed(FORMATS, params);
	}

	@Override
	public RestObject getFormat(String uri, String... params) {
		return get(uri, false, JsonObject.class, params);
	}

	@Override
	public Feed<RestObject> getNetworkLocations(String... params) {
		return objectFeed(NETWORK_LOCATIONS, params);
	}

	@Override
	public RestObject getNetworkLocation(String uri, String... params) {
		return get(uri, false, JsonObject.class, params);
	}

	@Override
	public Feed<FolderLink> getFolderLinks(Linkable object, LinkRelation rel, String... params) {
		return feed(object, rel, JsonFeeds.FolderLinkFeed.class, params);
	}

	@Override
	public FolderLink getFolderLink(String uri, String... params) {
		return get(uri, false, JsonFolderLink.class, params);
	}

	@Override
	public FolderLink move(FolderLink oldLink, FolderLink newLink, String... params) {
		return put(oldLink.getHref(SELF), new JsonFolderLink(newLink), JsonFolderLink.class, params);
	}

	@Override
	public FolderLink link(Linkable object, LinkRelation rel, FolderLink link) {
		return post(object.getHref(rel), new JsonFolderLink(link), JsonFolderLink.class);
	}

	@Override
	public Feed<RestObject> getAcls(String... params) {
		return objectFeed(ACLS, params);
	}

	@Override
	public Feed<RestObject> getAclAssociations(Linkable acl, String... params) {
		return objectFeed(acl, ASSOCIATIONS, params);
	}

	@Override
	public RestObject getAcl(String uri, String... params) {
		return get(uri, false, JsonObject.class, params);
	}

	@Override
	public RestObject createAcl(RestObject object) {
		return post(getRepository().getHref(ACLS), new JsonObject(object), JsonObject.class);
	}

	@Override
	public Capabilities getBatchCapabilities() {
		return get(getRepository().getHref(BATCH_CAPABILITIES), false, JsonBatchCapabilities.class);
	}

	@Override
	public Batch createBatch(Batch batch) {
		return post(batch, JsonBatch.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initRestTemplate(RestTemplate restTemplate) {
		super.initRestTemplate(restTemplate);
		restTemplate.setErrorHandler(new DCTMJacksonErrorHandler(restTemplate.getMessageConverters()));
		for (HttpMessageConverter<?> c : restTemplate.getMessageConverters()) {
			if (c instanceof MappingJackson2HttpMessageConverter) {
				((MappingJackson2HttpMessageConverter) c).getObjectMapper()
						.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			} else if (c instanceof FormHttpMessageConverter) {
				try {
					Field pcField = FormHttpMessageConverter.class.getDeclaredField("partConverters");
					pcField.setAccessible(true);
					List<HttpMessageConverter<?>> partConverters = ((List<HttpMessageConverter<?>>) pcField.get(c));
					for (HttpMessageConverter<?> pc : partConverters) {
						if (pc instanceof MappingJackson2HttpMessageConverter) {
							((MappingJackson2HttpMessageConverter) pc).getObjectMapper()
									.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
							break;
						}
					}
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Feed<RestObject> objectFeed(Linkable parent, LinkRelation rel, String... params) {
		return feed(parent, rel, JsonFeeds.ObjectFeed.class, params);
	}

	@SuppressWarnings("unchecked")
	private Feed<RestObject> objectFeed(LinkRelation rel, String... params) {
		return feed(rel, JsonFeeds.ObjectFeed.class, params);
	}

	@Override
	public void serialize(Object object, OutputStream os) {
		try {
			DCTMJacksonMapper.marshal(os, object);
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public ClientType getClientType() {
		return ClientType.JSON;
	}

}
