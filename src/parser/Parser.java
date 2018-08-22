package parser;

import ast.*;
import lexer.Lexer;
import lexer.SymbolMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Lexer lexer;

    public Parser(Lexer lexer) throws IOException {

        this.lexer = lexer;
        lexer.next_token();

    }

    private void advance() {

        try {
            lexer.next_token();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void eat(int token) throws IOException, ParseException {

        if (lexer.getCurrentSymbol().sym == token) {
            lexer.next_token();
        } else
            throwError(lexer.getCurrentSymbol().toString(), lexer.getCurrentSymbol().toString());

    }

    public Program parseProgram() {

        MainClass mainClass = parseMainClass();
        List<ClassDeclaration> classDeclarations = new ArrayList<>();

        
    }

    private MainClass parseMainClass() {

    }

    private ClassDeclaration parseClassDeclaration() {

    }

    private Identifier parseIdentifier() throws IOException, ParseException {

        Identifier identifier = null;

        if (SymbolMapping.symbolsMap.get("ID") == lexer.getCurrentSymbol().sym) {
            identifier = new Identifier(lexer.getCurrentSymbol().value.toString());
            lexer.next_token();
        } else
            throwError(lexer.getCurrentSymbol().toString(), "ID");

        return identifier;

    }

    private void throwError(String expected, String got) throws ParseException {

        String message = "Expected token \"" + expected + "\" instead got \"" + got + "\"";
        throw new ParseException(lexer.getCurrentSymbol().getLine(), lexer.getCurrentSymbol().getColumn(), message);

    }

}

class ParseException extends Exception {

    ParseException(int line, int column, String message) {
        super("Parse error: " + message + " at line: " + line + " and column " + column + ".");
    }

}