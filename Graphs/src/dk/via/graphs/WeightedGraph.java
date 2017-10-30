package dk.via.graphs;

import java.util.List;

public interface WeightedGraph extends Graph {
	interface Edge extends Graph.Edge {
		double getWeight();
	}
	List<? extends Edge> getOutgoingEdges(int i);
	List<? extends Edge> getIncomingEdges(int i);
	List<? extends Edge> getEdges();
}
