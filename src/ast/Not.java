package ast;

public class Not extends Expression {

    public Expression expr;

    public Not(Expression expr) {
        this.expr = expr;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}