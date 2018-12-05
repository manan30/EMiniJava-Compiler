package ast;

public class VarDeclaration extends Tree {

    private Type varType;
    private Identifier varIdentifier;

    public VarDeclaration(Type varType, Identifier varIdentifier) {

        this.varType = varType;
        this.varIdentifier = varIdentifier;

    }

    public Type getVarType() {
        return varType;
    }

    public Identifier getVarIdentifier() {
        return varIdentifier;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
