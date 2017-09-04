package dk.via.expressions;

public class UnaryMinus extends UnaryExpression {

	public UnaryMinus(Expression child) {
		super(child);
	}

	@Override
	public double getValue() {
		return - getChild().getValue();
	}
}
