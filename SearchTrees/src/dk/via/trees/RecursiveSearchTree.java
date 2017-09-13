package dk.via.trees;

public class RecursiveSearchTree<T extends Comparable<T>> implements SearchTree<T> {
	private Node<T> tree;
	private int size;

	public RecursiveSearchTree() {
		this.tree = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	private T find(Node<T> n, T x) {
		if (n == null) {
			return null;
		} else if (x.compareTo(n.getValue()) == 0) {
			return n.getValue();
		} else if (x.compareTo(n.getValue()) < 0) {
			return find(n.getLeft(), x);
		} else {
			return find(n.getRight(), x);
		}
	}

	private Node<T> add(Node<T> n, T x) {
		if (n == null) {
			size++;
			return new Node<T>(x);
		} else if (x.compareTo(n.getValue()) == 0) {
			return n;
		} else if (x.compareTo(n.getValue()) < 0) {
			n.setLeft(add(n.getLeft(), x));
			return n;
		} else {
			n.setRight(add(n.getRight(), x));
			return n;
		}
	}

	public Node<T> remove(Node<T> n, T x) {
		if (n == null)
			return n;
		else if (x.compareTo(n.getValue()) < 0) { 
			n.setLeft(remove(n.getLeft(), x));
			return n;
		} else if (x.compareTo(n.getValue()) > 0) { 
			n.setRight(remove(n.getRight(), x));
			return n;
		} else {
			size--;
			if (n.getRight() == null) {
				return n.getLeft();
			} else {
				Node<T> replacement = replacement(n.getRight());
				replacement.setLeft(n.getLeft());
				if (replacement != n.getRight()) 
					replacement.setRight(n.getRight());
				return replacement;
			}
		}
	}
	
	private Node<T> replacement(Node<T> n) {
		if (n.getLeft() == null) {
			return n;
		} else {
			Node<T> replacement = replacement(n.getLeft());
			if (n.getLeft() == replacement) 
				n.setLeft(replacement.getRight());
			return replacement;
		}
	}

	@Override
	public T find(T x) {
		return find(tree, x);
	}
	
	@Override
	public void add(T x) {
		tree = add(tree, x);
	}

	@Override
	public void remove(T x) {
		tree = remove(tree, x);
	}
}

