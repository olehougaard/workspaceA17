package dk.via.trees;

class Node<T> {
	private T value;
	private Node<T> left;
	private Node<T> right;
	private int weight;
	private int height;

	public Node(T value, Node<T> left, Node<T> right, int height, int weight) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.weight = weight;
	}
	
	public Node(T value) {
		this(value, null, null, 0, 1);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
