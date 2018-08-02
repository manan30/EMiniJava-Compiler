package lexer;

import java.util.HashMap;

class SymbolMapping {

    static HashMap<String, Integer> symbolsMap = new HashMap<>();

    static {

        symbolsMap.put("PRINTLN", 57);
        symbolsMap.put("EXTENDS", 55);
        symbolsMap.put("BOOLEAN", 54);
        symbolsMap.put("RETURN", 53);
        symbolsMap.put("STRING", 52);
        symbolsMap.put("PUBLIC", 51);
        symbolsMap.put("STATIC", 50);
        symbolsMap.put("LENGTH", 49);
        symbolsMap.put("WHILE", 48);
        symbolsMap.put("FALSE", 47);
        symbolsMap.put("SIDEF", 46);
        symbolsMap.put("CLASS", 45);
        symbolsMap.put("ELSE", 44);
        symbolsMap.put("MAIN", 43);
        symbolsMap.put("VOID", 42);
        symbolsMap.put("THIS", 41);
        symbolsMap.put("TRUE", 40);
        symbolsMap.put("NEW", 39);
        symbolsMap.put("INT", 38);
        symbolsMap.put("DEQ", 28);
        symbolsMap.put("OR", 27);
        symbolsMap.put("AND", 26);
        symbolsMap.put("IF", 25);
        symbolsMap.put("STRING_LITERAL", 24);
        symbolsMap.put("MINUS", 20);
        symbolsMap.put("PLUS", 19);
        symbolsMap.put("NOT", 18);
        symbolsMap.put("LT", 17);
        symbolsMap.put("EQ", 16);
        symbolsMap.put("COMMA", 15);
        symbolsMap.put("SEMICOLON", 14);
        symbolsMap.put("RBRACK", 13);
        symbolsMap.put("LBRACK", 12);
        symbolsMap.put("RBRACE", 11);
        symbolsMap.put("LBRACE", 10);
        symbolsMap.put("RPAREN", 9);
        symbolsMap.put("LPAREN", 8);
        symbolsMap.put("DOT", 7);
        symbolsMap.put("INT_LITERAL", 6);
        symbolsMap.put("ID", 5);
        symbolsMap.put("MULTIPLY", 4);
        symbolsMap.put("DIVIDE", 3);

        symbolsMap.put("BOOLEAN_LITERAL", 98);
        symbolsMap.put("EOF", 99);
        symbolsMap.put("BAD", 100);

    }

    static String getSymbolName(int token) {

        for (String key : symbolsMap.keySet()) {

            if (symbolsMap.get(key).equals(token))
                return key;

        }

        return "BAD_TOKEN";

    }

}
