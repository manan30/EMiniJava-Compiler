package lexer;

class EMJavaSymbol extends java_cup.runtime.Symbol {

    EMJavaSymbol(int type, int line, int column) {

        super(type, line, column);

    }

    EMJavaSymbol(int type, int line, int column, Object value) {

        super(type, line, column, value);

    }

}
