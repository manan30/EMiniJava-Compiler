package ast;

import java.util.List;

public class Dot extends Expression {

    private Expression lhs;
    private boolean length;
    private Identifier identifier;
    private List<Expression> expressionList;

    public Dot(Expression lhs, boolean length, Identifier identifier, List<Expression> expressionList) {

        this.lhs = lhs;
        this.length = length;
        this.identifier = identifier;
        this.expressionList = expressionList;

    }

    public Expression getLhs() {
        return lhs;
    }

    public boolean isLength() {
        return length;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
