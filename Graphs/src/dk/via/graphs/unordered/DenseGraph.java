package dk.via.graphs.unordered;

public class DenseGraph extends dk.via.graphs.ordered.DenseGraph {
	public DenseGraph(int size) {
		super(size);
	}
	
	// O(1)
	@Override
	public void addEdge(int i, int j) {
		super.addEdge(i, j);
		super.addEdge(j, i);
	}
}
