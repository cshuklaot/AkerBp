package com.ot.akbp.commons.exception;

/**
 * Use whenever you must provide a catch block for a checked exception that should not occur is this
 * context or throw in places in the code that will only be executed if there is a logical
 * implementation error.
 *
 * @author Martin Heibel
 */
public class ImpossibleException extends RuntimeException {
    
    /**
     * Constructor.
     * 
     * @param message
     *            An error message.
     */
    public ImpossibleException(final String message) {
        super("Impossible(?) exception: " + message);
    }
    
    /**
     * Constructor.
     * 
     * @param exc
     *            The exception that should never be thrown.
     */
    public ImpossibleException(final Exception exc) {
        super("Impossible(?) exception.", exc);
    }
    
    /**
     * Constructor.
     * 
     * @param message
     *            An error message.
     * @param exc
     *            The exception that should never be thrown.
     */
    public ImpossibleException(final String message, final Exception exc) {
        super("Impossible(?) exception: " + message, exc);
    }
    
    /**
     * <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 1L;
    
}