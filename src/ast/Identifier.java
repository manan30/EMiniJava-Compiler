package ast;

public class Identifier extends Tree {

    private String idName;

    public Identifier(String idName) {
        this.idName = idName;
    }

    public String getIdName() {
        return idName;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return null;
    }

}
