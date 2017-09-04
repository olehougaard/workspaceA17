package dk.via.expressions;

public class Division extends BinaryExpression {
	public Division(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public double getValue() {
		return getLeft().getValue() / getRight().getValue();
	}

}
