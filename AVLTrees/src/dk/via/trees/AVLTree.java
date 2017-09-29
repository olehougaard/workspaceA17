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
	
	private int height(Node<T> n) {
		if (n == null)
			return -1;
		else
			return n.getHeight();
	}
	
	private int weight(Node<T> n) {
		if (n == null)
			return 0;
		else
			return n.getWeight();
	}
	
	// For white-box testing of balance
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
			n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
			n.setWeight(1 + weight(n.getLeft()) + weight(n.getRight()));
			return rebalance(n);
		} else {
			Node<T> right = add(n.getRight(), x);
			n.setRight(right);
			n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
			n.setWeight(1 + weight(n.getLeft()) + weight(n.getRight()));
			return rebalance(n);
		}
	}

	public Node<T> remove(Node<T> n, T x) {
		if (n == null)
			return n;
		else if (x.compareTo(n.getValue()) < 0) { 
			n.setLeft(remove(n.getLeft(), x));
			n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
			n.setWeight(1 + weight(n.getLeft()) + weight(n.getRight()));
		} else if (x.compareTo(n.getValue()) > 0) { 
			n.setRight(remove(n.getRight(), x));
			n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
			n.setWeight(1 + weight(n.getLeft()) + weight(n.getRight()));
		} else {
			size--;
			if (n.getRight() == null) {
				return n.getLeft();
			} else {
				Node<T> replacement = replacement(n.getRight());
				replacement.setLeft(n.getLeft());
				if (replacement != n.getRight()) 
					replacement.setRight(n.getRight());
				replacement.setHeight(1 + Math.max(height(replacement.getLeft()), height(replacement.getRight())));
				n = replacement;
			}
		}
		return rebalance(n);
	}
	
	private T smallest(Node<T> node) {
		if (node == null)
			return null;
		else if (node.getLeft() == null)
			return node.getValue();
		else
			return smallest(node.getLeft());
	}
	
	private T nthSmallest(Node<T> node, int n) {
		if (node == null)
			return null;
		else if (weight(node.getLeft()) == n) 
			return node.getValue();
		else if (weight(node.getLeft()) > n)
			return nthSmallest(node.getLeft(), n);
		else 
			return nthSmallest(node.getRight(), n - weight(node.getLeft()) - 1);
	}
	
	private Node<T> replacement(Node<T> n) {
		if (n.getLeft() == null) {
			return n;
		} else {
			Node<T> replacement = replacement(n.getLeft());
			if (n.getLeft() == replacement) 
				n.setLeft(replacement.getRight());
			n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
			n.setWeight(1 + weight(n.getLeft()) + weight(n.getRight()));
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
	
	public T smallest() {
		return smallest(tree);
	}
	
	public T nthSmallest(int n) {
		return nthSmallest(tree, n);
	}
	
	// Rotates clockwise
	private Node<T> rotateRight(Node<T> n) {
		Node<T> l = n.getLeft();
		if (l == null) {
			return n;
		} else {
			n.setLeft(l.getRight());
			l.setRight(n);
			n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
			l.setHeight(1 + Math.max(height(l.getLeft()), height(l.getRight())));
			n.setWeight(1 + weight(n.getLeft()) + weight(n.getRight()));
			l.setWeight(1 + weight(l.getLeft()) + weight(l.getRight()));
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
			n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
			r.setHeight(1 + Math.max(height(r.getLeft()), height(r.getRight())));
			n.setWeight(1 + weight(n.getLeft()) + weight(n.getRight()));
			r.setWeight(1 + weight(r.getLeft()) + weight(r.getRight()));
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
