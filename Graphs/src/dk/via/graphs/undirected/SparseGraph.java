package dk.via.graphs.undirected;

public class SparseGraph extends dk.via.graphs.directed.SparseGraph {
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
