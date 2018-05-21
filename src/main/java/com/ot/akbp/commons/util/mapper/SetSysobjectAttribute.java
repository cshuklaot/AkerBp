package com.ot.akbp.commons.util.mapper;

import org.w3c.dom.Node;

import com.documentum.fc.client.IDfSysObject;

/**
 * An interface for objects that set sysobject attributes. The job of derived classes is to
 * translate strings and store them.
 */
public interface SetSysobjectAttribute {

    /**
     * Set a repeating attribute value.
     *
     * @param object
     *            The target object.
     * @param attrName
     *            The name of the target attribute.
     * @param attrIndex
     *            The position of the value.
     * @param valueNode
     *            xml node containing the value.
     */
    void set(IDfSysObject object, final String attrName, int attrIndex, final Node valueNode) throws Exception;

    /**
     * @return name of attribute to set.
     */
    String getAttributeName();

}