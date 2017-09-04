package dk.via.expressions;

public class Minus extends BinaryExpression {
	public Minus(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public double getValue() {
		return getLeft().getValue() - getRight().getValue();
	}

}
