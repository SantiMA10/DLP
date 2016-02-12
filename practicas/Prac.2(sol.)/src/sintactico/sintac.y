%{
package sintactico;
%}

%left '<' '>'
%left '+'
%%

programa: 'DATA' definiciones 'CODE' sentencias
	;
	
definiciones: definicion
	| definiciones definicion
	;
	
definicion: tipo 'IDENT' ';'
	;
	
tipo: 'INT' | 'REAL' ;

sentencias: sentencia
	| sentencias sentencia
	;

sentencia: 'PRINT' expr ';'
	| 'READ' 'IDENT' ';'
	| 'IDENT' '=' expr ';'
	| 'IF' '(' expr ')' '{' sentencias '}'
	| 'IF' '(' expr ')' '{' sentencias '}' 'ELSE' '{' sentencias '}'
	| 'WHILE' '(' expr ')' '{' sentencias '}'
	;   

expr: 'LITENT'
	| 'LITREAL'
	| 'IDENT'
	| expr '+' expr	    
	| expr '<' expr
	| expr '>' expr
	| '(' expr ')'
	| '<' tipo '>''(' expr ')'
	;

%%

private Yylex lex;
private int token;

public Parser(Yylex lex, boolean debug) {
  this(debug);
  this.lex = lex;
}


public int parse() { return yyparse(); }


// Funciones requeridas por el parser

void yyerror(String s)
{
 System.out.println("Error sintáctico en " + lex.line() + ":" + lex.column() + " Token = " + token + " lexema = \"" + lex.lexeme()+"\"");
}

int yylex() {
  try {
	token = lex.yylex();
	yylval = lex.lexeme();
	return token;
  } catch (Exception e) {
    return -1;
  }
}

