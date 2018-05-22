/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.List;
import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.VirtualDocumentNode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonVirtualDocumentNode extends JsonInlineLinkableBase implements VirtualDocumentNode {
    @JsonProperty("are-children-compound")
    private boolean areChildrenCompound;
    @JsonProperty("available-versions")
    private List<String> availableVersions;
    @JsonProperty("binding")
    private String binding;
    @JsonProperty("can-be-removed")
    private boolean canBeRemoved;
    @JsonProperty("can-be-restructured")
    private boolean canBeRestructured;
    @JsonProperty("child-count")
    private int childCount;
    @JsonProperty("chronicle-id")
    private String chronicleId;
    @JsonProperty("copy-behavior")
    private int copyBehavior;
    @JsonProperty("follow-assembly")
    private boolean followAssembly;
    @JsonProperty("node-id")
    private String nodeId;
    @JsonProperty("is-binding-broken")
    private boolean isBindingBroken;
    @JsonProperty("is-compound")
    private boolean isCompound;
    @JsonProperty("is-from-assembly")
    private boolean isFromAssembly;
    @JsonProperty("is-structurally-modified")
    private boolean isStructurallyModified;
    @JsonProperty("is-virtual-document")
    private boolean isVirtualDocument;
    @JsonProperty("late-binding-value")
    private String lateBindingValue;
    @JsonProperty("override-late-binding")
    private boolean overrideLateBinding;
    @JsonProperty("vdm-number")
    private String vdmNumber;
    @JsonProperty("selected-object-name")
    private String selectedObjectName;
    @JsonProperty("selected-object-id")
    private String selectedObjectId;
    
    @Override
    public List<String> getAvailableVersions() {
        return availableVersions;
    }

    public void setAvailableVersions(List<String> availableVersions) {
        this.availableVersions = availableVersions;
    }

    @Override
    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    @Override
    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    @Override
    public String getChronicleId() {
        return chronicleId;
    }

    public void setChronicleId(String chronicleId) {
        this.chronicleId = chronicleId;
    }

    @Override
    public int getCopyBehavior() {
        return copyBehavior;
    }

    public void setCopyBehavior(int copyBehavior) {
        this.copyBehavior = copyBehavior;
    }

    @Override
    public boolean isFollowAssembly() {
        return followAssembly;
    }

    public void setFollowAssembly(boolean followAssembly) {
        this.followAssembly = followAssembly;
    }

    @Override
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String id) {
        this.nodeId = id;
    }

    @Override
    public String getLateBindingValue() {
        return lateBindingValue;
    }

    public void setLateBindingValue(String lateBindingValue) {
        this.lateBindingValue = lateBindingValue;
    }

    @Override
    public boolean isOverrideLateBinding() {
        return overrideLateBinding;
    }

    public void setOverrideLateBinding(boolean overrideLateBinding) {
        this.overrideLateBinding = overrideLateBinding;
    }

    @Override
    public String getVdmNumber() {
        return vdmNumber;
    }

    public void setVdmNumber(String vdmNumber) {
        this.vdmNumber = vdmNumber;
    }

    @Override
    public boolean isAreChildrenCompound() {
        return areChildrenCompound;
    }

    public void setAreChildrenCompound(boolean areChildrenCompound) {
        this.areChildrenCompound = areChildrenCompound;
    }

    @Override
    public boolean isCanBeRemoved() {
        return canBeRemoved;
    }

    public void setCanBeRemoved(boolean canBeRemoved) {
        this.canBeRemoved = canBeRemoved;
    }

    @Override
    public boolean isCanBeRestructured() {
        return canBeRestructured;
    }

    public void setCanBeRestructured(boolean canBeRestructured) {
        this.canBeRestructured = canBeRestructured;
    }

    @Override
    public boolean isBindingBroken() {
        return isBindingBroken;
    }

    public void setBindingBroken(boolean isBindingBroken) {
        this.isBindingBroken = isBindingBroken;
    }

    @Override
    public boolean isCompound() {
        return isCompound;
    }

    public void setCompound(boolean isCompound) {
        this.isCompound = isCompound;
    }

    @Override
    public boolean isFromAssembly() {
        return isFromAssembly;
    }

    public void setFromAssembly(boolean isFromAssembly) {
        this.isFromAssembly = isFromAssembly;
    }

    @Override
    public boolean isStructurallyModified() {
        return isStructurallyModified;
    }

    public void setStructurallyModified(boolean isStructurallyModified) {
        this.isStructurallyModified = isStructurallyModified;
    }

    @Override
    public boolean isVirtualDocument() {
        return isVirtualDocument;
    }

    public void setVirtualDocument(boolean isVirtualDocument) {
        this.isVirtualDocument = isVirtualDocument;
    }

    @Override
    public String getSelectedObjectName() {
        return selectedObjectName;
    }

    public void setSelectedObjectName(String selectedObjectName) {
        this.selectedObjectName = selectedObjectName;
    }

    @Override
    public String getSelectedObjectId() {
        return selectedObjectId;
    }

    public void setSelectedObjectId(String selectedObjectId) {
        this.selectedObjectId = selectedObjectId;
    }
    
    @Override
    public boolean equals(Object obj) {
        JsonVirtualDocumentNode that = (JsonVirtualDocumentNode) obj;
        return Equals.equal(areChildrenCompound, that.areChildrenCompound)
                && Equals.equal(availableVersions, that.availableVersions)
                && Equals.equal(binding, that.binding)
                && Equals.equal(canBeRemoved, that.canBeRemoved)
                && Equals.equal(canBeRestructured, that.canBeRestructured)
                && Equals.equal(childCount, that.childCount)
                && Equals.equal(chronicleId, that.chronicleId)
                && Equals.equal(copyBehavior, that.copyBehavior)
                && Equals.equal(followAssembly, that.followAssembly)
                && Equals.equal(nodeId, that.nodeId)
                && Equals.equal(isBindingBroken, that.isBindingBroken)
                && Equals.equal(isCompound, that.isCompound)
                && Equals.equal(isFromAssembly, that.isFromAssembly)
                && Equals.equal(isStructurallyModified, that.isStructurallyModified)
                && Equals.equal(isVirtualDocument, that.isVirtualDocument)
                && Equals.equal(lateBindingValue, that.lateBindingValue)
                && Equals.equal(overrideLateBinding, that.overrideLateBinding)
                && Equals.equal(vdmNumber, that.vdmNumber)
                && Equals.equal(selectedObjectName, that.selectedObjectName)
                && Equals.equal(selectedObjectId, that.selectedObjectId)
                && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, vdmNumber, selectedObjectId);
    }
}