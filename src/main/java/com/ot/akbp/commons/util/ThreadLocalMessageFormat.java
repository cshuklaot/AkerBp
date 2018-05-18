package com.ot.akbp.commons.util;

import java.text.MessageFormat;

/**
 * Makes {@link MessageFormat} functionality available in a thread-safe way. Each instance of this
 * class wraps a <code>ThreadLocal<MessageFormat></code> and delegates method calls to it.
 * 
 * @author heibel
 */
public class ThreadLocalMessageFormat {

    /**
     * Constructor.
     * 
     * @param pattern
     *            the {@link MessageFormat} pattern.
     */
    public ThreadLocalMessageFormat(final String pattern) {
        this.threadLocal = new ThreadLocal<MessageFormat>() {

            /** {@inheritDoc} */
            @Override
            protected MessageFormat initialValue() {
                return new MessageFormat(pattern);
            }

        };
    }

    /**
     * Calls {@link MessageFormat#format(Object)} and returns the result.
     * 
     * @param params
     *            the parameters to insert into the message.
     * @return the formatted result.
     */
    public String format(final Object... params) {
        return this.threadLocal.get().format(params);
    }

    private final ThreadLocal<MessageFormat> threadLocal;

}
