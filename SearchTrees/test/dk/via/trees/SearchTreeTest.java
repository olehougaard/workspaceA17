package dk.via.trees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchTreeTest {

	private SearchTree<Integer> tree;

	@Before
	public void setUp() {
		tree = new SearchTree<>();
	}
	
	@Test
	public void testSearchEmpty() {
		assertNull(tree.find(7));
	}

	@Test
	public void testAddAndFind() throws Exception {
		tree.add(7);
		assertEquals(Integer.valueOf(7), tree.find(7));
	}
	
	@Test
	public void testAddSmaller() throws Exception {
		tree.add(7);
		tree.add(2);
		assertEquals(Integer.valueOf(2), tree.find(2));
	}
}
