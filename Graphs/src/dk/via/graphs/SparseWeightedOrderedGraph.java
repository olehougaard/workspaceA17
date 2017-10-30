package dk.via.graphs;

import java.util.LinkedList;
import java.util.List;

public class SparseWeightedOrderedGraph implements Graph {
	public static class Edge implements WeightedGraph.Edge {
		private int from, to;
		private double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int getFrom() {
			return from;
		}

		public int getTo() {
			return to;
		}
		
		public double getWeight() {
			return weight;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Edge) {
				Edge other = (Edge) obj;
				return from == other.from && to == other.to;
			}
			return false;
		}
	}
	
	private int size;
	private List<Edge>[] edges;
	private int edgeCount;
	
	@SuppressWarnings("unchecked")
	public SparseWeightedOrderedGraph(int size) {
		this.size = size;
		this.edges = new List[size];
		for(int i = 0; i < edges.length; i++) {
			edges[i] = new LinkedList<>();
		}
		this.edgeCount = 0;
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getEdgeCount() {
		return edgeCount;
	}

	@Override
	public char getName(int i) {
		return (char)('A' + i);
	}

	public void addEdge(int i, int j, double weight) {
		edges[i].add(new Edge(i, j, weight));
	}

	@Override
	public Edge getEdge(int i, int j) {
		for(Edge edge: edges[i]) {
			if (edge.getTo() == j) return edge;
		}
		return null;
	}
	
	public List<Integer> getOutgoingNodes(int i) {
		LinkedList<Integer> outgoing = new LinkedList<>();
		for(Edge edge: getOutgoingEdges(i)) {
			outgoing.add(edge.getTo());
		}
		return outgoing;
	}
	
	public Iterable<Integer> getIncomingNodes(int j) {
		LinkedList<Integer> incoming = new LinkedList<>();
		for(Edge edge: getIncomingEdges(j)) {
			incoming.add(edge.getFrom());
		}
		return incoming;
	}

	@Override
	public List<Edge> getOutgoingEdges(int i) {
		return edges[i];
	}

	@Override
	public List<Edge> getIncomingEdges(int i) {
		List<Edge> incoming = new LinkedList<>();
		for(List<Edge> outgoing: edges) {
			for(Edge edge: outgoing) {
				if (edge.getTo() == i) incoming.add(edge);
			}
		}
		return incoming;
	}

	@Override
	public List<Edge> getEdges() {
		List<Edge> allEdges = new LinkedList<>();
		for(List<Edge> outgoing: edges) {
			allEdges.addAll(outgoing);
		}
		return allEdges;
	}
}
