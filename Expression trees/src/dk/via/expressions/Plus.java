package dk.via.expressions;

public class Plus extends BinaryExpression {
	public Plus(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public double getValue() {
		return getLeft().getValue() + getRight().getValue();
	}

}
