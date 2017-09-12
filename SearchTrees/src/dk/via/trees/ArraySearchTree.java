package dk.via.trees;

@SuppressWarnings("unchecked")
public class ArraySearchTree<T extends Comparable<T>> implements SearchTree<T> {
	private T[] elements = (T[]) new Comparable[4];
	private int size = 0;
	
	private void set(int index, T x) {
		while(index >= elements.length) {
			T[] temp = (T[]) new Comparable[2 * elements.length];
			System.arraycopy(elements, 0, temp, 0, elements.length);
			elements = temp;
		}
		elements[index] = x;
	}

	private T get(int index) {
		if (index >= elements.length)
			return null;
		else
			return elements[index];
	}
	
	private void clear() {
		elements = (T[]) new Comparable[4];
		size = 0;
	}
	
	@SuppressWarnings("unused")
	// Not really used, but here for completeness.
	private int parent(int index) {
		return (index - 1) / 2;
	}
	
	private int left(int index) {
		return 2 * index + 1;
	}
	
	private int right(int index) {
		return 2 * index + 2;
	}
	
	private int targetIndex(T x) {
		int index = 0;
		while(get(index) != null && get(index).compareTo(x) != 0) {
			if (get(index).compareTo(x) < 0) 
				index = left(index);
			else
				index = right(index);	
		}
		return index;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public T find(T x) {
		return get(targetIndex(x));
	}

	@Override
	public void add(T x) {
		int index = targetIndex(x);
		if (get(index) == null) size++;
		set(index, x);
	}

	@Override
	public void remove(T x) {
		// This is generally not possible to do efficiently, so we do it the easy way:
		// 1. Remove everything
		// 2. Add everything other than x back
		T[] values = elements;
		clear();
		for(T y: values) {
			if (y != null && y.compareTo(x) != 0) add(y);
		}
	}
}
