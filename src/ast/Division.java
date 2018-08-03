package ast;

public class Division extends Expression {

    public Expression lhs;
    public Expression rhs;

    public Division(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
