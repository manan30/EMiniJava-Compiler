package ast;

public class VarDeclaration extends Tree {

    public Type type;
    public String varIdentifier;

    public VarDeclaration(Type type, String varIdentifier) {

        this.type = type;
        this.varIdentifier = varIdentifier;

    }

    public Type getType() {
        return type;
    }

    public String getVarIdentifier() {
        return varIdentifier;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return null;
    }

}
