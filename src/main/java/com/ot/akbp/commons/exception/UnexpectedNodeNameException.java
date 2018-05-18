package com.ot.akbp.commons.exception;

/**
 * Indicates a node name that doesn't meet the expectations.
 *
 * @author Martin Heibel
 */
public class UnexpectedNodeNameException extends RuntimeException {
    
    /**
     * Constructor.
     * 
     * @param message
     *            A helpfull error message.
     */
    public UnexpectedNodeNameException(final String message) {
        super(message);
    }
    
    /**
     * Constructor.
     * 
     * @param message
     *            A helpfull error message.
     * @param cause
     *            The exception that caused this one.
     */
    public UnexpectedNodeNameException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    /**
     * <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 1L;
    
}
