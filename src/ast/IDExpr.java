package ast;

public class IDExpr extends Expression {

    private String idName;

    public IDExpr(String idName) {
        this.idName = idName;
    }

    public String getIdName() {
        return idName;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
