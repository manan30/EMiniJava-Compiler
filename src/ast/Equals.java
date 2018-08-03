package ast;

public class Equals extends Expression {

    public Expression lhs;
    public Expression rhs;

    public Equals(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
