package ast;

public class ArrayAssign extends Statement {

    public String identifier;
    public Expression sizeExpression;
    public Expression expression;

    public ArrayAssign(String identifier, Expression sizeExpression, Expression expression) {

        this.identifier = identifier;
        this.sizeExpression = sizeExpression;
        this.expression = expression;

    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression getSizeExpression() {
        return sizeExpression;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}