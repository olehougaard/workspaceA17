package dk.via.graphs.unordered;

public class SparseGraph extends dk.via.graphs.ordered.SparseGraph {
	public SparseGraph(int size) {
		super(size);
	}
	
	// O(2)
	@Override
	public void addEdge(int i, int j) {
		super.addEdge(i, j);
		super.addEdge(j, i);
	}
}
