package com.ot.akbp.commons.util.mapper;

import java.text.DateFormat;

import org.w3c.dom.Node;

import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfTime;
import com.documentum.fc.common.IDfTime;
import com.spr.ajwf.commons.xml.DOMHelper;

/**
 * Set date values.
 * 
 * @author Chandresh Shukla
 */
public class SetDateAttribute extends AbstractSetAttribute {

    /**
     * Constructor.
     *
     * @param attributeName
     *            The name of attribute to set.
     * @param format
     *            Will be used to convert string values to dates.
     */
    public SetDateAttribute(final String attributeName, final DateFormat format) {
        super(attributeName);
        this.format = format;
    }

    /**
     * Constructor.
     *
     * @param attributeName
     *            The name of attribute to set.
     * @param format
     *            Will be used to convert string values to dates.
     * @param updateOnlyIfEmpty
     *            -- If true, update the value only if the date is nulldate.
     */
    public SetDateAttribute(
                            final String attributeName,
                            final DateFormat format,
                            final boolean updateOnlyIfEmpty) {
        super(attributeName);
        this.format = format;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(
            final IDfSysObject object,
            final String attrName,
            final int attrIndex,
            final Node valueNode) throws Exception {

        final IDfTime newValue = new DfTime(getDateString(valueNode), this.format);
        object.setRepeatingTime(attrName, attrIndex, newValue);

    }

    /**
     * @param nodeValue
     *            XML node containing value
     * @return the string value extracted from node.
     */
    protected String getDateString(final Node nodeValue) {
        return DOMHelper.getText(nodeValue);
    }

    /**
     * The date format this object can store.
     */
    private final DateFormat format;

}