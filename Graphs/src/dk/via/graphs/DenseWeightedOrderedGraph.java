package dk.via.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DenseWeightedOrderedGraph<NodeInfo> implements WeightedGraph<NodeInfo> {
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
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Edge) {
				Edge other = (Edge) obj;
				return from == other.from && to == other.to;
			}
			return false;
		}

		@Override
		public double getWeight() {
			return weight;
		}
	}
	
	private int size;
	private NodeInfo[] info;
	private double[][] edges;
	private int edgeCount;
	
	@SuppressWarnings("unchecked")
	public DenseWeightedOrderedGraph(int size) {
		this.size = size;
		this.info = (NodeInfo[]) new Object[size];
		this.edges = new double[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				edges[i][j] = 0.0;
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

	@Override
	public NodeInfo getInfo(int i) {
		return info[i];
	}
	
	@Override
	public void setInfo(int i, NodeInfo info) {
		this.info[i] = info;
	}
	
	public void addEdge(int i, int j, double weight) {
		edges[i][j] = weight;
	}

	@Override
	public Edge getEdge(int i, int j) {
		if (edges[i][j] != 0) 
			return new Edge(i, j, edges[i][j]);
		else
			return null;
	}
	
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
	public List<Edge> getOutgoingEdges(int i) {
		LinkedList<Edge> outgoing = new LinkedList<>();
		for(int j = 0; j < size; j++) {
			if (edges[i][j] != 0)
				outgoing.add(new Edge(i, j, edges[i][j]));
		}
		return outgoing;
	}

	@Override
	public List<Edge> getIncomingEdges(int j) {
		LinkedList<Edge> outgoing = new LinkedList<>();
		for(int i = 0; i < size; i++) {
			if (edges[i][j] != 0)
				outgoing.add(new Edge(i, j, edges[i][j]));
		}
		return outgoing;
	}

	@Override
	public List<Edge> getEdges() {
		List<Edge> allEdges = new LinkedList<>();
		for(int i = 0; i < size; i++) {
			allEdges.addAll(getOutgoingEdges(i));
		}
		return allEdges;
	}
}
