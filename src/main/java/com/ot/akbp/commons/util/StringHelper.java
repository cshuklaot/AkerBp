package com.ot.akbp.commons.util;

import java.text.Format;
import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

/**
 * Utility class with methods for <code>String</code> handling. Please check
 * Apache {@link StringUtils} before adding functionality here.
 *
 */
public final class StringHelper {

	/**
	 * Constructs a single string from the array of strings.
	 *
	 * @param delimiter
	 *            The delimiter put between the single values.
	 * @param strings
	 *            The strings to be concatenated.
	 * @return Returns a concatenation of the given Strings delimited by the given
	 *         delimiter, an empty string if the given array is <code>null</code>.
	 * @see StringUtils#join(Object[], String)
	 */
	public static String concat(final String delimiter, final String... strings) {
		final String joined = StringUtils.join(strings, delimiter);
		return (joined == null) ? "" : joined;
	}

	/**
	 * Convert supplemental characters (if they are present) in initialString to
	 * entities (e.g. &#x1d5e3). <br>
	 * <br>
	 * <b>This method is used to workaround supplemental character converting
	 * problem in Xerces 2.0.2. The problem was resolved in Xerces 2.7.0 and above,
	 * so if project will migrate to newer version of Xerces, this method can be
	 * removed.</b>
	 *
	 * @param initialString
	 *            A String to convert
	 * @return Copy of initialString where supplemental characters converted to
	 *         entities. If initialString does not contain any supplemental
	 *         characters, the copy of initialString is returned.
	 */
	public static String convertSupplementalCharsToEntities(final String initialString) throws Exception {

		if (initialString == null) {
			throw new Exception("convertSupplementalCharsToEntities: initialString is null!");
		}
		final StringBuilder builder = new StringBuilder();
		final int initialStrLength = initialString.length();
		for (int i = 0; i < initialString.length(); i++) {
			final char potentialHighSurrogate = initialString.charAt(i);
			if (Character.isHighSurrogate(potentialHighSurrogate)) {
				if ((i + 1) < initialStrLength) {
					final char lowSurrogate = initialString.charAt(i + 1);
					if (Character.isLowSurrogate(lowSurrogate)) {
						final int supplemental = Character.toCodePoint(potentialHighSurrogate, lowSurrogate);
						builder.append(MessageFormat.format(SUPPLEMENTAL_CHAR_TO_ENTITY_FORMAT,
								Integer.toHexString(supplemental)));
						++i;
						continue;
					} else {
						throw new Exception("High surrogate character is expected to be followed by the low surrogate"
								+ ", but the non-low surrogate gotten");
					}
				} else {
					throw new Exception("High surrogate character is expected to be followed by the low surrogate"
							+ ", but the end of the string has gotten");
				}
			}
			builder.append(potentialHighSurrogate);
		}
		return builder.toString();

	}

	/**
	 * Format a string using a parameter array. This method passes the parameter
	 * array to the <code>Format</code> object as it is required for
	 * <code>MessageFormat</code> objects.
	 *
	 * @param format
	 *            The <code>Format</code> object.
	 * @param params
	 *            The parameters.
	 * @return The formatted String.
	 */
	public static String format(final Format format, final Object... params) {
		return format.format(params);
	}

	/**
	 * Creates a <code>MessageFormat</code> object and calls
	 * {@link StringHelper#format(Format, Object...)}.
	 *
	 * @param pattern
	 *            The pattern string.
	 * @param params
	 *            The parameters.
	 * @return The formatted String.
	 */
	public static String format(final String pattern, final Object... params) {
		return format(new MessageFormat(pattern), params);
	}

	/**
	 * Finds the index of the n-th occurrence of a string (marker) inside a given
	 * string.<br>
	 *
	 * @param str
	 *            the string where to find the marker
	 * @param marker
	 *            the marker to be found
	 * @param n
	 *            the occurrence of interest (starting with 1)
	 * @return -1 if the marker was not found n times
	 */
	public static int indexOfNth(final String str, final String marker, final int n) {
		if (n < 1) {
			throw new RuntimeException("Invalid occurrence value. (First valid value is 1.)");
		}
		int found = 0;
		int i = str.indexOf(marker);
		while (i < str.length()) {
			++found;
			if (-1 == i || found == n) {
				return i;
			}
			i = str.indexOf(marker, i + marker.length());
		}
		return -1;
	}

	/**
	 * Checks if a string is null or zero sized or has the value <code>"0"</code>.
	 *
	 * @param str
	 *            The string to be checked.
	 * @return Returns <code>true</code> if string is null, empty or
	 *         <code>"0"</code>.
	 */
	public static boolean isNullEmptyOrZero(final String str) {
		return StringUtils.isEmpty(str) || str.equals("0");
	}

	/**
	 * @deprecated just use {@link StringUtils#isBlank(String)} directly
	 *             <p/>
	 *             Checks if a string is null or blank or zero sized .
	 * @param str
	 *            The string to be checked.
	 * @return Returns <code>true</code> if string is null, empty or contains only
	 *         spaces.
	 */
	@Deprecated
	public static boolean isNullEmptyOrBlank(final String str) {
		return StringUtils.isBlank(str);
	}

	/**
	 * Find index of first non-whitespace character using same rules as in
	 * {@link String#trim()}.
	 */
	public static int lTrim(final CharSequence cs, final int stArg, final int endArg) {
		final int len = endArg;
		int st = stArg;

		while ((st < len) && (cs.charAt(st) <= ' ')) {
			st++;
		}
		return st;
	}

	/**
	 * Find index of last non-whitespace character using same rules as in
	 * {@link String#trim()}.
	 */
	public static int rTrim(final CharSequence cs, final int st, final int endArg) {
		int len = endArg;

		while ((st < len) && (cs.charAt(len - 1) <= ' ')) {
			len--;
		}
		return len;
	}

	/**
	 * Format string for supplemental chars to entity conversion.
	 */
	private static final String SUPPLEMENTAL_CHAR_TO_ENTITY_FORMAT = "&#x{0};";

	/** Hidden constructor. */
	private StringHelper() {
	}

}