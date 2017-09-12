package dk.via.trees;

public class IterativeSearchTree<T extends Comparable<T>> implements SearchTree<T> {
	private Node<T> tree;
	private int size;

	public IterativeSearchTree() {
		this.tree = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public T find(T x) {
		// Left as an exercise
		return null;
	}

	@Override
	public void add(T x) {
		if (tree == null) {
			tree = new Node<T>(x);
			size = 1;
		} else {
			Node<T> previousNode;
			Node<T> currentNode = tree;
			do {
				previousNode = currentNode;
				if (x.compareTo(currentNode.getValue()) < 0) {
					currentNode = currentNode.getLeft();
					if (currentNode == null) {
						currentNode = new Node<T>(x);
						previousNode.setLeft(currentNode);
						size++;
					}
				} else if(x.compareTo(currentNode.getValue()) > 0) {
					currentNode = currentNode.getRight();
					if (currentNode == null) {
						currentNode = new Node<T>(x);
						previousNode.setRight(currentNode);
						size++;
					}
				}
			} while (x.compareTo(currentNode.getValue()) != 0);
		}
	}
	
	@Override
	public void remove(T x) {
		Node<T> previousNode = null;
		Node<T> currentNode = tree;
		while (currentNode != null && x.compareTo(currentNode.getValue()) != 0) {
			previousNode = currentNode;
			if (x.compareTo(currentNode.getValue()) < 0) {
				currentNode = currentNode.getLeft();
			} else if(x.compareTo(currentNode.getValue()) > 0) {
				currentNode = currentNode.getRight();
			}
		}
		if (currentNode != null) {
			Node<T> newCurrent = replacement(currentNode);
			if (currentNode == tree) {
				tree = newCurrent;
			} else if (currentNode == previousNode.getLeft()) {
				previousNode.setLeft(newCurrent);
			} else if (currentNode == previousNode.getRight()) {
				previousNode.setRight(newCurrent);
			}
			size--;
		}
	}

	private Node<T> replacement(Node<T> currentNode) {
		Node<T> newCurrent;
		if (currentNode.getLeft() == null) {
			newCurrent = currentNode.getRight();
		} else if (currentNode.getRight() == null) {
			newCurrent = currentNode.getLeft();
		} else if (currentNode.getRight().getLeft() == null) {
			newCurrent = currentNode.getRight();
			newCurrent.setLeft(currentNode.getLeft());
		} else {
			Node<T> newParent = currentNode.getRight();
			newCurrent = currentNode.getRight().getLeft();
			while (newCurrent.getLeft() != null) {
				newParent = newCurrent;
				newCurrent = newCurrent.getLeft();
			}
			newParent.setLeft(newCurrent.getRight());
			newCurrent.setLeft(currentNode.getLeft());
			newCurrent.setRight(currentNode.getRight());
		}
		return newCurrent;
	}
}
