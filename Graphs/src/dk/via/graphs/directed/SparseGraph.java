package dk.via.graphs.directed;

import java.util.LinkedList;
import java.util.List;

import dk.via.graphs.Graph;
import dk.via.graphs.UnweightedEdge;

public class SparseGraph implements Graph {
	private int size;
	private List<UnweightedEdge>[] edges;
	private int edgeCount;
	
	// O(V)
	@SuppressWarnings("unchecked")
	public SparseGraph(int size) {
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
	public void addEdge(int i, int j) {
		edges[i].add(new UnweightedEdge(i, j));
	}

	// O(|outgoing(i)|)
	public List<Integer> getOutgoingNodes(int i) {
		LinkedList<Integer> outgoing = new LinkedList<>();
		for(UnweightedEdge edge: getOutgoingEdges(i)) {
			outgoing.add(edge.getTo());
		}
		return outgoing;
	}
	
	// O(E)
	public Iterable<Integer> getIncomingNodes(int j) {
		List<Integer> incoming = new LinkedList<>();
		for(List<UnweightedEdge> outgoing: edges) {
			for(UnweightedEdge edge: outgoing) {
				if (edge.getTo() == j) incoming.add(edge.getFrom());
			}
		}
		return incoming;
	}

	// O(1)
	public List<UnweightedEdge> getOutgoingEdges(int i) {
		return edges[i];
	}

	// O(E)
	public List<UnweightedEdge> getEdges() {
		List<UnweightedEdge> allEdges = new LinkedList<>();
		for(List<UnweightedEdge> outgoing: edges) {
			allEdges.addAll(outgoing);
		}
		return allEdges;
	}
	
	@Override
	public String toString() {
		return asString();
	}
}
