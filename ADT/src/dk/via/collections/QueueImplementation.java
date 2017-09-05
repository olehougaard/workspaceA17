package dk.via.collections;

import java.util.LinkedList;

public class QueueImplementation<T> implements Queue<T> {
	private LinkedList<T> elements = new LinkedList<>();

	@Override
	public void enqueue(T x) {
		elements.add(x);
	}

	@Override
	public T dequeue() {
		return elements.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

}
