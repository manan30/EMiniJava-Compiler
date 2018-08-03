package ast;

public interface Visitor<R> {

    // Statement
    public R visit(Print n);

    public R visit(Assign n);

    public R visit(Skip n);

    public R visit(Block n);

    public R visit(IfThenElse n);

    public R visit(While n);

    // Expression
    public R visit(Var n);

    public R visit(IntLiteral n);

    public R visit(Plus n);

    public R visit(Minus n);

    public R visit(Multiply n);

    public R visit(Divide n);

    public R visit(Equals n);

    public R visit(LessThan n);

    public R visit(And n);

    public R visit(Or n);

    public R visit(Neg n);

    public R visit(Not n);

    public R visit(Program program);

    public R visit(ClassDeclaration classDeclaration);

    public R visit(MainClass mainClass);

    public R visit(MethodDeclaration methodDeclaration);

    public R visit(Type type);

    public R visit(ArrayAssign arrayAssign);

    public R visit(Sidef sidef);

    public R visit(StringLiteral stringLiteral);
}