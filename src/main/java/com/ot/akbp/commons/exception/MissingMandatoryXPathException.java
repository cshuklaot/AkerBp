package com.ot.akbp.commons.exception;

/**
 * An exception that indicates that configuration object does not contain mandatory xPath.
 *
 * @author Sergey Oplavin
 */
public class MissingMandatoryXPathException extends RuntimeException {
    
    /**
     * UID.
     */
    private static final long serialVersionUID = -5129951917080330295L;
    
    /**
     * Constructor.
     * 
     * @param cause
     *            The exception that caused this one.
     */
    public MissingMandatoryXPathException(final Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructor.
     * 
     * @param message
     *            An error message.
     */
    public MissingMandatoryXPathException(final String message) {
        super(message);
    }
    
    /**
     * @param message
     *            An error message.
     * @param cause
     *            The exception that caused this one.
     */
    public MissingMandatoryXPathException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
}
