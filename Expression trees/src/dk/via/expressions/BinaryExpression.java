package dk.via.expressions;

public abstract class BinaryExpression implements Expression {
	private Expression left;
	private Expression right;
	
	public BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
}
