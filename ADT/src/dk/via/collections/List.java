package dk.via.collections;

import java.util.Iterator;

public interface List<T> {
	T get(int i);
	Iterator<T> iterator();
	void add(T x);
	void add(int i, T x);
	void put(int i, T x);
	void remove(int i);
	int size();
}
