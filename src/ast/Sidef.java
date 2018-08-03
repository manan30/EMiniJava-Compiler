package ast;

public class Sidef extends Statement {

    public Expression printExpression;

    public Sidef(Expression printExpression) {
        this.printExpression = printExpression;
    }

    public Expression getPrintExpression() {
        return printExpression;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}