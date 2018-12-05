package ast;

public class Boolean extends Expression {

    String boolType;

    public Boolean(String boolType) {
        this.boolType = boolType;
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
