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
	
	@Override
	public String toString() {
		String s = "";
		if (left != null) {
			s += left.toString() + ", ";
		}
		s += value;
		if (right != null) {
			s += ", " + right.toString();
		}
		return s;
	}

	public static void main(String[] args) {
		Node<Integer> minusNine = new NodeImplementation<>(-9, null, null);
		Node<Integer> four = new NodeImplementation<>(4, null, null);
		Node<Integer> two = new NodeImplementation<Integer>(2, minusNine, four);
		System.out.println(two);
	}
}
