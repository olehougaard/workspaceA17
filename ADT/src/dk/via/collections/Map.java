package dk.via.collections;

public interface Map<K, V> {
	V lookup(K key);
	void insert(K key, V value);
	void delete(K key);
	int size();
}
