package com.ot.akbp.commons.util.mapper;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Node;

import com.documentum.fc.client.IDfSysObject;
import com.spr.ajwf.commons.util.ThreadLocalMessageFormat;
import com.spr.ajwf.commons.xml.DOMHelper;

/**
 * Sets dates from compound date elements containing date as three fields: Year, Month and Day.

 */
public class SetDateYearFromCompoundElement extends AbstractSetAttribute {

    /**
     * Constructor.
     *
     * @param attributeName
     *            The name of attribute to set.
     */
    public SetDateYearFromCompoundElement(final String attributeName) {
        super(attributeName);
    }

    /**
     * Constructor.
     *
     * @param attributeName
     *            -- The attribute we're updating
     * @param updateOnlyIfEmpty
     *            -- If true, skip update if the attribute is set alread.
     */
    public SetDateYearFromCompoundElement(
                                          final String attributeName,
                                          final boolean updateOnlyIfEmpty) {
        super(attributeName);
    }

    protected String getDateString(final Node nodeValue) {
        final DOMHelper node = new DOMHelper(nodeValue);
        try {
            return DATE_STRING_FORMAT.format(node.selectText(MONTH), node.selectText(YEAR));
        } catch (final TransformerException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static final String MONTH = "Month";

    private static final String YEAR = "Year";

    private static final ThreadLocalMessageFormat DATE_STRING_FORMAT = new ThreadLocalMessageFormat(
            "{0}/{1}");

    @Override
    protected void setValue(
            final IDfSysObject object,
            final String attrName,
            final int attrIndex,
            final Node valueNode) throws Exception {

        final String newValue = getDateString(valueNode);
        object.setRepeatingString(attrName, attrIndex, newValue);

    }
}
