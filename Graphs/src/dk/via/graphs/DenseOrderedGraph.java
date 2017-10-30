package dk.via.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DenseOrderedGraph<NodeInfo> implements Graph<NodeInfo> {
	public static class Edge implements Graph.Edge {
		private int from, to;

		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
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
	}
	
	private int size;
	private NodeInfo[] info;
	private boolean[][] edges;
	private int edgeCount;
	
	@SuppressWarnings("unchecked")
	public DenseOrderedGraph(int size) {
		this.size = size;
		this.info = (NodeInfo[]) new Object[size];
		this.edges = new boolean[size][size];
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
	
	public void addEdge(int i, int j) {
		edges[i][j] = true;
	}

	@Override
	public Edge getEdge(int i, int j) {
		if (edges[i][j]) 
			return new Edge(i, j);
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
						while(j < size && !edges[i][j]) j++;
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
						while(i < size && !edges[i][j]) i++;
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
			if (edges[i][j])
				outgoing.add(new Edge(i, j));
		}
		return outgoing;
	}

	@Override
	public List<Edge> getIncomingEdges(int j) {
		LinkedList<Edge> outgoing = new LinkedList<>();
		for(int i = 0; i < size; i++) {
			if (edges[i][j])
				outgoing.add(new Edge(i, j));
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
