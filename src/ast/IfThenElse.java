package ast;

public class IfThenElse extends Statement {

    public Expression expression;
    public Statement thenStatement;
    public Statement elseStatement;

    public IfThenElse(Expression expression, Statement thenStatement, Statement elseStatement) {

        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;

    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getThenStatement() {
        return thenStatement;
    }

    public Statement getElseStatement() {
        return elseStatement;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}