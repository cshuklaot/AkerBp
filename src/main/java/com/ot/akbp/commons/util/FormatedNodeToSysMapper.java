package com.ot.akbp.commons.util;

import java.text.MessageFormat;
import java.util.Iterator;

import com.ot.akbp.commons.util.mapper.NodeToSysObjectMapper;

public class FormatedNodeToSysMapper extends NodeToSysObjectMapper {
    
    /**
     * Construct from another mapper.
     *
     * @param mapper
     *            mapper.
     */
    public FormatedNodeToSysMapper(final String fileName, //
                                   final NodeToSysObjectMapper... mappers) {
        super(mappers);
        final Iterator<String> iterator = getSetAttributeMap().keySet().iterator();
        final Object[] fileNameArr = { fileName };
        while (iterator.hasNext()) {
            final String key = iterator.next();
            override(new MessageFormat(key).format(fileNameArr), getSetAttributeMap().get(key));
        }
    }
}
