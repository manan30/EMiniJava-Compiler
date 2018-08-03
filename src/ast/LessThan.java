package ast;

public class LessThan extends Expression {

    public Expression lhs;
    public Expression rhs;

    public LessThan(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }
    
}
