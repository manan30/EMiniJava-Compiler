package ast;

public class Minus extends Expression {

    public Expression lhs;
    public Expression rhs;

    public Minus(Expression lhs, Expression rhs) {
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
