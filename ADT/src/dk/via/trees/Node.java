package dk.via.trees;

public interface Node<T> {
	T getValue();
	boolean isLeaf();
	Node<T> getLeft();
	Node<T> getRight();
}
