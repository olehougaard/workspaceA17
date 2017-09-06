package dk.via.trees;

import dk.via.collections.Queue;
import dk.via.collections.QueueImplementation;
import dk.via.collections.Stack;
import dk.via.collections.StackImplementation;

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
	
	public static<T> void printPreOrder(Node<T> root) {
		if (root == null) return;
		System.out.println(root.getValue());
		printPreOrder(root.getLeft());
		printPreOrder(root.getRight());
	}
	
	public static<T> void printLevelOrder(Node<T> root) {
		if (root == null) return;
		Queue<Node<T>> queue = new QueueImplementation<>();
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			Node<T> node = queue.dequeue();
			System.out.println(node.getValue());
			if (node.getLeft() != null) queue.enqueue(node.getLeft());
			if (node.getRight() != null) queue.enqueue(node.getRight());
		}
	}
	
	public static<T> void printSomeOrder(Node<T> root) {
		if (root == null) return;
		Stack<Node<T>> stack = new StackImplementation<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node<T> node = stack.pop();
			System.out.println(node.getValue());
			if (node.getRight() != null) stack.push(node.getRight());
			if (node.getLeft() != null) stack.push(node.getLeft());
		}
	}
	
	public static void main(String[] args) {
		Node<Integer> one = new NodeImplementation<Integer>(1, null, null);
		Node<Integer> four = new NodeImplementation<Integer>(4, null, null);
		Node<Integer> nine = new NodeImplementation<Integer>(9, null, null);
		Node<Integer> fourteen = new NodeImplementation<Integer>(14, null, null);
		
		Node<Integer> two = new NodeImplementation<Integer>(2, one, four);
		Node<Integer> twelve = new NodeImplementation<Integer>(12, nine, fourteen);
		
		Node<Integer> root = new NodeImplementation<Integer>(7, two, twelve);
		
		printSomeOrder(root);
	}
}
