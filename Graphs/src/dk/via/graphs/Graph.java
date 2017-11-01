package dk.via.graphs;

public interface Graph {
	int getSize();
	int getEdgeCount();
	char getName(int i);
	Iterable<Integer> getIncomingNodes(int j);
	Iterable<Integer> getOutgoingNodes(int i);
}
