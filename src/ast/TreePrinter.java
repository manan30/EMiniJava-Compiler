package ast;

public class TreePrinter implements Visitor<String> {

    int level = 0;

    void incLevel() {
        level = level + 1;
    }

    void decLevel() {
        level = level - 1;
    }

    String printInc() {
        char[] chars = new char[level];
        java.util.Arrays.fill(chars, '\t');
        return new String(chars);
    }

    public String visit(Var n) {
        return n.varID;
    }

    public String visit(IntLiteral n) {
        return Integer.toString(n.value);
    }

    public String visit(Plus n) {
        return "(" + n.lhs.accept(this) + " + " + n.rhs.accept(this) + ")";
    }

    public String visit(Minus n) {
        return "(" + n.lhs.accept(this) + " - " + n.rhs.accept(this) + ")";
    }

    public String visit(Multiply n) {
        return "(" + n.lhs.accept(this) + " * " + n.rhs.accept(this) + ")";
    }

    public String visit(Divide n) {
        return "(" + n.lhs.accept(this) + " / " + n.rhs.accept(this) + ")";
    }

    public String visit(Equals n) {
        return "(" + n.lhs.accept(this) + " == " + n.rhs.accept(this) + ")";
    }

    public String visit(LessThan n) {
        return "(" + n.lhs.accept(this) + " < " + n.rhs.accept(this) + ")";
    }

    public String visit(And n) {
        return "(" + n.lhs.accept(this) + " && " + n.rhs.accept(this) + ")";
    }

    public String visit(Or n) {
        return "(" + n.lhs.accept(this) + " || " + n.rhs.accept(this) + ")";
    }

    public String visit(Neg n) {
        return "-" + n.expr.accept(this);
    }

    public String visit(Not n) {
        return "!" + n.expr.accept(this);
    }

    @Override
    public String visit(Program program) {
        return null;
    }

    @Override
    public String visit(ClassDeclaration classDeclaration) {
        return null;
    }

    @Override
    public String visit(MainClass mainClass) {
        return null;
    }

    @Override
    public String visit(MethodDeclaration methodDeclaration) {
        return null;
    }

    @Override
    public String visit(Type type) {
        return null;
    }

    @Override
    public String visit(ArrayAssign arrayAssign) {
        return null;
    }

    @Override
    public String visit(Sidef sidef) {
        return null;
    }

    @Override
    public String visit(StringLiteral stringLiteral) {
        return null;
    }

    // ##############   Statements   ##############

    public String visit(IfThenElse n) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(printInc());
        stringBuilder.append("if (");
        stringBuilder.append(n.expression.accept(this));
        stringBuilder.append(") {\n");

        incLevel();

        stringBuilder.append(n.thenStatement.accept(this));

        decLevel();

        if (!(n.elseStatement.accept(this).equals(""))) {

            stringBuilder.append(printInc());
            stringBuilder.append("} else {\n");
            incLevel();
            stringBuilder.append(n.elseStatement.accept(this));
            decLevel();

        }

        stringBuilder.append(printInc());
        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    public String visit(Print n) {
        return printInc() + "println(\"" + n.printExpression.accept(this) + ");\n";
    }

    public String visit(Assign n) {
        return printInc() + n.identifier + " = " + n.expression.accept(this) + ";\n";
    }

    public String visit(While n) {

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(printInc());
        strBuilder.append("while (");
        strBuilder.append(n.expression.accept(this));
        strBuilder.append(") {\n");

        incLevel();

        strBuilder.append(n.body.accept(this));

        decLevel();

        strBuilder.append("}\n");

        return strBuilder.toString();

    }

    public String visit(Block n) {

        StringBuilder strBuilder = new StringBuilder();

        for (Statement stat : n.body)
            strBuilder.append(stat.accept(this));

        return strBuilder.toString();

    }

    public String visit(Skip n) {
        return "";
    }
}	