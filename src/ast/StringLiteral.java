package ast;

public class StringLiteral extends Expression {

    public String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
