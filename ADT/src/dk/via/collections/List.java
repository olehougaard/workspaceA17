package dk.via.collections;

import java.util.Iterator;

public interface List<T> {
	/*
	 * ArrayList: O(1)
	 * LinkedList: O(n)
	 */
	T get(int i);
	
	/*
	 * ArrayList, LinkedList: Running through list = O(n)
	 */
	Iterator<T> iterator();
	
	/*
	 * ArrayList: Worst-case: O(n) (Average: O(1))
	 * LinkedList: O(1)
	 */
	void add(T x);
	
	/*
	 * ArrayList: O(n)
	 * LinkedList: O(n)
	 */
	void add(int i, T x);
	
	/*
	 * ArrayList: O(1)
	 * LinkedList: O(n)
	 */
	void put(int i, T x);
	
	/*
	 * ArrayList: O(n)
	 * LinkedList: O(n)
	 */
	void remove(int i);

	/*
	 * ArrayList, LinkedList: O(1)
	 */
	int size();
}
