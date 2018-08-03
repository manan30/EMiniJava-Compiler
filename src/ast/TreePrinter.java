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

    public String visit(Times n) {
        return "(" + n.lhs.accept(this) + " * " + n.rhs.accept(this) + ")";
    }

    public String visit(Division n) {
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

    // ##############   Statements   ##############

    public String visit(IfThenElse n) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(printInc());
        stringBuilder.append("if (");
        stringBuilder.append(n.expr.accept(this));
        stringBuilder.append(") {\n");

        incLevel();

        stringBuilder.append(n.then.accept(this));

        decLevel();

        if (!(n.elze.accept(this).equals(""))) {

            stringBuilder.append(printInc());
            stringBuilder.append("} else {\n");
            incLevel();
            stringBuilder.append(n.elze.accept(this));
            decLevel();

        }

        stringBuilder.append(printInc());
        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    public String visit(Print n) {
        return printInc() + "println(\"" + n.msg + "\", " + n.varID + ");\n";
    }

    public String visit(Assign n) {
        return printInc() + n.varID + " = " + n.expr.accept(this) + ";\n";
    }

    public String visit(While n) {

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(printInc());
        strBuilder.append("while (");
        strBuilder.append(n.expr.accept(this));
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