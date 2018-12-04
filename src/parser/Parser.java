package parser;

import ast.*;
import lexer.Lexer;
import lexer.SymbolMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    private void eat(int token) throws ParseException {

        if (lexer.getCurrentSymbol().sym == token) {
            advance();
        } else
            throwError(lexer.getCurrentSymbol().toString(), lexer.getCurrentSymbol().toString());

    }

    public Program parseProgram() throws IOException, ParseException {

        Program program;

        MainClass mainClass = parseMainClass();
        List<ClassDeclaration> classDeclarations = new ArrayList<>();

        while (SymbolMapping.symbolsMap.get("EOF") != lexer.getCurrentSymbol().sym)
            classDeclarations.add(parseClassDeclaration());

        program = new Program(mainClass, classDeclarations);

        return program;

    }

    private MainClass parseMainClass() throws IOException, ParseException {

        MainClass mainClass;
        Identifier mainClassName;
        Identifier args;

        eat(SymbolMapping.symbolsMap.get("CLASS"));

        mainClassName = parseIdentifier();

        eat(SymbolMapping.symbolsMap.get("LBRACE"));
        eat(SymbolMapping.symbolsMap.get("PUBLIC"));
        eat(SymbolMapping.symbolsMap.get("STATIC"));
        eat(SymbolMapping.symbolsMap.get("VOID"));
        eat(SymbolMapping.symbolsMap.get("MAIN"));
        eat(SymbolMapping.symbolsMap.get("LAPREN"));
        eat(SymbolMapping.symbolsMap.get("STRING"));
        eat(SymbolMapping.symbolsMap.get("LBRACK"));
        eat(SymbolMapping.symbolsMap.get("RBRACK"));

        args = parseIdentifier();

        eat(SymbolMapping.symbolsMap.get("LBRACE"));

        Statement statement = parseStatement();

        eat(SymbolMapping.symbolsMap.get("RBRACE"));
        eat(SymbolMapping.symbolsMap.get("RBRACE"));

        mainClass = new MainClass(mainClassName, args, statement);

        return mainClass;

    }

    private ClassDeclaration parseClassDeclaration() throws IOException, ParseException {

        ClassDeclaration classDeclaration;
        Identifier className;
        Identifier parentClass = null;
        List<VarDeclaration> varDeclarations = new ArrayList<>();
        List<MethodDeclaration> methodDeclarations = new ArrayList<>();

        eat(SymbolMapping.symbolsMap.get("CLASS"));

        className = parseIdentifier();

        if (SymbolMapping.symbolsMap.get("EXTENDS") == lexer.getCurrentSymbol().sym) {

            eat(SymbolMapping.symbolsMap.get("EXTEDNS"));
            parentClass = parseIdentifier();

        }

        eat(SymbolMapping.symbolsMap.get("LBRACE"));

        while (SymbolMapping.symbolsMap.get("PUBLIC") != lexer.getCurrentSymbol().sym)
            varDeclarations.add(parseVarDeclaration());

        while (SymbolMapping.symbolsMap.get("RBRACE") == lexer.getCurrentSymbol().sym)
            methodDeclarations.add(parseMethodDeclaration());

        eat(SymbolMapping.symbolsMap.get("RBRACE"));

        classDeclaration = new ClassDeclaration(className, parentClass, varDeclarations, methodDeclarations);

        return classDeclaration;

    }

    private VarDeclaration parseVarDeclaration() throws IOException, ParseException {

        VarDeclaration varDeclaration;
        Type varType;
        Identifier varIdentifier;

        varType = parseType();
        varIdentifier = parseIdentifier();

        eat(SymbolMapping.symbolsMap.get("SEMICOLON"));

        varDeclaration = new VarDeclaration(varType, varIdentifier);

        return varDeclaration;

    }

    private MethodDeclaration parseMethodDeclaration() throws IOException, ParseException {

        MethodDeclaration methodDeclaration;
        Type methodType;
        Identifier methodIdentifier;
        HashMap<Type, Identifier> methodArgs = new HashMap<>();
        List<VarDeclaration> varDeclarations = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        Expression returnExpression;

        eat(SymbolMapping.symbolsMap.get("PUBLIC"));

        methodType = parseType();

        methodIdentifier = parseIdentifier();

        eat(SymbolMapping.symbolsMap.get("LPAREN"));

        if (SymbolMapping.symbolsMap.get("RPAREN") != lexer.getCurrentSymbol().sym) {

            Type argType = parseType();
            Identifier argIdentifier = parseIdentifier();

            methodArgs.put(argType, argIdentifier);

            while (SymbolMapping.symbolsMap.get("RPAREN") != lexer.getCurrentSymbol().sym) {

                argType = parseType();
                argIdentifier = parseIdentifier();

                methodArgs.put(argType, argIdentifier);

            }

        }

        eat(SymbolMapping.symbolsMap.get("RPAREN"));
        eat(SymbolMapping.symbolsMap.get("LBRACE"));

        while (condition())
            varDeclarations.add(parseVarDeclaration());

        while (SymbolMapping.symbolsMap.get("RETURN") != lexer.getCurrentSymbol().sym)
            statements.add(parseStatement());

        eat(SymbolMapping.symbolsMap.get("RETURN"));

        returnExpression = parseExpression();

        eat(SymbolMapping.symbolsMap.get("SEMICOLON"));
        eat(SymbolMapping.symbolsMap.get("RBRACE"));

        methodDeclaration = new MethodDeclaration(methodType, methodIdentifier, methodArgs,
                varDeclarations, statements, returnExpression);

        return methodDeclaration;

    }

    private Type parseType() {

        Type type;

        int type_value = lexer.getCurrentSymbol().sym;

        if (type_value == SymbolMapping.symbolsMap.get("INT")) {

            if (SymbolMapping.symbolsMap.get("LBRACK") == lexer.getCurrentSymbol().right)
                type = new Type(Type.Typename.INT_ARRAY);
            else
                type = new Type(Type.Typename.INT);

        } else if (type_value == SymbolMapping.symbolsMap.get("BOOLEAN"))
            type = new Type(Type.Typename.BOOLEAN);

        else if (type_value == SymbolMapping.symbolsMap.get("STRING"))
            type = new Type(Type.Typename.STRING);

        else
            type = new Type(Type.Typename.IDENTIFIER);

        advance();

        return type;

    }

    private Statement parseStatement() throws IOException, ParseException {

        Statement statement = null;

        int current_token = lexer.getCurrentSymbol().sym;

        if (current_token == SymbolMapping.symbolsMap.get("LBRACE"))

            while (SymbolMapping.symbolsMap.get("LBRACE") != lexer.getCurrentSymbol().sym)
                parseStatement();

        else if (current_token == SymbolMapping.symbolsMap.get("IF"))
            statement = parseIfStatement();

        else if (current_token == SymbolMapping.symbolsMap.get("WHILE"))
            statement = parseWhileStatement();

        else if (current_token == SymbolMapping.symbolsMap.get("PRINTLN")) {

            Print print;

            eat(SymbolMapping.symbolsMap.get("PRINTLN"));
            eat(SymbolMapping.symbolsMap.get("LPAREN"));

            Expression printExpression = parseExpression();

            eat(SymbolMapping.symbolsMap.get("RPAREN"));
            eat(SymbolMapping.symbolsMap.get("SEMICOLON"));

            print = new Print(printExpression);

            statement = print;

        } else if (current_token == SymbolMapping.symbolsMap.get("SIDEF")) {

            Sidef sidef;

            eat(SymbolMapping.symbolsMap.get("SIDEF"));
            eat(SymbolMapping.symbolsMap.get("LPAREN"));

            Expression sidefExpression = parseExpression();

            eat(SymbolMapping.symbolsMap.get("RPAREN"));
            eat(SymbolMapping.symbolsMap.get("SEMICOLON"));

            sidef = new Sidef(sidefExpression);

            statement = sidef;

        } else {

            Assign assign;
            ArrayAssign arrayAssign;
            Identifier lhs;

            lhs = parseIdentifier();

            if (lexer.getCurrentSymbol().sym == SymbolMapping.symbolsMap.get("EQ")) {

                eat(SymbolMapping.symbolsMap.get("EQ"));

                Expression rhs = parseExpression();

                eat(SymbolMapping.symbolsMap.get("SEMICOLON"));

                assign = new Assign(lhs, rhs);

                return assign;

            } else {

                eat(SymbolMapping.symbolsMap.get("LBRACK"));

                Expression array_size = parseExpression();

                eat(SymbolMapping.symbolsMap.get("RBRACK"));

                eat(SymbolMapping.symbolsMap.get("EQ"));

                Expression rhs = parseExpression();

                eat(SymbolMapping.symbolsMap.get("SEMICOLON"));

                arrayAssign = new ArrayAssign(lhs, array_size, rhs);

                return arrayAssign;

            }

        }

        return statement;

    }

    private Expression parseExpression() {

        Expression expression = null;

        return expression;
        
    }

    private Statement parseIfStatement() throws IOException, ParseException {

        IfThenElse ifThenElse;
        Expression ifCondition;
        Statement thenStatement;
        Statement elseStatement = null;

        eat(SymbolMapping.symbolsMap.get("IF"));
        eat(SymbolMapping.symbolsMap.get("LPAREN"));

        ifCondition = parseExpression();

        thenStatement = parseStatement();

        if (lexer.getCurrentSymbol().sym == SymbolMapping.symbolsMap.get("ELSE")) {

            eat(SymbolMapping.symbolsMap.get("ELSE"));

            elseStatement = parseStatement();

        }

        ifThenElse = new IfThenElse(ifCondition, thenStatement, elseStatement);

        return ifThenElse;

    }

    private Statement parseWhileStatement() throws IOException, ParseException {

        While aWhile;
        Expression whileCondition;
        Statement whileStatement;

        eat(SymbolMapping.symbolsMap.get("WHILE"));
        eat(SymbolMapping.symbolsMap.get("LPAREN"));

        whileCondition = parseExpression();

        eat(SymbolMapping.symbolsMap.get("RPAREN"));

        whileStatement = parseStatement();

        aWhile = new While(whileCondition, whileStatement);

        return aWhile;

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

    private Boolean condition() {

        int current_value = lexer.getCurrentSymbol().sym;

        return current_value != SymbolMapping.symbolsMap.get("LBRACE") &&
                current_value != SymbolMapping.symbolsMap.get("IF") &&
                current_value != SymbolMapping.symbolsMap.get("WHILE") &&
                current_value != SymbolMapping.symbolsMap.get("PRINTLN") &&
                current_value != SymbolMapping.symbolsMap.get("ID") &&
                current_value != SymbolMapping.symbolsMap.get("SIDEF");

    }

}

class ParseException extends Exception {

    ParseException(int line, int column, String message) {
        super("Parse error: " + message + " at line: " + line + " and column " + column + ".");
    }

}