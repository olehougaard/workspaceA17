package dk.via.graphs.ordered;

import java.util.Iterator;

import dk.via.graphs.Graph;

public class DenseGraph implements Graph {
	private int size;
	private boolean[][] edges;
	private int edgeCount;
	
	// O(V^2)
	public DenseGraph(int size) {
		this.size = size;
		this.edges = new boolean[size][size];
		this.edgeCount = 0;
	}
	
	// O(1)
	@Override
	public int getSize() {
		return size;
	}

	// O(1)
	@Override
	public int getEdgeCount() {
		return edgeCount;
	}

	// O(1)
	@Override
	public char getName(int i) {
		return (char)('A' + i);
	}

	// O(1)
	public void addEdge(int i, int j) {
		edges[i][j] = true;
	}

	// O(1) to get, but O(V) to run through
	@Override
	public Iterable<Integer> getOutgoingNodes(int i) {
		return new Iterable<Integer>() {
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					private int j = 0;
					private void wind() {
						while(j < size && !edges[i][j]) j++;
					}
					{
						wind();
					}
					public Integer next() {
						int next = j;
						wind();
						return next;
					}
					public boolean hasNext() {
						return j < size;
					}
				};
			}
		};
	}

	
	// O(1) to get, but O(V) to run through
	@Override
	public Iterable<Integer> getIncomingNodes(int j) {
		return new Iterable<Integer>() {
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					private int i = 0;
					private void wind() {
						while(i < size && !edges[i][j]) i++;
					}
					{
						wind();
					}
					public Integer next() {
						int next = i;
						wind();
						return next;
					}
					public boolean hasNext() {
						return i < size;
					}
				};
			}
		};
	}
}
