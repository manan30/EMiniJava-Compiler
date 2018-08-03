package ast;

public class Type extends Tree {

    public enum Typename {
        INT, BOOLEAN, STRING, INT_ARRAY, IDENTIFIER, STRING_ARRAY, VOID
    }

    public Identifier identifier;
    public Typename typename;

    public Type(Typename typename) {
        this.typename = typename;
    }

    public Type(Identifier identifier) {
        this.identifier = identifier;
        this.typename = Typename.IDENTIFIER;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
