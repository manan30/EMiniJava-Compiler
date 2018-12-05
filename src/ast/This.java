package ast;

public class This extends Expression {

    public This() {
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
