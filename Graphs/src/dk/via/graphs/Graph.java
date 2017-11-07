package dk.via.graphs;

import java.util.LinkedList;

public interface Graph {
	int getSize();
	int getEdgeCount();
	char getName(int i);
	Iterable<Integer> getIncomingNodes(int j);
	Iterable<Integer> getOutgoingNodes(int i);
	default String asString() {
		StringBuffer buffer = new StringBuffer();
		for(int v = 0; v < getSize(); v++) {
			buffer.append(getName(v));
			buffer.append(" -> ");
			LinkedList<Character> outGoingNodeNames = new LinkedList<>();
			for(int w: getOutgoingNodes(v))
				outGoingNodeNames.add(getName(w));
			buffer.append(outGoingNodeNames.toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
