package dk.via.trees;

public class NodeImplementation<T> implements Node<T> {
	private T value;
	private Node<T> left, right;
	
	public NodeImplementation(T value, Node<T> left, Node<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public boolean isLeaf() {
		return left == null && right == null;
	}

	@Override
	public Node<T> getLeft() {
		return left;
	}

	@Override
	public Node<T> getRight() {
		return right;
	}
}
