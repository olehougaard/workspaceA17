package dk.via.graphs;

public interface Graph<NodeInfo> {
	public interface Edge {
		int getFrom();
		int getTo();
	}
	int getSize();
	int getEdgeCount();
	char getName(int i);
	NodeInfo getInfo(int i);
	Edge getEdge(int i, int j);
	Iterable<? extends Edge> getOutgoingEdges(int i);
	Iterable<? extends Edge> getIncomingEdges(int i);
	Iterable<? extends Edge> getEdges();
	void setInfo(int i, NodeInfo info);
	Iterable<Integer> getIncomingNodes(int j);
	Iterable<Integer> getOutgoingNodes(int i);
}
