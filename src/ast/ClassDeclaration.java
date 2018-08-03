package ast;

import java.util.List;

public class ClassDeclaration extends Tree {

    public String classIdentifier;
    public String classExtendsIdentifier;
    public List<VarDeclaration> varDeclarations;
    public List<MethodDeclaration> methodDeclarations;

    public ClassDeclaration(String classIdentifier, String classExtendsIdentifier,
                            List<VarDeclaration> varDeclarations, List<MethodDeclaration> methodDeclarations) {

        this.classIdentifier = classIdentifier;
        this.classExtendsIdentifier = classExtendsIdentifier;
        this.varDeclarations = varDeclarations;
        this.methodDeclarations = methodDeclarations;

    }

    public String getClassIdentifier() {
        return classIdentifier;
    }

    public String getClassExtendsIdentifier() {
        return classExtendsIdentifier;
    }

    public List<VarDeclaration> getVarDeclarations() {
        return varDeclarations;
    }

    public List<MethodDeclaration> getMethodDeclarations() {
        return methodDeclarations;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
