package dk.via.graphs;

public class UnweightedEdge {
	private int from, to;

	public UnweightedEdge(int from, int to) {
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
		if (obj instanceof UnweightedEdge) {
			UnweightedEdge other = (UnweightedEdge) obj;
			return from == other.from && to == other.to;
		}
		return false;
	}
}