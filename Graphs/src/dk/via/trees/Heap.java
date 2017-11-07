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
		if (index != root && index < size) {
			if (is_smaller(index, parent(index))) {
				swap(index, parent(index));
				rise(parent(index));
			}
		}
	}
	
	private void sink(int index) {
		if (get(right(index)) != null) {
			// Has both subtrees.
			if (is_smaller(left(index), index) && is_smaller(right(index), index)) {
				if (is_smaller(left(index), right(index))) {
					swap(index, left(index));
					sink(left(index));
				} else {
					swap(index, right(index));
					sink(right(index));
				}
			}  else if (is_smaller(left(index), index)) {
				swap(index, left(index));
				sink(left(index));
			} else if (is_smaller(right(index), index)) {
				swap(index, right(index));
				sink(right(index));
			}
		} else if (get(left(index)) != null) {
			// has only left subtree.
			if (is_smaller(left(index), index)) {
				swap(index, left(index));
			}
		} else {
			// already a leaf. Nothing to do here
		}
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T x) {
		if (x == null) 
			throw new NullPointerException();
		size++;
		set(size - 1, x);
		rise(size - 1);
	}

	@Override
	public T removeMin() {
		if (size == 0) 
			return null;
		T min = get(0);
		set(0, get(size - 1));
		size--;
		sink(0);
		return min;
	}
}
