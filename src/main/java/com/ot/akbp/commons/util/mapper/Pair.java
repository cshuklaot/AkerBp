package com.ot.akbp.commons.util.mapper;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A pair of objects (immutable).
 *
 * @param <T1>
 *            The type of the first object.
 * @param <T2>
 *            The type of the second oject.
 * @author heibel
 */
public class Pair<T1, T2> {

	/**
	 * Utility method that creates a pair. Using this method prevents you from
	 * having to explicitly specify the type arguments.
	 * 
	 * @param <T1>
	 *            The type of the first object.
	 * @param <T2>
	 *            The type of the second object.
	 * @param first
	 *            The first object.
	 * @param second
	 *            The second object.
	 * @return Returns an appropriate <code>Pair</code> object.
	 */
	public static <T1, T2> Pair<T1, T2> create(final T1 first, final T2 second) {
		return new Pair<T1, T2>(first, second);
	}

	/**
	 * Utility method that creates an integer pair. Using this method prevents you
	 * from having to explicitly specify the type arguments.
	 * 
	 * @param i1
	 *            The first integer.
	 * @param i2
	 *            The second integer.
	 * @return Returns a <code>Pair</code> object storing the two integers.
	 */
	public static Pair<Integer, Integer> create(final int i1, final int i2) {
		return new Pair<Integer, Integer>(i1, i2);
	}

	/**
	 * Constructor.
	 * 
	 * @param first
	 *            The first object.
	 * @param second
	 *            The second object.
	 */
	public Pair(final T1 first, final T2 second) {
		super();
		this.first = first;
		this.second = second;
	}

	/**
	 * @return Returns the first object.
	 */
	public T1 getFirst() {
		return first;
	}

	/**
	 * @return Returns the second object.
	 */
	public T2 getSecond() {
		return second;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Pair[" + first + "|" + second + "]";
	}

	private final T1 first;

	private final T2 second;

}
