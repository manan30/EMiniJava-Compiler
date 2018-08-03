package ast;

import java.util.HashMap;
import java.util.List;

public class MethodDeclaration extends Tree {

    public Type methodType;
    public String methodIdentifier;
    public HashMap<Type, String> methodArguments;
    public List<VarDeclaration> varDeclarations;
    public List<Statement> statements;
    public Expression returnExpression;

    public MethodDeclaration(Type methodType, String methodIdentifier, HashMap<Type, String> methodArguments,
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

    public String getMethodIdentifier() {
        return methodIdentifier;
    }

    public HashMap<Type, String> getMethodArguments() {
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
