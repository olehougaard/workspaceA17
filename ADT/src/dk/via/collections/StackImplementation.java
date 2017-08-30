package dk.via.collections;

import java.util.LinkedList;

public class StackImplementation<T> implements Stack<T> {
	private LinkedList<T> elements = new LinkedList<>();
	
	@Override
	public void push(T x) {
		elements.add(x);
	}

	@Override
	public T pop() {
		return elements.removeLast();
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

}
