package ast;

public class Times extends Expression {

    public Expression lhs;
    public Expression rhs;

    public Times(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
