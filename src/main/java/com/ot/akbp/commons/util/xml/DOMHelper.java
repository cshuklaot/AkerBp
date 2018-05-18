/**
 * modified on 02 Aug 2006
 * by SPi
 *
 * added a constructor DOMHelper(DMSession,String)
 */
package com.ot.akbp.commons.util.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ot.akbp.commons.exception.ImpossibleException;
import com.ot.akbp.commons.exception.MissingMandatoryXPathException;
import com.ot.akbp.commons.exception.UnexpectedNodeNameException;
import com.ot.akbp.commons.exception.UnexpectedNodeTypeException;
import com.ot.akbp.commons.util.ThreadLocalMessageFormat;



/**
 * A class to handle parts of a DOM tree.
 * */
public class DOMHelper {

    /**
     * Maps DOM node types (<code>Short</code> objects) to a textual representation of the node
     * type.
     */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DOMHelper.class);

    private static final Map<Short, String> NODE_TYPE_TOSTRING_MAP;

	private static final String UTF8 = null;
    static {
        final Map<Short, String> nodeTypeToString = new HashMap<Short, String>();

        nodeTypeToString.put(Node.ATTRIBUTE_NODE, "Attribute");
        nodeTypeToString.put(Node.CDATA_SECTION_NODE, "CDATA Section");
        nodeTypeToString.put(Node.COMMENT_NODE, "Comment");
        nodeTypeToString.put(Node.DOCUMENT_FRAGMENT_NODE, "Document Fragment");
        nodeTypeToString.put(Node.DOCUMENT_NODE, "Document");
        nodeTypeToString.put(Node.DOCUMENT_TYPE_NODE, "Document Type");
        nodeTypeToString.put(Node.ELEMENT_NODE, "Element");
        nodeTypeToString.put(Node.ENTITY_NODE, "Entity");
        nodeTypeToString.put(Node.ENTITY_REFERENCE_NODE, "Entity Reference");
        nodeTypeToString.put(Node.NOTATION_NODE, "Notation");
        nodeTypeToString.put(Node.PROCESSING_INSTRUCTION_NODE, "Processing Instruction");
        nodeTypeToString.put(Node.TEXT_NODE, "Text");

        NODE_TYPE_TOSTRING_MAP = Collections.unmodifiableMap(nodeTypeToString);
    }

    /**
     * Creates an <code>Iterable</code> wrapper for a <code>NodeList</code>.
     *
     * @param list
     *            The node list.
     * @return Returns an iterable to iterate over the nodes in the list in sequential order.
     */
    public static Iterable<Node> iterable(final NodeList list) {
        return new Iterable<Node>() {

            /** {@inheritDoc} */
            @Override
            public Iterator<Node> iterator() {
                return new Iterator<Node>() {

                    /** {@inheritDoc} */
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException("No element removal allowed.");
                    }

                    /** {@inheritDoc} */
                    @Override
                    public Node next() {
                        if (this.i >= list.getLength()) {
                            throw new IndexOutOfBoundsException();
                        }

                        return list.item(this.i++);
                    }

                    /** {@inheritDoc} */
                    @Override
                    public boolean hasNext() {
                        return this.i < list.getLength();
                    }

                    private int i = 0;
                };
            }
        };
    }

    /**
     * Creates an <code>Iterable</code> wrapper for a <code>NodeList</code>.
     *
     * @param list
     *            The node list.
     * @return Returns an iterable to iterate over the nodes in the list in sequential order. Wraps
     *         each node in a <code>DOMHelper</code> object.
     */
    public static Iterable<DOMHelper> domHelperIterable(final NodeList list) {
        return new Iterable<DOMHelper>() {

            /** {@inheritDoc} */
            @Override
            public Iterator<DOMHelper> iterator() {
                return new Iterator<DOMHelper>() {

                    /** {@inheritDoc} */
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException("No element removal allowed.");
                    }

                    /** {@inheritDoc} */
                    @Override
                    public DOMHelper next() {
                        if (this.i >= list.getLength()) {
                            throw new IndexOutOfBoundsException();
                        }

                        return new DOMHelper(list.item(this.i++));
                    }

                    /** {@inheritDoc} */
                    @Override
                    public boolean hasNext() {
                        return this.i < list.getLength();
                    }

                    private int i = 0;
                };
            }
        };
    }

    /**
     * Converts BooleanAttrValues from common.dtd to boolean. Throws IllegalArgeumentExcpetion -- if
     * no null, "No", or "Yes".
     *
     * @param nullNoYes
     *            -- What it says.
     * @return -- true/false.
     */
    public static boolean booleanFromAttribute(final String nullNoYes) {
        final String routineName = "booleanFromAttribute";
        if (null == nullNoYes || nullNoYes.equals("No")) {
            return false;
        } else if (nullNoYes.equals("Yes")) {
            return true;
        }
        final String msg = "Failed to recognize value: " + nullNoYes + ".\nExpected: null/No/Yes.";
        LOGGER.error(MessageFormat.format(routineName + " - {0}", new Object[] { msg }));
        throw new IllegalArgumentException(msg);
    }

    /**
     * Recursive helper method for <code>getPath()</code>.
     *
     * @param node
     *            The node for which the path is created.
     * @return A buffer containing the path to the given node or "null" if the node is
     *         <code>null</code>.
     */
    private static StringBuffer createPath(final Node node) {
        if (node == null) {
            return new StringBuffer("null");
        }
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            return new StringBuffer();
        }

        final Node parent = node.getParentNode();
        final StringBuffer buf = createPath(parent);

        final String name = node.getNodeName();
        buf.append('/');
        buf.append(name);

        // check if we need a position condition
        final NodeList siblings = parent.getChildNodes();
        int nSimilarSiblings = 0; // siblings with same name (including current
        // node itself)
        int position = 0; // position of current node within similar siblings
        for (int i = 0; i < siblings.getLength(); ++i) {
            final Node sibling = siblings.item(i);
            if (sibling.getNodeName().equals(name)) {
                ++nSimilarSiblings;
            }
            if (sibling == node) {
                position = nSimilarSiblings;
            }
        }

        if (nSimilarSiblings > 1) {
            buf.append('[');
            buf.append(position);
            buf.append(']');
        }

        return buf;
    }

    /**
     * Usefull for debugging and error messages.
     *
     * @param node
     *            A node.
     * @return The path from the document root to the current context node.
     */
    public static String getPath(final Node node) {
        return createPath(node).toString();
    }

    /**
     * Get a text value from a node. The result depends on the node type:
     * <dl>
     * <dt>attribute
     * <dd>The attribute value.
     * <dt>CDATA section
     * <dd>The CDATA
     * <dt>comment
     * <dd>The comment
     * <dt>text
     * <dd>The text value
     * <dt>element
     * <dd>Must have only one child which must be a text node. Then the value of this text node is
     * returned
     * </dl>
     *
     * @param node
     *            The node.
     * @return The text value, may be <code>null</code>.
     */
    public static String getText(final Node node) {
        try {
            return org.apache.xpath.XPathAPI.eval(node, ".").str();
        } catch (final TransformerException exc) {
            throw new ImpossibleException(exc);
        }
    }

    /**
     * Get content string of node.
     *
     * @param node
     *            - target node
     * @return content string of node, <code>null</code> if node is empty.
     */
    public static String getContentWithTags(final Node node) {
        // Serialize node
        final StringBuilder buf = new StringBuilder(serialize(node));

        // Check for empty content
        final int indxStart = buf.indexOf(RIGHT_BROCKET) + 1;
        if (buf.length() == indxStart) {
            LOGGER.debug("The node is empty, return null");
            return null;
        }

        // Fetch content
        final int indxEnd = buf.lastIndexOf(LEFT_BROCKET);
        final String result = buf.substring(indxStart, indxEnd).replaceAll(
                REGEXP_LINE_BREAK,
                EMPTY_STRING);
        LOGGER.debug("Content of node: " + result);
        return result;
    }

    /**
     * Serialize node to string.
     *
     * @param node
     *            - target node
     * @return serialization of node.
     * @throws TransformerException
     */
    public static String serialize(final Node node) {
        LOGGER.debug("Node name: " + node.getNodeName());
        DOMImplementationRegistry registry;
        try {
            registry = DOMImplementationRegistry.newInstance();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        // try {

        final DOMImplementationLS impls = (DOMImplementationLS) registry.getDOMImplementation("LS");
        final LSSerializer serializer = impls.createLSSerializer();
        final DOMConfiguration domConfig = serializer.getDomConfig();
        domConfig.setParameter("xml-declaration", false);
        final String result = serializer.writeToString(node);
        LOGGER.debug("Serialization of node: " + result);
        return result;
    }

    /**
     * Get a textual representation of a given node type.
     *
     * @param nodeType
     *            The node type from a <code>org.w3c.dom.Node</code>.
     * @return Returns a string representing the node type in a human readable form.
     * @see org.w3c.dom.Node#getNodeType()
     */
    public static String nodeTypeToString(final short nodeType) {
        final String nodeTypeString = NODE_TYPE_TOSTRING_MAP.get(new Short(nodeType));
        return nodeTypeString == null //
                ? "Unknown node type (" + nodeType + ")" //
                : nodeTypeString;
    }

    /**
     * Parse a DOM tree from a file using default settings.
     *
     * @param f
     *            The file.
     * @return The document.
     */
    public static Document parse(final File f) throws ParserConfigurationException,
                                              SAXException,
                                              IOException {
        LOGGER.debug("parsing file '" + f.getAbsolutePath() + "'");
        final FileInputStream in = new FileInputStream(f);
        try {
            return parse(in);
        } finally {
            StreamHelper.close(in);
        }
    }

    /**
     * Parse a DOM tree from a stream using default settings.
     *
     * @param source
     *            The stream.
     * @return The document.
     */
    public static Document parse(final InputStream source) throws ParserConfigurationException,
                                                          SAXException,
                                                          IOException {
        LOGGER.debug("parsing InputStream");
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
    }

    /**
     * Parse a DOM tree from a stream using default settings.
     *
     * @param source
     *            The source.
     * @return The document.
     */
    public static Document parse(final InputSource source) throws ParserConfigurationException,
                                                          SAXException,
                                                          IOException {
        LOGGER.debug("parsing InputStream");
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
    }

    /**
     * Parse a DOM tree from a stream using default settings.
     *
     * @param sourceStr
     *            String with XML content as the source.
     * @return The document.
     */
    public static Document parse(final String sourceStr) throws ParserConfigurationException,
                                                        SAXException,
                                                        IOException {
        final InputSource source = new InputSource(new StringReader(sourceStr));
        LOGGER.debug("parsing InputStream");
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
    }

    /**
     * Get a text value from a node and an XPath expression. The XPath expression is evaluated
     * relative to the given node and the result node is interpreted as a text value.
     *
     * @param node
     *            The node.
     * @param xpath
     *            The XPath expression.
     * @return The text value, <code>null</code> if the XPath has no result node.
     * @throws TransformerException
     *             If the XPath expression is invalid.
     * @see DOMHelper#getText(Node)
     */
    public static String selectText(final Node node, final String xpath) throws TransformerException {
        LOGGER.trace("selectText, xpath: \"" + xpath + "\"");
        final Node resultNode = XPathAPI.selectSingleNode(node, xpath);
        return resultNode == null ? null : getText(resultNode);
    }

    /**
     * Write a dom tree to a file.
     *
     * @param node
     *            The root of the tree to be written.
     * @param f
     *            The file.
     */
    public static void write(final Node node, final File f) throws FileNotFoundException {
        final FileOutputStream out = new FileOutputStream(f);
        try {
            write(node, out);
        } finally {
            StreamHelper.close(out);
        }
    }

    /**
     * Write a dom tree to a stream.
     *
     * @param node
     *            The root of the tree to be written.
     * @param out
     *            output stream to write log to.
     */
    public static void write(final Node node, final OutputStream out) {
        try {
            final DOMSource source = new DOMSource(node);
            final StreamResult res = new StreamResult(out);

            final TransformerFactory tFactory = TransformerFactory.newInstance();
            final Transformer transformer = tFactory.newTransformer();
            if (node.getNodeType() == Node.DOCUMENT_NODE) {
                final DocumentType doctype = ((Document) node).getDoctype();
                if (doctype != null) {
                    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
                    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                }
            }

            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, res);
        } catch (final TransformerException exc) {
            throw new RuntimeException(exc);
        }
    }

    /**
     * The context node.
     */
    private final Node contextNode;

    /**
     * The document.
     */
    private final Document document;

    /**
     * Creates an empty document.
     */
    public DOMHelper() throws ParserConfigurationException {
        this(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
    }

    /**
     * Constructor.
     *
     * @param document
     *            The document and the context node for this handler.
     * @see DOMHelper#DOMHelper(Document, Node)
     */
    public DOMHelper(final Document document) {
        this(document, document);
    }

    /**
     * Constructor.
     *
     * @param document
     *            The document, needed e.g. to create new nodes.
     * @param contextNode
     *            The context node. All XPath expressions are evaluated relative to this node.
     */
    public DOMHelper(final Document document, final Node contextNode) {
        this.document = document;
        this.contextNode = contextNode;
    }

    /**
     * Reads a DOM from a file.
     *
     * @param f
     *            The file.
     */
    public DOMHelper(final File f) throws ParserConfigurationException, SAXException, IOException {
        this(parse(f));
    }

    /**
     * Reads a DOM from a stream.
     *
     * @param source
     *            The stream.
     */
    public DOMHelper(final InputStream source) throws ParserConfigurationException,
                                              SAXException,
                                              IOException {
        this(parse(source));
    }

    /**
     * Reads a DOM from a stream.
     *
     * @param source
     *            The source.
     */
    public DOMHelper(final InputSource source) throws ParserConfigurationException,
                                              SAXException,
                                              IOException {
        this(parse(source));
    }

    /**
     * Gets an attribute value from a given element node.
     *
     * @param node
     *            Gets an attribute value from a given element node.
     * @param attributeName
     *            The name of the attribute.
     * @return The the attribute value or <code>null</code> if the attribute does not exist.
     */
    public static String getAttribute(final Node node, final String attributeName) {
        if (!(node instanceof Element)) {
            throw new UnexpectedNodeTypeException("Nodes of type '"
                    + node.getNodeType()
                    + "' have no attributes.");
        }

        final Element meAsElement = (Element) node;
        return meAsElement.hasAttribute(attributeName)
                ? meAsElement.getAttribute(attributeName)
                : null;

    }

    /**
     * Gets a mandatory attribute value from a given element node.
     * <p>
     * Throws exception if the attribute does not exist.
     *
     * @param node
     *            Gets an attribute value from a given element node.
     * @param attributeName
     *            The name of the attribute.
     * @return The attribute value.
     */
    public static String getMandatoryAttribute(final Node node, final String attributeName) {
        final String attribute = getAttribute(node, attributeName);
        if (StringUtils.isNotBlank(attribute)) {
            return attribute;
        } else {
            throw new IllegalArgumentException("Attribute ["
                    + attributeName
                    + "] is missing for Node ["
                    + node.getNodeName()
                    + "]");
        }
    }

    /**
     * Get a text value from a node and an XPath expression. The XPath expression is evaluated
     * relative to the given node and the result node is interpreted as a text value.
     *
     * @param node
     *            The node.
     * @param xpath
     *            The XPath expression.
     * @param viewLog
     *            flag for showing log.
     * @return The text value, <code>null</code> if the XPath has no result node.
     * @throws TransformerException
     *             If the XPath expression is invalid.
     * @see DOMHelper#getText(Node)
     */
    public static String selectText(final Node node, final String xpath, final boolean viewLog)//
    throws TransformerException {
        final String routineName = "selectText";
        if (viewLog) {
            LOGGER.debug(routineName + " - " + "selectText, xpath: \"" + xpath + "\"");
        }
        final String value = selectText(node, xpath);
        if (viewLog) {
            LOGGER.debug(MessageFormat.format(routineName + " - value: {0}", value));
        }
        return value;
    }

    /**
     * Constructor.
     *
     * @param node
     *            The node for this handler.
     * @see DOMHelper#DOMHelper(Document, Node)
     */
    public DOMHelper(final Node node) {
        this(node.getOwnerDocument(), node);
    }

    /**
     * This method removes all the child elements specified by xpath. the xpath should be such as it
     * points only elements to be removed[use of .// is intended if only current nodes needs to be
     * evaluated].
     *
     * @param xPath
     * @throws TransformerException
     */
    public void removeChildren(final String xPath) throws TransformerException {
        final NodeList list = selectNodeList(xPath);
        for (int i = 0; i < list.getLength(); i++) {
            final Element curElement = (Element) list.item(i);
            final Node prev = curElement.getPreviousSibling();
            final Node itemsParent = getContextNode();
            if (prev != null
                    && prev.getNodeType() == Node.TEXT_NODE
                    && prev.getNodeValue().trim().length() == 0) {
                itemsParent.removeChild(prev);
            }
            itemsParent.removeChild(curElement);
        }
    }

    /**
     * Especially usefull for classes that wrap a certain element type.
     *
     * @param contextNode
     *            The node for this handler.
     * @param expectedNodeName
     *            The expected node name.
     * @throws UnexpectedNodeNameException
     *             if the given context node doesn't ave the desired name.
     */
    public DOMHelper(final Node contextNode, final String expectedNodeName) throws UnexpectedNodeNameException {
        this(contextNode);
        if (!expectedNodeName.equals(contextNode.getNodeName())) {
            throw new UnexpectedNodeNameException("Unexpected node name: '"
                    + contextNode.getNodeName()
                    + "', expected '"
                    + expectedNodeName
                    + "' ("
                    + getContextPath()
                    + ").");
        }
    }

    /**
     * Document and absolute xpath to context node.
     *
     * @param document
     *            -- What it says.
     * @param contextNodeXpath
     *            -- What it says.
     * @throws Exception
     *             -- Error during document processing or xpath not found.
     */
    public DOMHelper(final Document document, final String contextNodeXpath) throws Exception {
        this.document = document;
        try {
            this.contextNode = XPathAPI.selectSingleNode(document, contextNodeXpath);
            if (null == this.contextNode) {
                throw new Exception("XPathAPI.selectSingleNode returned null.");
            }
        } catch (final Exception ex) {
            final String msg = "Failed to create node for xpath "
                    + contextNodeXpath
                    + ": "
                    + ex.getMessage();
            throw new Exception(msg, ex);
        }
    }

    /**
     * Creates a new element and appends it as a child.
     *
     * @param name
     *            The tag name of the element.
     * @return Returns a helper tohat handles the new element.
     */
    public DOMHelper appendChildElement(final String name) {
        final Node newElement = this.document.createElement(name);
        this.contextNode.appendChild(newElement);
        return new DOMHelper(newElement);
    }

    /**
     * Creates a new element and appends it as a child before reference element.
     *
     * @param name
     *            The tag name of the element.
     * @return Returns a helper tohat handles the new element.
     */
    public DOMHelper insertChildElementBefore(final String name, final Node referenceNode) {
        final Node newElement = this.document.createElement(name);
        this.contextNode.insertBefore(newElement, referenceNode);
        return new DOMHelper(newElement);

    }

    /**
     * Append a processing instruction.
     *
     * @param target
     *            The PI's target.
     * @param data
     *            The PI's data.
     */
    public void appendProcessingInstruction(final String target, final String data) {
        final Node piNode = this.document.createProcessingInstruction(target, data);
        this.contextNode.appendChild(piNode);
    }

    /**
     * Append a text node.
     *
     * @param text
     *            The text node's text value.
     */
    public void appendText(final String text) {
        final Node textNode = this.document.createTextNode(text);
        this.contextNode.appendChild(textNode);
    }

    /**
     * Append a text element, does nothing if the given text object is <code>null</code>.
     * Convenience method that appends a child elements and appends a text node to that child.
     *
     * @param name
     *            The name of the new element.
     * @param text
     *            This object is converted to a string for text node's text value.
     * @return Returns a handler for the new element or <code>null</code> if the given object is
     *         <code>null</code>.
     */
    public DOMHelper appendTextElement(final String name, final Object text) {
        if (text == null) {
            return null;
        }

        final DOMHelper child = appendChildElement(name);
        child.appendText(text.toString());
        return child;
    }

    /**
     * Get an attribute value from an element node.
     *
     * @param attributeName
     *            The name of the attribute.
     * @return Returns the attribute value or <code>null</code> if the attribute does not exist.
     * @throws UnexpectedNodeTypeException
     *             if this object does not wrap an element node.
     */
    public String getAttribute(final String attributeName) throws UnexpectedNodeTypeException {
        if (!(this.contextNode instanceof Element)) {
            throw new UnexpectedNodeTypeException("Nodes of type '"
                    + getNodeTypeString()
                    + "' have no attributes.");
        }

        final Element meAsElement = (Element) this.contextNode;
        return meAsElement.hasAttribute(attributeName)
                ? meAsElement.getAttribute(attributeName)
                : null;
    }

    /**
     * @return The node this object handles.
     */
    public final Node getContextNode() {
        return this.contextNode;
    }

    /**
     * Usefull for debugging and error messages.
     *
     * @return The path from the document root to the current context node.
     */
    public String getContextPath() {
        return getPath(this.contextNode);
    }

    /**
     * @return The document.
     */
    public final Document getDocument() {
        return this.document;
    }

    /**
     * @return Returns the first child node or <code>null</code> if there is none.
     */
    public final DOMHelper getFirstChildElement() {
        try {
            final Node childNode = selectSingleNode("*");
            return childNode == null ? null : new DOMHelper(childNode);
        } catch (final TransformerException exc) {
            throw new ImpossibleException(exc);
        }
    }

    /**
     * @return Return's the context node's node name.
     */
    public final String getName() {
        return this.contextNode.getNodeName();
    }

    /**
     * @return Returns the wrapped node's node type.
     * @see org.w3c.dom.Node#getNodeType()
     */
    public short getNodeType() {
        return this.contextNode.getNodeType();
    }

    /**
     * @return Returns a human readable text describing the node type of the node wrapped by this
     *         object.
     */
    public String getNodeTypeString() {
        return nodeTypeToString(this.getNodeType());
    }

    /**
     * @return Returns the current context node converted to text.
     * @see DOMHelper#getText(Node)
     */
    public final String getText() {
        return getText(this.contextNode);
    }

    /**
     * Evaluates an XPath expression and converts it to a boolean.
     *
     * @param xpath
     *            The XPath.
     * @return A boolean representation of the result.
     * @throws TransformerException
     *             if the XPath is invalid.
     */
    public boolean selectBool(final String xpath) throws TransformerException {
        final String routineName = "selectBool";
        LOGGER.trace(routineName + ": xpath: \"" + xpath + "\"");
        return XPathAPI.eval(this.contextNode, xpath).bool();
    }

    /**
     * Select double.
     *
     * @param xpath
     *            the xpath
     * @return the double
     * @throws TransformerException
     *             the transformer exception
     */
    public double selectDouble(final String xpath) throws TransformerException {
        final String routineName = "selectDouble";
        LOGGER.trace(routineName + ": xpath: \"" + xpath + "\"");
        return XPathAPI.eval(this.contextNode, xpath).num();
    }

    /**
     * Get an integer value.
     *
     * @param xpath
     *            An XPath expression that must result in a text node that can be converted into an
     *            integer.
     * @param defaultValue
     *            If the node does not exist, this default value is returned.
     * @return Some int value.
     * @throws NumberFormatException
     *             if the XPath results in a text that cannot be converted into an integer.
     */
    public int selectIntValue(final String xpath, final int defaultValue) throws TransformerException,
                                                                         NumberFormatException {
        LOGGER.trace("selectIntValue, xpath: \"" + xpath + "\"");
        final String value = selectText(xpath);
        try {
            return value == null ? defaultValue : Integer.parseInt(value);
        } catch (final NumberFormatException exc) {
            throw new NumberFormatException("'" + value + "' is no integer" //
                    + " (context: \""
                    + getContextPath()
                    + "\", xpath: \""
                    + xpath
                    + "\").");
        }
    }

    /**
     * Wraps XPathAPI.selectNodeIterator using this object's root as the context node.
     *
     * @param xpath
     *            The XPath expression.
     * @return The resulting node list.
     * @throws TransformerException
     *             If the expression is invalid.
     */
    public NodeIterator selectNodeIterator(final String xpath) throws TransformerException {
        LOGGER.trace("selectNodeIterator, xpath: \"" + xpath + "\"");
        return XPathAPI.selectNodeIterator(this.contextNode, xpath);
    }

    /**
     * Select a list of nodes using XPath.
     *
     * @param xpath
     *            The XPath expression.
     * @return The resulting node list, never <code>null</code>.
     * @throws TransformerException
     *             If the expression is invalid.
     */
    public NodeList selectNodeList(final String xpath) throws TransformerException {
        LOGGER.trace("selectNodeList, xpath: \"" + xpath + "\"");
        return XPathAPI.selectNodeList(this.contextNode, xpath);
    }

    /**
     * Get the name of a certain node.
     *
     * @param xpath
     *            The XPath that specifies the node.
     * @return The node name of the selected node.
     * @throws TransformerException
     *             if the XPath is invalid.
     */
    public String selectNodeName(final String xpath) throws TransformerException {
        return selectSingleNode(xpath).getNodeName();
    }

    /**
     * Select a single nodes using XPath.
     *
     * @param xpath
     *            The XPath expression.
     * @return The result node.
     * @throws TransformerException
     *             If the expression is invalid.
     */
    public Node selectSingleNode(final String xpath) throws TransformerException {
        LOGGER.trace("selectSingleNode, xpath: \"" + xpath + "\"");
        return XPathAPI.selectSingleNode(this.contextNode, xpath);
    }

    /**
     * Select a single mandatory node using XPath. In case if node is not found,
     * {@link MissingMandatoryXPathException} is thrown.
     *
     * @param xpath
     *            The XPath expression.
     * @return The result node, never <code>null</code>.
     * @throws TransformerException
     *             if the expression is invalid
     * @throws MissingMandatoryXPathException
     *             if the node is not found.
     */
    public Node selectMandatorySingleNode(final String xpath) throws TransformerException {
        final Node node = selectSingleNode(xpath);
        if (node == null) {
            throw new MissingMandatoryXPathException("Cannot find mandatory node by xpath: "
                    + xpath);
        }
        return node;
    }

    /**
     * Get a mandatory text value from an XPath expression. The XPath expression is evaluated
     * relative to the node this object handles node and the result node is interpreted as a text
     * value.
     *
     * @param xpath
     *            The XPath expression.
     * @return The text value, never <code>null</code>.
     * @throws TransformerException
     *             if the XPath expression is invalid.
     * @throws MissingMandatoryXPathException
     *             if the text element is not found or empty.
     */
    public String selectMandatoryText(final String xpath) throws TransformerException {
        final String value = selectText(xpath);
        if (StringUtils.isEmpty(value)) {
            throw new MissingMandatoryXPathException("Mandatory text element '"
                    + getContextPath()
                    + "/"
                    + xpath
                    + "' is missing or empty.");
        }
        return value;
    }

    /**
     * Get a text value from an XPath expression. The XPath expression is evaluated relative to the
     * node this object handles node and the result node is interpreted as a text value.
     *
     * @param xpath
     *            The XPath expression.
     * @return The text value, <code>null</code> if the XPath has no result node.
     * @throws TransformerException
     *             If the XPath expression is invalid.
     * @see DOMHelper#getText(Node)
     */
    public String selectText(final String xpath) throws TransformerException {
        return selectText(this.contextNode, xpath);
    }

    /**
     * Get a text value from an XPath expression. The XPath expression is evaluated relative to the
     * node this object handles node and the result node is interpreted as a text value.
     *
     * @param xpath
     *            The XPath expression.
     * @return The text value, <code>null</code> if the XPath has no result node.
     * @throws RuntimeException
     *             If the XPath expression is invalid.
     * @see DOMHelper#getText(Node)
     */
    public String selectTextRuntime(final String xpath) {
        try {
            return selectText(this.contextNode, xpath);
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    /**
     * Select a number of text values from the DOM.
     *
     * @param xpath
     *            The XPath that specifies the text values.
     * @return The result nodes of the XPath convertet to strings.
     * @throws TransformerException
     *             if the XPath is invalid.
     */
    public List<String> selectTexts(final String xpath) throws TransformerException {
        LOGGER.trace("selectTexts, xpath: \"" + xpath + "\"");
        final NodeList resultNodes = XPathAPI.selectNodeList(this.contextNode, xpath);
        final List<String> texts = new ArrayList<String>();
        for (int i = 0; i < resultNodes.getLength(); ++i) {
            texts.add(getText(resultNodes.item(i)));
        }

        return texts;
    }

    /**
     * Sets/removes an attribute value. If the given value is <code>null</code> the attribute is
     * removed.
     *
     * @param name
     *            The attribute name.
     * @param value
     *            The new attribute value.
     */
    public void setAttribute(final String name, final String value) {
        if (value == null) {
            ((Element) this.contextNode).removeAttribute(name);
        } else {
            ((Element) this.contextNode).setAttribute(name, value);
        }
    }



    /**
     * Write the partial dom tree with this element's context node as root to a stream.
     *
     * @param out
     *            The output stream to log to.
     */
    public final void write(final OutputStream out) {
        write(this.contextNode, out);
    }

    /**
     * Write the whole dom to a stream.
     *
     * @param out
     *            The output stream to write to.
     */
    public final void writeDocument(final OutputStream out) {
        write(this.document, out);
    }

    /**
     * Removes the whitespace (empty text) nodes. Example:
     *
     * <pre>
     *  &lt;car&gt;
     *       &lt;make&gt;BMW&lt;/make&gt;
     *       &lt;model&gt;2000&lt;/model&gt;
     *  &lt;/car&gt;
     * 
     *   becomes:
     * 
     *   &lt;car&gt;&lt;make&gt;BMW&lt;/make&gt;&lt;model&gt;2000&lt;/model&gt;&lt;/car&gt;
     * </pre>
     *
     * @param doc
     *            , xml document
     * @return the document
     */
    public static Document stripDocumentOfEmptyNodes(final Document doc) {
        final Element docRoot = doc.getDocumentElement();
        removeEmptyTextNodes(docRoot);
        return doc;
    }

    /**
     * <b>NOTE:</b> This method is called from XSL templates, so do not delete it until all
     * references in XSL templates are removed.<br>
     * Perform conversion of a given string containing XML data (strVal) into XML document with root
     * element with name elementName.
     *
     * @param strVal
     *            The string containing XML data
     * @param elementName
     *            The name of the root element.
     * @return root element of the XML, null if the System cannot parse such a string in the valid
     *         XML.
     */
    public static Element stringToElement(final String strVal, final String elementName) {
        final String preparedXMLString = WRAP_FORMAT.format(elementName, strVal);
        try {
            final InputStream stream = new ByteArrayInputStream(
                    preparedXMLString.getBytes(UTF8));
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setExpandEntityReferences(false);
            final Document doc = factory.newDocumentBuilder().parse(stream);

            return doc.getDocumentElement();
        } catch (final Exception ex) {
            LOGGER.error(
                    "Exception occurred during converting String [" + strVal + "] to element.",
                    ex);
        }
        return null;
    }

    /**
     * Checks whether the node is empty.
     *
     * @param nodeContents
     *            the node contents
     * @return true, if successful
     */
    private static boolean isNodeEmpty(final String nodeContents) {
        String nodeContent = nodeContents.trim();
        // remove tab, carriage return and newline characters
        nodeContent = nodeContent.replaceAll("\t", "");
        nodeContent = nodeContent.replaceAll("\r", "");
        nodeContent = nodeContent.replaceAll("\n", "");

        return StringUtils.isEmpty(nodeContent);
    }

    /**
     * Recursively removes the non element nodes.
     *
     * @param fromNode
     *            the from node
     */
    private static void removeEmptyTextNodes(final Node fromNode) {
        final List<Node> garbageNodes = new ArrayList<Node>();
        for (int i = 0; i < fromNode.getChildNodes().getLength(); i++) {
            final Node currentNode = fromNode.getChildNodes().item(i);

            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                removeEmptyTextNodes(currentNode);
            } else {
                if (currentNode.getNodeType() == Node.TEXT_NODE) {
                    final Text text = (Text) currentNode;
                    if (isNodeEmpty(text.getWholeText())) {
                        garbageNodes.add(currentNode);
                    }
                }
            }
        }
        // remove nodes from DOM
        for (final Node n : garbageNodes) {
            fromNode.removeChild(n);
        }
    }

    /**
     * Format object for wrapping string in xml tag.
     */
    private static final ThreadLocalMessageFormat WRAP_FORMAT = new ThreadLocalMessageFormat(
            "<{0}>{1}</{0}>");


    /**
     * Empty string.
     */
    private static final String EMPTY_STRING = "";

    /**
     * Regexp of line break.
     */
    private static final String REGEXP_LINE_BREAK = "(\\n)|(\\r)";

    /**
     * Right brocket.
     */
    private static final String RIGHT_BROCKET = ">";

    /**
     * Left brocket.
     */
    private static final String LEFT_BROCKET = "<";

}
