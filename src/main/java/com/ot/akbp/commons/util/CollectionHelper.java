package com.ot.akbp.commons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ot.akbp.commons.util.mapper.Pair;

/**
 * Utilities for collections.
 *
 * @author Martin Heibel
 */
public final class CollectionHelper {

	/**
	 * Puts several entries to a map. The given key-value pairs are added to the map
	 * in the order in which they are returned by the collection's iterator.
	 * 
	 * @param <K>
	 *            The map's key type.
	 * @param <V>
	 *            The map's value type.
	 * @param map
	 *            The map to which the entries will be put.
	 * @param pairs
	 *            A collection of key-value pairs.
	 * @return Returns the given map.
	 */
	public static <K, V> Map<K, V> putToMap(final Map<K, V> map,
			final Collection<? extends Pair<? extends K, ? extends V>> pairs) {
		for (final Pair<? extends K, ? extends V> pair : pairs) {
			map.put(pair.getFirst(), pair.getSecond());
		}
		return map;
	}

	/**
	 * Puts several key-value pairs to a map.
	 * 
	 * @param <K>
	 *            The map's key type.
	 * @param <V>
	 *            The map's value type.
	 * @param map
	 *            The map to which the entries will be put.
	 * @param pairs
	 *            The key-value pairs.
	 * @return Returns the given map.
	 */
	@SafeVarargs
	public static <K, V> Map<K, V> putToMap(final Map<K, V> map, final Pair<? extends K, ? extends V>... pairs) {
		for (final Pair<? extends K, ? extends V> pair : pairs) {
			map.put(pair.getFirst(), pair.getSecond());
		}
		return map;
	}

	/**
	 * Adds several values to a set.
	 * 
	 * @param <T>
	 *            the set's element type.
	 * @param set
	 *            the set.
	 * @param values
	 *            the values to be added.
	 * @return the given set.
	 */
	@SafeVarargs
	public static <T> Set<T> addToSet(final Set<T> set, final T... values) {
		for (final T t : values) {
			set.add(t);
		}
		return set;
	}

	/**
	 * Creates a <code>HashSet</code> filled with the given values.
	 * 
	 * @param <T>
	 *            the set's element type.
	 * @param values
	 *            the values.
	 * @return a newly created <code>HashSet</code> containing the given values.
	 */
	@SafeVarargs
	public static <T> Set<T> createHashSet(final T... values) {
		final Set<T> set = new HashSet<T>(values.length);
		return addToSet(set, values);
	}

	/**
	 * Creates an unmodifiable set filled with the given values.
	 * 
	 * @param <T>
	 *            the set's element type.
	 * @param values
	 *            the values.
	 * @return a newly created set containing the given values.
	 */
	@SafeVarargs
	public static <T> Set<T> createUnmodifiableSet(final T... values) {
		switch (values.length) {
		case 0:
			return Collections.emptySet();
		case 1:
			return Collections.singleton(values[0]);
		default:
			return Collections.unmodifiableSet(createHashSet(values));
		}
	}

	/**
	 * Creates an <code>ArrayList</code> filled with the given values. The list's
	 * capacity will match its size.
	 * 
	 * @param <T>
	 *            the list's element type.
	 * @param values
	 *            the initial values.
	 * @return the list.
	 */
	@SafeVarargs
	public static <T> List<T> createArrayList(final T... values) {
		final List<T> l = new ArrayList<T>(values.length);
		for (final T t : values) {
			l.add(t);
		}
		return l;
	}

	/**
	 * Crates an unmodifiable list filled with the given values.
	 * 
	 * @param <T>
	 *            the list's element type.
	 * @param values
	 *            the values.
	 * @return the list.
	 */
	@SafeVarargs
	public static <T> List<T> createUnmodifiableList(final T... values) {
		return Collections.unmodifiableList(createArrayList(values));
	}

	/** Hidden constructor. */
	private CollectionHelper() {
	}

}