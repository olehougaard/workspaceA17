package dk.via.graphs;

public class WeightedEdge extends UnweightedEdge implements Comparable<WeightedEdge> {
	private double weight;

	public WeightedEdge(int from, int to, double weight) {
		super(from, to);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WeightedEdge) {
			WeightedEdge other = (WeightedEdge) obj;
			return super.equals(other) && weight == other.weight;
		}
		return false;
	}

	@Override
	public int compareTo(WeightedEdge o) {
		return Double.compare(weight, o.weight);
	}
}