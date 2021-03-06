package ast;

public class Print extends Statement {

    public Expression printExpression;

    public Print(Expression printExpression) {
        this.printExpression = printExpression;
    }

    public Expression getPrintExpression() {
        return printExpression;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}