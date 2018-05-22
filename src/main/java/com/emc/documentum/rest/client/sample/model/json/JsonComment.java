/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonComment extends JsonInlineLinkableBase implements Comment {
    @JsonProperty("object-id")
    private String objectId;
    @JsonProperty("comment-id")
    private String commentId;
    @JsonProperty("owner-name")
    private String ownerName;
    @JsonProperty("creation-date")
    private String creationDate;
    @JsonProperty("modify-date")
    private String modifyDate;
    @JsonProperty("content-value")
    private String contentValue;
    @JsonProperty("parent-id")
    private String parentId;
    @JsonProperty
    private String title;
    @JsonProperty("can-delete")
    private boolean canDelete;
    @JsonProperty("can-reply")
    private boolean canReply;
    
    public JsonComment() {
    }
    
    public JsonComment(Comment comment) {
        this.objectId = comment.getObjectId();
        this.commentId = comment.getCommentId();
        this.ownerName = comment.getOwnerName();
        this.creationDate = comment.getCreationDate();
        this.modifyDate = comment.getModifyDate();
        this.contentValue = comment.getContentValue();
        this.parentId = comment.getParentId();
        this.title = comment.getTitle();
        this.canDelete = comment.isCanDelete();
        this.canReply = comment.isCanReply();
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    public boolean isCanReply() {
        return canReply;
    }

    public void setCanReply(boolean canReply) {
        this.canReply = canReply;
    }

    @Override
    public boolean equals(Object obj) {
        JsonComment that = (JsonComment) obj;
        return Equals.equal(objectId, that.objectId)
                && Equals.equal(commentId, that.commentId)
                && Equals.equal(ownerName, that.ownerName)
                && Equals.equal(creationDate, that.creationDate)
                && Equals.equal(modifyDate, that.modifyDate)
                && Equals.equal(creationDate, that.creationDate)
                && Equals.equal(contentValue, that.contentValue)
                && Equals.equal(parentId, that.parentId)
                && Equals.equal(title, that.title)
                && Equals.equal(canDelete, that.canDelete)
                && Equals.equal(canReply, that.canReply)
                && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, commentId, ownerName, contentValue, parentId, title);
    }
}