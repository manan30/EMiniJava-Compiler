
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals,
   because the input is read twice and the methods used to parse
   the numbers are not very fast.
   For a production quality application (e.g. a Java compiler)
   this could be optimized */


import java_cup.runtime.*;

%%

%public
%class Lexer
%implements sym

%unicode

%line
%column

%cup
%cupdebug

%{
  StringBuilder string = new StringBuilder();

  private Symbol symbol(int type) {
    return new EMJavaSymbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new EMJavaSymbol(type, yyline+1, yycolumn+1, value);
  }

  /**
   * assumes correct representation of a long value for
   * specified radix in scanner buffer from <code>start</code>
   * to <code>end</code>
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {BlockedComment} | {LineComment}

BlockedComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
LineComment = "//" {InputCharacter}* {LineTerminator}?

/* identifiers */
Identifier = [a-zA-Z][a-zA-Z_0-9]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*

/* string literals */
StringCharacter = [^\n\r\"\\]+

%state STRING

%%

<YYINITIAL> {

  /* keywords */
  "class"                        { return symbol(CLASS); }
  "public"                       { return symbol(PUBLIC); }
  "static"                       { return symbol(STATIC); }
  "void"                         { return symbol(VOID); }
  "main"                      { return symbol(MAIN); }
  "String"                      { return symbol(STRING); }
  "extends"                      { return symbol(EXTENDS); }
  "return"                       { return symbol(RETURN); }
  "int"                          { return symbol(INT); }
  "boolean"                      { return symbol(BOOLEAN); }
  "if"                           { return symbol(IF); }
  "else"                         { return symbol(ELSE); }
  "while"                        { return symbol(WHILE); }
  "System.out.println"           { return symbol(PRINTLN); }
  "sidef"                      { return symbol(SIDEF); }
  "new"                          { return symbol(NEW); }
  "this"                         { return symbol(THIS); }
  "length"                       { return symbol(LENGTH); }

  /* boolean literals */
  "true"                         { return symbol(BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(BOOLEAN_LITERAL, false); }

  /* separators */
  "("                            { return symbol(LPAREN); }
  ")"                            { return symbol(RPAREN); }
  "{"                            { return symbol(LBRACE); }
  "}"                            { return symbol(RBRACE); }
  "["                            { return symbol(LBRACK); }
  "]"                            { return symbol(RBRACK); }
  ";"                            { return symbol(SEMICOLON); }
  ","                            { return symbol(COMMA); }
  "."                            { return symbol(DOT); }

  /* operators */
  "&&"                           { return symbol(AND); }
  "||"                           { return symbol(OR); }
  "=="                           { return symbol(DEQ); }
  "="                            { return symbol(EQ); }
  "<"                            { return symbol(LT); }
  "!"                            { return symbol(NOT); }
  "+"                            { return symbol(PLUS); }
  "-"                            { return symbol(MINUS); }
  "*"                            { return symbol(MULT); }
  "/"                            { return symbol(DIV); }

  /* string literal */
  \"                             { string.setLength(0); yybegin(STRING); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }
  {DecIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer(yytext())); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }

}

<STRING> {

   \"                             { yybegin(YYINITIAL);
                                         return symbol(sym.STRING_LITERAL,
                                         string.toString()); }

  [^\n\r\"\\]+                   { string.append( yytext() ); }

  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}


/* error fallback */
.|\n                             { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(EOF); }
