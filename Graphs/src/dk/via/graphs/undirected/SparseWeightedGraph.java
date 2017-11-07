package dk.via.graphs.undirected;

public class SparseWeightedGraph extends dk.via.graphs.directed.SparseWeightedGraph {
	public SparseWeightedGraph(int size) {
		super(size);
	}

	// O(1)
	@Override
	public void addEdge(int i, int j, double weight) {
		super.addEdge(i, j, weight);
		super.addEdge(j, i, weight);
	}
}
