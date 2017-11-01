package dk.via.graphs.unordered;

public class DenseWeightedGraph extends dk.via.graphs.ordered.DenseWeightedGraph {
	public DenseWeightedGraph(int size) {
		super(size);
	}
	
	// O(1)
	@Override
	public void addEdge(int i, int j, double weight) {
		super.addEdge(i, j, weight);
		super.addEdge(j, i, weight);
	}
}
