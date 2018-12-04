package ast;

public class Assign extends Statement {

    public Identifier identifier;
    public Expression expression;

    public Assign(Identifier identifier, Expression expression) {

        this.identifier = identifier;
        this.expression = expression;

    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}