package ast;

import java.util.HashMap;
import java.util.List;

public class MethodDeclaration extends Tree {

    private Type methodType;
    private Identifier methodIdentifier;
    private HashMap<Type, Identifier> methodArguments;
    private List<VarDeclaration> varDeclarations;
    private List<Statement> statements;
    private Expression returnExpression;

    public MethodDeclaration(Type methodType, Identifier methodIdentifier, HashMap<Type, Identifier> methodArguments,
                             List<VarDeclaration> varDeclarations, List<Statement> statements, Expression returnExpression) {

        this.methodType = methodType;
        this.methodIdentifier = methodIdentifier;
        this.methodArguments = methodArguments;
        this.varDeclarations = varDeclarations;
        this.statements = statements;
        this.returnExpression = returnExpression;

    }

    public Type getMethodType() {
        return methodType;
    }

    public Identifier getMethodIdentifier() {
        return methodIdentifier;
    }

    public HashMap<Type, Identifier> getMethodArguments() {
        return methodArguments;
    }

    public List<VarDeclaration> getVarDeclarations() {
        return varDeclarations;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public Expression getReturnExpression() {
        return returnExpression;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
