package dk.via.expressions;

public class Times extends BinaryExpression {
	public Times(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public double getValue() {
		return getLeft().getValue() * getRight().getValue();
	}

}
