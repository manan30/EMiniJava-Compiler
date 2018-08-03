package ast;

public class While extends Statement {

    public Expression expression;
    public Statement body;

    public While(Expression expression, Statement body) {

        this.expression = expression;
        this.body = body;

    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getBody() {
        return body;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}