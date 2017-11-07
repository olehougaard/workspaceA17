package dk.via.trees;

public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
	public static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>>{
		private K key;
		private V value;
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() { return key; }
		public V getValue() { return value; }
		@Override
		public int compareTo(Entry<K, V> o) {
			return key.compareTo(o.key);
		}
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			return obj instanceof Entry && compareTo((Entry<K, V>)obj) == 0;
		}
		@Override
		public int hashCode() {
			return key.hashCode();
		}
	}
	
	private AVLTree<Entry<K, V>> tree;
	
	public AVLMap() {
		tree = new AVLTree<>();
	}

	@Override
	public V lookup(K key) {
		Entry<K, V> found = tree.find(new Entry<>(key, null));
		if (found == null)
			return null;
		else
			return found.getValue();
	}

	@Override
	public void put(K key, V value) {
		tree.add(new Entry<>(key, value));
	}

	@Override
	public void delete(K key) {
		tree.remove(new Entry<>(key, null));
	}

	@Override
	public int size() {
		return tree.size();
	}
	
	public static void main(String[] args) {
		AVLMap<Integer, String> map = new AVLMap<>();
		map.put(7, "Seven");
		map.put(2, "Two");
		System.out.println(map.lookup(2));
		System.out.println(map.lookup(7));
		map.delete(2);
		System.out.println(map.lookup(2));
		System.out.println(map.lookup(7));
	}
}
