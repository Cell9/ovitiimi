package ohtu.unitAndRepoTests;

import java.util.Collection;
import java.util.Iterator;

import ohtu.database.entities.ISimilar;

public class Helpers {
	public static <T extends ISimilar<T>> boolean areSimilar(Collection<T> left, Collection<T> right) {
		if (left.size() != right.size()) { //Fast path
			return false;
		}
		
		Iterator<T> leftIterator = left.iterator();
		Iterator<T> rightIterator = right.iterator();
		while (leftIterator.hasNext() && rightIterator.hasNext()) {
			if (!leftIterator.next().areSimilar(rightIterator.next())) {
				return false;
			}
		}
		
		return true;
	}
}
