package dk.via.expressions;

public class Constant implements Expression {
	private double value;
	
	public Constant(double value) {
		this.value = value;
	}
}
