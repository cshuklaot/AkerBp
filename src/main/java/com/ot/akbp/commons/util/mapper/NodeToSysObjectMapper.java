package com.ot.akbp.commons.util.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.documentum.fc.client.IDfSysObject;
import com.spr.ajwf.commons.util.CollectionHelper;
import com.spr.ajwf.commons.util.Pair;

/**
 * Maps values from a DOM node to an <code>IDfSysObject</code> instance using specific setters.
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
    public NodeToSysObjectMapper(final Pair<String, SetSysobjectAttribute>... xpathsAndSetters) {
        this.setAttributeMap = new LinkedHashMap<String, SetSysobjectAttribute>();
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
        this.setAttributeMap = new LinkedHashMap<String, SetSysobjectAttribute>();
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
    public void override(final String xpath, final SetSysobjectAttribute setter) {
        setAttributeMap.put(xpath, setter);
    }
    
    /**
     * Executes the XPath expressions and maps the values to the corresponding sysobject attributes.
     *
     * @param node
     *            The context node for the XPath expressions.
     * @param object
     *            The sysobject.
     * @throws TransformerException
     *             if one of the XPath expressions is invalid.
     */
    public void process(final DOMHelper node, final IDfSysObject object)
            throws TransformerException, Exception {
        if (node == null) {
            return;
        }
        for (final Entry<String, SetSysobjectAttribute> entry : setAttributeMap.entrySet()) {
            final String xPath = entry.getKey();
            final SetSysobjectAttribute setter = entry.getValue();
            final String attrName = setter.getAttributeName();
            if (object.isAttrRepeating(attrName)) {
                mapRepeatingAttribute(setter, attrName, node.selectNodeList(xPath), object);
            } else {
                mapSingleAttribute(setter, attrName, node.selectSingleNode(xPath), object);
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
    private void mapRepeatingAttribute(final SetSysobjectAttribute setter, final String attrName,
            final NodeList valueNodes, final IDfSysObject object) throws Exception {
        final int nValues = valueNodes.getLength();
        for (int i = 0; i < nValues; ++i) {
            setter.set(object, attrName, i, valueNodes.item(i));
        }
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
    private void mapSingleAttribute(final SetSysobjectAttribute setter, final String attrName,
            final Node valueNode, final IDfSysObject object) throws Exception {
        if (valueNode != null) {
            setter.set(object, attrName, 0, valueNode);
        }
    }
    
    /**
     * A map that maps attribute names on <code>SetSysobjectAttribute</code> instances.
     */
    private final Map<String, SetSysobjectAttribute> setAttributeMap;
    
    public Map<String, SetSysobjectAttribute> getSetAttributeMap() {
        return setAttributeMap;
    }
    
}