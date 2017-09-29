package dk.via.trees;

@SuppressWarnings("unchecked")
public class Heap<T extends Comparable<T>> implements PriorityQueue<T> {
	private T[] elements = (T[]) new Comparable[1];
	private int size = 0;
	private final int root = 0;
	
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
	
	private boolean is_smaller(int i, int j) {
		return get(i).compareTo(get(j)) < 0;
	}
	
	private int parent(int index) {
	}
	
	private int left(int index) {
	}
	
	private int right(int index) {
	}
	
	private void swap(int i, int j) {
	}
	
	private void rise(int index) {
	}
	
	private void sink(int index) {
	}
	
	@Override
	public int size() {
	}

	@Override
	public void add(T x) {
	}

	@Override
	public T removeMin() {
	}
}
