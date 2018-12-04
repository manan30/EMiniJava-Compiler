package ast;

import java.util.List;

public class ClassDeclaration extends Tree {

    private Identifier classIdentifier;
    private Identifier classExtendsIdentifier;
    private List<VarDeclaration> varDeclarations;
    private List<MethodDeclaration> methodDeclarations;

    public ClassDeclaration(Identifier classIdentifier, Identifier classExtendsIdentifier,
                            List<VarDeclaration> varDeclarations, List<MethodDeclaration> methodDeclarations) {

        this.classIdentifier = classIdentifier;
        this.classExtendsIdentifier = classExtendsIdentifier;
        this.varDeclarations = varDeclarations;
        this.methodDeclarations = methodDeclarations;

    }

    public Identifier getClassIdentifier() {
        return classIdentifier;
    }

    public Identifier getClassExtendsIdentifier() {
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
