package dk.via.trees;

public interface SearchTree<T extends Comparable<T>> {

	int size();

	T find(T x);

	void add(T x);

	void remove(T x);

}