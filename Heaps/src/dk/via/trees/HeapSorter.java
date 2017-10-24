package dk.via.trees;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class HeapSorter<T extends Comparable<T>> {
	private T[] elements = (T[]) new Comparable[1];
	private int size = 0;
	private final int root = 0;
	
	private HeapSorter(T[] elements) {
		this.elements = elements;
		for(int i = 0; i < elements.length; i++) {
			this.size++;
			rise(i);
		}
	}
	
	private void set(int index, T x) {
		while(index >= elements.length) {
			T[] temp = (T[]) new Comparable[2 * elements.length + 1];
			System.arraycopy(elements, 0, temp, 0, elements.length);
			elements = temp;
		}
		elements[index] = x;
	}

	private T get(int index) {
		if (index >= size)
			return null;
		else
			return elements[index];
	}
	
	private boolean is_bigger(int i, int j) {
		return get(i).compareTo(get(j)) > 0;
	}
	
	private int parent(int index) {
		return (index - 1) / 2;
	}
	
	private int left(int index) {
		return 2 * index + 1;
	}
	
	private int right(int index) {
		return 2 * index + 2;
	}
	
	private void swap(int i, int j) {
		T temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}
	
	private void rise(int index) {
		while (index != root) {
			if (is_bigger(index, parent(index))) {
				swap(index, parent(index));
				index = parent(index);
			} else {
				return;
			}
		}
	}
	
	private void sink(int index) {
		while (get(left(index)) != null) {
			// Not a leaf
			if (is_bigger(left(index), index)) {
				if (get(right(index)) != null && is_bigger(right(index), left(index))) {
					swap(index, right(index));
					index = right(index);
				} else {
					swap(index, left(index));
					index = left(index);
				}
			} else if (get(right(index)) != null && is_bigger(right(index), index)){
				swap(index, right(index));
				index = right(index);
			} else {
				return;
			}
		}
	}
	
	public static<T extends Comparable<T>> void sort(T[] a) {
		HeapSorter<T> sorter = new HeapSorter<>(a);
		while(sorter.size > 0) {
			sorter.swap(0, sorter.size - 1);
			sorter.size--;
			sorter.sink(0);
		}
	}
	
	public static void main(String[] args) {
		Integer[] values = {7, 2, 100, -19, 5, 88};
		sort(values);
		System.out.println(Arrays.asList(values));
	}
}
