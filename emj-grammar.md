Program: MainClass ( ClassDeclaration )* <EOF>

MainClass: class Identifier { public static void main ( String [ ] Identifier ) { Statement } }

ClassDeclaration: class Identifier ( extends Identifier )? { ( VarDeclaration )* ( MethodDeclaration )* }

VarDeclaration: Type Identifier ;

MethodDeclaration: public Type Identifier ( ( Type Identifier ( , Type Identifier )* )? ) { ( VarDeclaration )* ( Statement )* return Expression ; }

Type: int | boolean | String | int [ ] | Identifier

Statement: { ( Statement )* } |
           if ( Expression ) Statement ( else Statement )? |
           while ( Expression ) Statement |
           System.out.println ( Expression ); |
           Identifier = Expression; |
           Identifier [ Expression ] = Expression; |
           sidef ( Expression );

Expression: Expression ( && | || | == | < | + | - | * | / ) Expression | 
            Expression [ Expression ] | 
            Expression . dot | 
            Expression . Identifier ( ( Expression ( , Expression )* )? ) | 
            <INTEGER_LITERAL> | 
            "<STRING_LITERAL>" | 
            true | 
            false |
            Identifier | 
            this | 
            new int [ Expression ] | 
            new Identifier () | 
            ! Expression | 
            ( Expression )
            
Identifier: <IDENTIFIER>


1. <IDENTIFIER> represents a sequence of letters, digits and underscores, starting with a letter. An identifier is not 
   a keyword. Identifiers are case-sensitive.
2. <INTEGER_LITERAL> represents a sequence of digits
3. <STRING_LITERAL> represents a sequence of arbitrary characters, except new lines and ". You don't need to support 
escape characters such as \n.
4. <EOF> represents the special end-of-file character