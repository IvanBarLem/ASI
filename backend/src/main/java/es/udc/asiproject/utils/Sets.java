package es.udc.asiproject.utils;

import java.util.HashSet;
import java.util.Set;

public class Sets {
	public static <T> Set<T> difference(final Set<T> setOne, final Set<T> setTwo) {
		Set<T> result = new HashSet<T>(setOne);
		result.removeAll(setTwo);

		return result;
	}
}
