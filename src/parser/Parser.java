package parser;

public class Parser {

}

class ParseException extends Exception {

    public ParseException(int line, int column, String message) {
        super("Parse error: " + message + "at line: " + line + " and column" + column + ".");
    }

}