package dk.via.graphs;

public interface Graph {
	public interface Edge {
		int getFrom();
		int getTo();
	}
	int getSize();
	int getEdgeCount();
	char getName(int i);
	Edge getEdge(int i, int j);
	Iterable<? extends Edge> getOutgoingEdges(int i);
	Iterable<? extends Edge> getIncomingEdges(int i);
	Iterable<? extends Edge> getEdges();
	Iterable<Integer> getIncomingNodes(int j);
	Iterable<Integer> getOutgoingNodes(int i);
}
