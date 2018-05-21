package com.ot.akbp.commons.util.mapper;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.documentum.fc.client.IDfSysObject;

/**
 * Abstract implementation of {@link SetSysobjectAttribute}.
 *

 */
public abstract class AbstractSetAttribute implements SetSysobjectAttribute {

    /**
     * Constructor.
     *
     * @param attributeName
     *            The name of attriute to set.
     */
    public AbstractSetAttribute(final String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public void set(
            final IDfSysObject object,
            final String attrName,
            final int attrIndex,
            final Node valueNode) throws Exception {
        if (containProcessingInstructions(valueNode)) {
            return;
        }
        setValue(object, attrName, attrIndex, valueNode);
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * set value.
     *
     * @param object
     *            object
     * @param attrName
     *            attribute name
     * @param attrIndex
     *            index for repeating attribute, 0 for single
     * @param valueNode
     *            node which contains value.
     * @throws Exception
     */
    protected abstract void setValue(
            IDfSysObject object,
            String attrName,
            int attrIndex,
            Node valueNode) throws Exception;

    /**
     * Check recursively for processing instructions.
     *
     * @param valueNode
     *            node to check.
     * @return <code>true</code> if there is at least one child processing instruction node.
     *         Recursively traverse all children node.
     */
    protected boolean containProcessingInstructions(final Node valueNode) {
        if (Node.PROCESSING_INSTRUCTION_NODE == valueNode.getNodeType()) {
            return true;
        }
        final NodeList nodeList = valueNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            final boolean hasProcessingInstr = containProcessingInstructions(nodeList.item(i));
            if (hasProcessingInstr) {
                return true;
            }
        }
        return false;
    }

    private final String attributeName;

}
