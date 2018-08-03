package lexer;

class EMJavaSymbol extends java_cup.runtime.Symbol {

    private int line, column;

    EMJavaSymbol(int type, int line, int column) {

        this(type, line, column, -1, -1, null);

    }

    EMJavaSymbol(int type, int line, int column, Object value) {

        this(type, line, column, -1, -1, value);

    }

    public EMJavaSymbol(int type, int line, int column, int left, int right, Object value) {

        super(type, left, right, value);
        this.line = line;
        this.column = column;

    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {

        return !(SymbolMapping.getSymbolName(sym).equals(SymbolMapping.getSymbolName(100))) ?
                line + " : " + column + "\t" + SymbolMapping.getSymbolName(sym) + ("(" + (value == null ? "" : value) + ")")
                : "";

    }

}
