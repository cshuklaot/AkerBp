package com.ot.akbp.commons.util.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.TransformerException;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ot.akbp.commons.util.CollectionHelper;
import com.ot.akbp.commons.util.xml.DOMHelper;

/**
 * Maps values from a DOM node to an <code>IDfSysObject</code> instance using
 * specific setters.
 *
 */
public class NodeToSysObjectMapper {

	/**
	 * Constructor for mapping of xpaths to attributes.
	 *
	 * @param xpathsAndSetters
	 *            mappings.
	 */
	@SafeVarargs
	public NodeToSysObjectMapper(final com.ot.akbp.commons.util.mapper.Pair<String, String>... xpathsAndSetters) {
		this.setAttributeMap = new LinkedHashMap<String, String>();
		CollectionHelper.putToMap(this.setAttributeMap, xpathsAndSetters);
	}

	/**
	 * Construct from another mapper.
	 *
	 * @param mapper
	 *            mapper.
	 */
	public NodeToSysObjectMapper(final NodeToSysObjectMapper mapper) {
		this.setAttributeMap = mapper.setAttributeMap;
	}

	/**
	 * Construct from another mapper.
	 *
	 * @param mapper
	 *            mapper.
	 */
	public NodeToSysObjectMapper(final NodeToSysObjectMapper... mappers) {
		this.setAttributeMap = new LinkedHashMap<String, String>();
		for (final NodeToSysObjectMapper mapper : mappers) {
			this.setAttributeMap.putAll(mapper.setAttributeMap);
		}
	}

	/**
	 * Override setter for given xpath.
	 *
	 * @param xpath
	 *            xpath.
	 * @param setter
	 *            setter.
	 */
	public void override(final String xpath, final String setter) {
		setAttributeMap.put(xpath, setter);
	}

	/**
	 * Executes the XPath expressions and maps the values to the corresponding
	 * sysobject attributes.
	 *
	 * @param node
	 *            The context node for the XPath expressions.
	 * @param object
	 *            The sysobject.
	 * @throws TransformerException
	 *             if one of the XPath expressions is invalid.
	 */
	public void process(final DOMHelper node, final Map<String, Object> object) throws TransformerException, Exception {
		if (node == null) {
			return;
		}
		for (final Entry<String, String> entry : setAttributeMap.entrySet()) {
			final String xPath = entry.getKey();
			final String attrName = entry.getValue();
			NodeList selectNodeList = node.selectNodeList(xPath);
			if (selectNodeList.getLength() > 1) {
				mapRepeatingAttribute(attrName, selectNodeList, object);
			} else {
				mapSingleAttribute(attrName, node.selectSingleNode(xPath), object);
			}
		}
	}

	/**
	 * Map the values of one repeating attribute.
	 *
	 * @param attrName
	 *            The attribute name.
	 * @param valueNodes
	 *            The nodes that contain the values.
	 * @param object
	 *            The target object.
	 * @param setter
	 *            The setter
	 */
	private void mapRepeatingAttribute(final String attrName, final NodeList valueNodes,
			final Map<String, Object> propertyMap) throws Exception {
		final int nValues = valueNodes.getLength();
		List<String> valueList = new ArrayList<>();
		for (int i = 0; i < nValues; ++i) {
			Node valueNode = valueNodes.item(i);
			valueList.add(getStringFromNode(valueNode));
		}
		propertyMap.put(attrName, valueList);

	}

	/**
	 * Map the value of one single attribute.
	 *
	 * @param attrName
	 *            The attribute name.
	 * @param valueNode
	 *            The node that contains the values.
	 * @param object
	 *            The target object.
	 * @param setter
	 *            The setter
	 */
	private void mapSingleAttribute(final String attrName, final Node valueNode, final Map<String, Object> propertyMap)
			throws Exception {
		if (valueNode != null) {
			propertyMap.put(attrName, getStringFromNode(valueNode));
		}
	}

	/**
	 * A map that maps attribute names on <code>SetSysobjectAttribute</code>
	 * instances.
	 */
	private final Map<String, String> setAttributeMap;

	public Map<String, String> getSetAttributeMap() {
		return setAttributeMap;
	}

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