package com.ot.akbp.commons.util.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Helper class for streams.
 *
 * @author Martin Heibel
 */
public final class StreamHelper {
    
    /**
     * Copy data from an input stream to an output stream.
     * 
     * @param in
     *            The data source.
     * @param out
     *            The data sink.
     * @throws IOException
     *             if a read or write error occurrs.
     */
    public static void copyStream(final InputStream in, final OutputStream out) throws IOException {
        final byte[] buffer = new byte[STREAM_COPY_BUFFER_SIZE];
        for (int bytesRead = in.read(buffer); bytesRead != -1; bytesRead = in.read(buffer)) {
            out.write(buffer, 0, bytesRead);
        }
    }
    
    /**
     * Convenience method, copy data from an input stream to a file.
     * 
     * @param in
     *            The data source.
     * @param f
     *            The file.
     */
    public static void copyStream(final InputStream in, final File f) throws FileNotFoundException,
            IOException {
        final FileOutputStream out = new FileOutputStream(f);
        try {
            copyStream(in, out);
        } finally {
            close(out);
        }
    }
    
    /**
     * Closes a writer ignoring exceptions. If <code>os</code> is <code>null</code> nothing is done.
     * 
     * @param writer
     *            The writer to close.
     */
    public static void close(final Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (final Exception exc) {
                LOGGER.warn("Cannot close writer.", exc);
            }
        }
    }
    
    /**
     * Closes an output stream ignoring exceptions. If <code>os</code> is <code>null</code> nothing
     * is done.
     * 
     * @param os
     *            The stream to close.
     */
    public static void close(final OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (final Exception exc) {
                LOGGER.warn("Cannot close output stream.", exc);
            }
        }
    }
    
    /**
     * Closes an input stream ignoring exceptions. If <code>is</code> is <code>null</code> nothing
     * is done.
     * 
     * @param is
     *            The stream to close.
     */
    public static void close(final InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (final Exception exc) {
                LOGGER.warn("Cannot close input stream.", exc);
            }
        }
    }
    
    /**
     * Closes a reader ignoring exceptions. If <code>in</code> is <code>null</code> nothing is done.
     * 
     * @param in
     *            The reader to close.
     */
    public static void close(final Reader in) {
        if (in != null) {
            try {
                in.close();
            } catch (final Exception exc) {
                LOGGER.warn("Cannot close reader.", exc);
            }
        }
    }
    
    /**
     * Private constructor prevents thic class from being instantiated.
     */
    private StreamHelper() {}
    
    /**
     * The logger for this class.
     */
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamHelper.class);
    
    /**
     * Buffer size for <code>copyStream()</code>.
     * 
     * @see StreamHelper#copyStream(InputStream, OutputStream)
     */
    private static final int STREAM_COPY_BUFFER_SIZE = 1024;
    
    /**
     * Encoding UTF-8.
     */
    public static final String CODING_UTF_8 = "UTF-8";
    
}