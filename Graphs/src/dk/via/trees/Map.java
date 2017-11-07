package dk.via.trees;

public interface Map<K, V> {
	V lookup(K key);
	void put(K key, V value);
	void delete(K key);
	int size();
}
