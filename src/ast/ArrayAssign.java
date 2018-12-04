package ast;

public class ArrayAssign extends Statement {

    private Identifier identifier;
    private Expression sizeExpression;
    private Expression expression;

    public ArrayAssign(Identifier identifier, Expression sizeExpression, Expression expression) {

        this.identifier = identifier;
        this.sizeExpression = sizeExpression;
        this.expression = expression;

    }

    public Identifier getIdentifier() {
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