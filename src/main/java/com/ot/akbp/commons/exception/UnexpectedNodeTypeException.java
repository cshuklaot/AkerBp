package com.ot.akbp.commons.exception;

/**
 * An exception type to indicate that a node has a different type than expected.
 *
 * @author Martin Heibel
 */
public class UnexpectedNodeTypeException extends RuntimeException {
    
    /**
     * Constructor.
     * 
     * @param message
     *            An error message.
     */
    public UnexpectedNodeTypeException(final String message) {
        super(message);
    }
    
    /**
     * Constructor.
     * 
     * @param message
     *            An error message.
     * @param cause
     *            The exception that caused this one.
     */
    public UnexpectedNodeTypeException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    /**
     * <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 1L;
    
}