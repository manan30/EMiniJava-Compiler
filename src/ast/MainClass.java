package ast;

public class MainClass extends Tree {

    public String classIdentifier;
    public String methodIdentifier;
    public Statement statement;

    public MainClass(String classIdentifier, String methodIdentifier, Statement statement) {

        this.classIdentifier = classIdentifier;
        this.methodIdentifier = methodIdentifier;
        this.statement = statement;

    }

    public String getClassIdentifier() {
        return classIdentifier;
    }

    public String getMethodIdentifier() {
        return methodIdentifier;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
