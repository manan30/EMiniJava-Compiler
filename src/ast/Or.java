package ast;

public class Or extends Expression {

    public Expression lhs;
    public Expression rhs;

    public Or(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}