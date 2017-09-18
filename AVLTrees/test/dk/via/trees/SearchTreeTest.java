package dk.via.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class SearchTreeTest {
	private static Random random = new Random();

	private static Set<Integer> randomTestData() {
		Set<Integer> data = new HashSet<>();
		int size = random.nextInt(500) + 1;
		while(data.size() < size) 
			data.add(random.nextInt());
		return data;
	}
	
	private AVLTree<Integer> createTree() {
		return new AVLTree<>();
	}
	
	@Test
	public void testSearchEmpty() {
		SearchTree<Integer> tree = createTree();
		assertNull(tree.find(random.nextInt()));
		assertEquals(0, tree.size());
	}

	@Test
	public void allAddedDataCanBeFound() throws Exception {
		for(int i = 0; i < 10; i++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) 
				tree.add(element);
			for(Integer element: testData)
				assertEquals(element, tree.find(element));
		}
	}

	@Test
	public void nonAddedDataCannotBeFound() throws Exception {
		for(int k = 0; k < 10; k++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			Integer nonAdded = testData.iterator().next();
			testData.remove(nonAdded);
			for(Integer element: testData) 
				tree.add(element);
			assertNull(tree.find(nonAdded));
		}
	}
	
	@Test
	public void sizeCountsTheAddedData() throws Exception {
		for(int i = 0; i < 10; i++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) 
				tree.add(element);
			assertEquals(testData.size(), tree.size());
		}
	}
	
	@Test
	public void DataIsNotAddedTwice() throws Exception {
		for(int i = 0; i < 10; i++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) 
				tree.add(element);
			for(Integer element: testData) 
				tree.add(element);
			assertEquals(testData.size(), tree.size());
		}
	}
	
	@Test
	public void RemovedDataCannotBeFound() throws Exception {
		for(int k = 0; k < 10; k++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) 
				tree.add(element);
			Integer removed = testData.iterator().next();
			tree.remove(removed);
			assertNull(tree.find(removed));
		}
	}
	
	@Test
	public void RemoveOnlyRemovesOne() throws Exception {
		for(int k = 0; k < 10; k++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) 
				tree.add(element);
			Integer removed = testData.iterator().next();
			tree.remove(removed);
			testData.remove(removed);
			for(Integer element: testData)
				assertEquals(element, tree.find(element));
		}
	}
	
	@Test
	public void RemoveDecrementsSize() throws Exception {
		for(int k = 0; k < 10; k++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) 
				tree.add(element);
			Integer removed = testData.iterator().next();
			tree.remove(removed);
			assertEquals(testData.size() - 1, tree.size());
		}
	}
	
	@Test
	public void RemoveNonexistingDoesNotChangeSize() throws Exception {
		for(int k = 0; k < 10; k++) {
			SearchTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			Integer nonAdded = testData.iterator().next();
			testData.remove(nonAdded);
			for(Integer element: testData) 
				tree.add(element);
			tree.remove(nonAdded);
			assertEquals(testData.size(), tree.size());
		}
	}

	@Test
	public void heightIsComputedCorrectly() throws Exception {
		for(int i = 0; i < 10; i++) {
			AVLTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) 
				tree.add(element);
			assertTrue(tree.checkHeight());
		}
	}
	
	private static Map<Integer, Integer> fibs = new HashMap<>();
	static {
		fibs.put(1, 1);
		fibs.put(2, 1);
	}
	private static int f(int n){
		if (!fibs.containsKey(n)) {
			fibs.put(n, f(n-1) + f(n-2));
		}
		return fibs.get(n);
	} 
	
	@Test
	public void treeIsBalancedThroughInsertion() throws Exception {
		for(int i = 0; i < 10; i++) {
			AVLTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) {
				tree.add(element);
				assertTrue(tree.size() >= f(tree.getHeight() + 2) - 1);
			}
		}
	}
	
	@Test
	public void treeIsBalancedThroughRemoval() throws Exception {
		for(int i = 0; i < 10; i++) {
			AVLTree<Integer> tree = createTree();
			Set<Integer> testData = randomTestData();
			for(Integer element: testData) {
				tree.add(element);
			}
			for(Integer element: testData) {
				tree.remove(element);
				assertTrue(tree.size() >= f(tree.getHeight() + 2) - 1);
			}
		}
	}
}
