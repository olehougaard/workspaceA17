package dk.via.graphs;

public interface WeightedGraph extends Graph {
	double getWeight(int i, int j);
	default String asString() {
		StringBuffer buffer = new StringBuffer();
		for(int v = 0; v < getSize(); v++) {
			buffer.append(getName(v)).append(" -> [");
			for(int w: getOutgoingNodes(v)) {
				buffer.append(getName(w)).append(" (").append(getWeight(v, w)).append("), ");
			}
			buffer.append("]\n");
		}
		return buffer.toString();
	}
}
