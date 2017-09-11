package dk.via.trees;

public class SearchTree<T extends Comparable<T>> {
	private Node<T> tree;

	public SearchTree() {
		this.tree = null;
	}

	public T find(T x) {
		Node<T> currentNode = tree;
		while (currentNode != null) {
			if (x.compareTo(currentNode.getValue()) == 0) {
				return currentNode.getValue();
			} else if (x.compareTo(currentNode.getValue()) < 0) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRight();
			}
		}
		return null;
	}

	public void add(T x) {
		if (tree == null) {
			tree = new Node<T>(x);
		} else {
			Node<T> previousNode;
			Node<T> currentNode = tree;
			do {
				if (x.compareTo(currentNode.getValue()) == 0) {
					return;
				} else {
					previousNode = currentNode;
					if (x.compareTo(currentNode.getValue()) < 0) {
						currentNode = currentNode.getLeft();
					} else {
						currentNode = currentNode.getRight();
					}
				}
			} while (currentNode != null);
			if (x.compareTo(previousNode.getValue()) < 0) {
				previousNode.setLeft(new Node<T>(x));
			} else {
				previousNode.setRight(new Node<T>(x));
			}
		}
	}
	
	private Node<T> addRecursively(Node<T> n, T x) {
		return new Node<>(x);
	}
}
