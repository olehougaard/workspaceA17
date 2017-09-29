package dk.via.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class HeapTest {
	private static Random random = new Random();

	private static Set<Integer> randomTestData() {
		Set<Integer> data = new HashSet<>();
		int size = random.nextInt(50) + 1;
		while(data.size() < size) 
			data.add(random.nextInt());
		return data;
	}
	
	@Test
	public void testSearchEmpty() {
		Heap<Integer> tree = new Heap<>();
		assertEquals(0, tree.size());
		assertNull(tree.removeMin());
	}

	@Test
	public void addIncrementsSize() throws Exception {
		Heap<Integer> tree = new Heap<>();
		Set<Integer> testData = randomTestData();
		for(Integer i: testData)
			tree.add(i);
		assertEquals(testData.size(), tree.size());
	}
	
	@Test
	public void removeMinReturnsInOrder() throws Exception {
		Heap<Integer> tree = new Heap<>();
		Set<Integer> testData = randomTestData();
		for(Integer i: testData)
			tree.add(i);
		List<Integer> sortedData = new ArrayList<>(testData);
		Collections.sort(sortedData);
		for(Integer i: sortedData) {
			assertEquals(i, tree.removeMin());
		}
	}

	@Test
	public void removeMinDecrementsSize() throws Exception {
		Heap<Integer> tree = new Heap<>();
		Set<Integer> testData = randomTestData();
		for(Integer i: testData)
			tree.add(i);
		int size = testData.size();
		while(tree.size() > 0) {
			tree.removeMin();
			assertEquals(--size, tree.size());
		}
	}
}
