package dk.via.trees;

public class AVLTree<T extends Comparable<T>> implements SearchTree<T> {
	private Node<T> tree;
	private int size;

	public AVLTree() {
		this.tree = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	// For white-box testing of balance
	private int height(Node<T> n) {
		if (n == null)
			return -1;
		else
			return n.getHeight();
	}
	
	private boolean checkHeight(Node<T> n) {
		if (n == null) return true;
		if (!checkHeight(n.getLeft()) || !checkHeight(n.getRight())) return false;
		return height(n) == 1 + Math.max(height(n.getLeft()), height(n.getRight()));
	}
	
	boolean checkHeight() {
		return checkHeight(tree);
	}
	
	int getHeight() {
		return height(tree);
	}

	// Recursive methods
	private void updateHeight(Node<T> n) {
		n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
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
		if (x == null) 
			throw new IllegalArgumentException("Attempt to insert null in the tree");
		if (n == null) {
			size++;
			return new Node<T>(x);
		} else if (x.compareTo(n.getValue()) == 0) {
			return n;
		} else if (x.compareTo(n.getValue()) < 0) {
			Node<T> left = add(n.getLeft(), x);
			n.setLeft(left);
			updateHeight(n);
			return rebalance(n);
		} else {
			Node<T> right = add(n.getRight(), x);
			n.setRight(right);
			updateHeight(n);
			return rebalance(n);
		}
	}

	public Node<T> remove(Node<T> n, T x) {
		if (n == null)
			return n;
		else if (x.compareTo(n.getValue()) < 0) { 
			n.setLeft(remove(n.getLeft(), x));
			updateHeight(n);
		} else if (x.compareTo(n.getValue()) > 0) { 
			n.setRight(remove(n.getRight(), x));
			updateHeight(n);
		} else {
			size--;
			if (n.getRight() == null) {
				return n.getLeft();
			} else {
				Node<T> replacement = replacement(n.getRight());
				replacement.setLeft(n.getLeft());
				if (replacement != n.getRight()) 
					replacement.setRight(n.getRight());
				updateHeight(replacement);
				n = replacement;
			}
		}
		return rebalance(n);
	}
	
	private Node<T> replacement(Node<T> n) {
		if (n.getLeft() == null) {
			return n;
		} else {
			Node<T> replacement = replacement(n.getLeft());
			if (n.getLeft() == replacement) 
				n.setLeft(replacement.getRight());
			updateHeight(n);
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
	
	// Rotates clockwise
	private Node<T> rotateRight(Node<T> n) {
		Node<T> l = n.getLeft();
		if (l == null) {
			return n;
		} else {
			n.setLeft(l.getRight());
			l.setRight(n);
			updateHeight(n);
			updateHeight(l);
			return l;
		}
	}
	
	// Rotates counter-clockwise
	private Node<T> rotateLeft(Node<T> n) {
		Node<T> r = n.getRight();
		if (r == null) {
			return n;
		} else {
			n.setRight(r.getLeft());
			r.setLeft(n);
			updateHeight(n);
			updateHeight(r);
			return r;
		}
	}
	
	// Rotate the left subtree to the left
	// Then rotate the tree to the right
	private Node<T> rotateLeftRight(Node<T> n) {
		if (n.getLeft() != null) {
			n.setLeft(rotateLeft(n.getLeft()));
		}
		return rotateRight(n);
	}
	
	// Rotate the right subtree to the right
	// Then rotate the tree to the left
	private Node<T> rotateRightLeft(Node<T> n) {
		if (n.getRight() != null) {
			n.setRight(rotateRight(n.getRight()));
		}
		return rotateLeft(n);
	}
	
	// Balance this node according to the rules of the AVL tree
	private Node<T> rebalance(Node<T> n) {
		if (height(n.getLeft()) < height(n.getRight()) - 1) {
			if (height(n.getRight().getLeft()) <= height(n.getRight().getRight())) 
				return rotateLeft(n);
			else 
				return rotateRightLeft(n);
		} else if (height(n.getLeft()) -1 > height(n.getRight())) {
			if (height(n.getLeft().getRight()) <= height(n.getLeft().getLeft())) 
				return rotateRight(n);
			else 
				return rotateLeftRight(n);
		} else {
			return n;
		}
	}
}
