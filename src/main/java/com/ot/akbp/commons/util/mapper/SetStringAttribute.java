package com.ot.akbp.commons.util.mapper;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.documentum.fc.client.IDfSysObject;
import com.spr.ajwf.commons.util.StringHelper;

/**
 * Set string values as they are in XML ignoring nested XML markup. CDATA sections and supplemental
 * characters are preserved.
 */
public class SetStringAttribute extends AbstractSetAttribute {

    /**
     * Constructor.
     *
     * @param attributeName
     *            The name of attribute to set.
     */
    public SetStringAttribute(final String attributeName) {
        super(attributeName);
    }

    /**
     * CTOR.
     *
     * @param attributeName
     *            -- What it says.
     * @param updateOnlyIfEmpty
     *            -- Skip update if the attribute has a value.
     */
    public SetStringAttribute(final String attributeName, final boolean updateOnlyIfEmpty) {
        super(attributeName);
    }

    @Override
    public void setValue(
            final IDfSysObject object,
            final String attrName,
            final int attrIndex,
            final Node valueNode) throws Exception {
        final String newValue = getStringValue(valueNode, object);
        object.setRepeatingString(attrName, attrIndex, newValue);
    }

    /**
     * @param valueNode
     *            the XML node containing value.
     * @param object
     *            sysobject.
     * @return extracted string value.
     * @throws Exception
     */
    protected String getStringValue(final Node valueNode, final IDfSysObject object) throws Exception {
        String textValue = getStringFromNode(valueNode);
        if (textValue != null) {
            textValue = StringHelper.convertSupplementalCharsToEntities(textValue);
        }
        return textValue;
    }

    /**
     * @param valueNode
     *            node containing text.
     * @return text representation of the node, nested elements markup ignored (node.getTextContent
     *         is used).
     */
    private String getStringFromNode(final Node valueNode) {
        final NodeList nodes = valueNode.getChildNodes();
        final int nodesLenght = nodes.getLength();
        if (nodesLenght == 0) {
            return valueNode.getTextContent();
        }
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            final int nodeType = node.getNodeType();
            switch (nodeType) {
                case Node.CDATA_SECTION_NODE:
                    final CDATASection section = (CDATASection) node;
                    builder.append("<![CDATA[").append(section.getData()).append("]]>");
                    break;
                default:
                    builder.append(node.getTextContent());
            }
        }
        return builder.toString();
    }
}