package dk.via.collections;

public interface List<T> {
	void add(T x);
	void add(int i, T x);
	T get(int i);
	void put(int i, T x);
	void remove(int i);
	int size();
}
