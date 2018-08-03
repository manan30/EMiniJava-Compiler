package ast;

public class Multiply extends Expression {

    public Expression lhs;
    public Expression rhs;

    public Multiply(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expression getLhs() {
        return lhs;
    }

    public Expression getRhs() {
        return rhs;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
