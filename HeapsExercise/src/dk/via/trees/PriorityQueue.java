package dk.via.trees;

public interface PriorityQueue<T extends Comparable<T>> {
	int size();

	void add(T x);

	T removeMin();
}