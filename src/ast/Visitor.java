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

    public R visit(Times n);

    public R visit(Division n);

    public R visit(Equals n);

    public R visit(LessThan n);

    public R visit(And n);

    public R visit(Or n);

    public R visit(Neg n);

    public R visit(Not n);

}