package dk.via.collections;

public interface Queue<T> {
	void enqueue(T x);
	T dequeue();
	boolean isEmpty();
}
