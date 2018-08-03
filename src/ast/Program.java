package ast;

import java.util.List;

public class Program extends Tree {

    public MainClass mainClass;
    public List<ClassDeclaration> classDeclarations;

    public Program(MainClass mainClass, List<ClassDeclaration> classDeclarations) {

        this.mainClass = mainClass;
        this.classDeclarations = classDeclarations;

    }


    public MainClass getMainClass() {
        return mainClass;
    }

    public List<ClassDeclaration> getClassDeclarations() {
        return classDeclarations;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }

}
