package dk.via.graphs.directed;

import java.util.LinkedList;
import java.util.List;

import dk.via.graphs.WeightedEdge;
import dk.via.graphs.WeightedGraph;

public class SparseWeightedGraph implements WeightedGraph {
	private int size;
	private List<WeightedEdge>[] edges;
	private int edgeCount;
	
	// O(V)
	@SuppressWarnings("unchecked")
	public SparseWeightedGraph(int size) {
		this.size = size;
		this.edges = new List[size];
		for(int i = 0; i < edges.length; i++) {
			edges[i] = new LinkedList<>();
		}
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
		edges[i].add(new WeightedEdge(i, j, weight));
	}

	// O(1)
	public void addEdge(WeightedEdge e) {
		addEdge(e.getFrom(), e.getTo(), e.getWeight());
	}

	// O(|outgoing(i)|)
	@Override
	public double getWeight(int i, int j) {
		for(WeightedEdge edge: edges[i]) {
			if (edge.getTo() == j) return edge.getWeight();
		}
		return 0;
	}
	
	// O(|outgoing(i)|)
	public List<Integer> getOutgoingNodes(int i) {
		LinkedList<Integer> outgoing = new LinkedList<>();
		for(WeightedEdge edge: getOutgoingEdges(i)) {
			outgoing.add(edge.getTo());
		}
		return outgoing;
	}
	
	// O(E)
	public Iterable<Integer> getIncomingNodes(int j) {
		List<Integer> incoming = new LinkedList<>();
		for(List<WeightedEdge> outgoing: edges) {
			for(WeightedEdge edge: outgoing) {
				if (edge.getTo() == j) incoming.add(edge.getFrom());
			}
		}
		return incoming;
	}

	// O(1)
	public List<WeightedEdge> getOutgoingEdges(int i) {
		return edges[i];
	}

	// O(E)
	public List<WeightedEdge> getEdges() {
		List<WeightedEdge> allEdges = new LinkedList<>();
		for(List<WeightedEdge> outgoing: edges) {
			allEdges.addAll(outgoing);
		}
		return allEdges;
	}
	
	@Override
	public String toString() {
		return asString();
	}
}
