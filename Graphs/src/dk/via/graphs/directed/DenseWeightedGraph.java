package dk.via.graphs.directed;

import java.util.Iterator;

import dk.via.graphs.WeightedGraph;

public class DenseWeightedGraph implements WeightedGraph {
	private int size;
	private double[][] edges;
	private int edgeCount;
	
	// O(V^2)
	public DenseWeightedGraph(int size) {
		this.size = size;
		this.edges = new double[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				edges[i][j] = 0.0;
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
	public void addEdge(int i, int j, double weight) {
		edges[i][j] = weight;
	}

	// O(1)
	@Override
	public double getWeight(int i, int j) {
		 return edges[i][j];
	}
	
	// O(1) to get, but O(V) to run through
	@Override
	public Iterable<Integer> getOutgoingNodes(int i) {
		return new Iterable<Integer>() {
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					private int j = 0;
					private void wind() {
						while(j < size && edges[i][j] == 0) j++;
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
						while(i < size && edges[i][j] == 0) i++;
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
	
	@Override
	public String toString() {
		return asString();
	}
}
