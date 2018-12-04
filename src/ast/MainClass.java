package ast;

public class MainClass extends Tree {

    private Identifier classIdentifier;
    private Identifier mainMethodArgs;
    public Statement statement;

    public MainClass(Identifier classIdentifier, Identifier mainMethodArgs, Statement statement) {

        this.classIdentifier = classIdentifier;
        this.mainMethodArgs = mainMethodArgs;
        this.statement = statement;

    }

    public Identifier getClassIdentifier() {
        return classIdentifier;
    }

    public Identifier getMainMethodArgs() {
        return mainMethodArgs;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
