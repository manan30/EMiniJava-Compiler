package ast;

public interface Visitor<R> {

    // Program
    R visit(Program program);

    // Main Class
    R visit(MainClass mainClass);

    // Class Declaration
    R visit(ClassDeclaration classDeclaration);

    // Var Declaration
    R visit(VarDeclaration varDeclaration);

    // Method Declaration
    R visit(MethodDeclaration methodDeclaration);

    // Type
    R visit(Type type);

    // Statements
    R visit(Print n);

    R visit(Assign n);

    R visit(ArrayAssign arrayAssign);

    R visit(Skip n);

    R visit(Block n);

    R visit(IfThenElse n);

    R visit(While n);

    R visit(Sidef sidef);

    // Expression
    R visit(Var n);

    R visit(IntLiteral n);

    R visit(Plus n);

    R visit(Minus n);

    R visit(Multiply n);

    R visit(Divide n);

    R visit(Equals n);

    R visit(LessThan n);

    R visit(And n);

    R visit(Or n);

    R visit(Neg n);

    R visit(Not n);

    R visit(New n);

    R visit(Size size);

    R visit(This tthis);

    R visit(Boolean bool);

    R visit(StringLiteral stringLiteral);

    R visit(IDExpr idExpr);

    R visit(Dot dot);

}