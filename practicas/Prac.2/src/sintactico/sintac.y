%{
package sintactico;
%}

%left '<' '>'
%left '+'

%%

programa: seccion_data seccion_code
	;
	
seccion_data: 'DATA' definiciones;

definiciones: definicion
			| definiciones definicion
			;
			
definicion: tipo 'IDENT' ';'
		  ;
		  
tipo: 'REAL'
	| 'INT'
	;

seccion_code: 'CODE' sentencias
			;
			
sentencias: sentencia
		  | sentencias sentencia
		  ;

sentencia: 'PRINT' expr ';'
		 | 'READ'  'IDENT' ';'
		 | 'IDENT' '=' expr ';'
		 | while
		 | if
		 ;   

expr: 'LITENT'
	| 'IDENT'
	| 'LITREAL'
	| expr '+' expr	    
	| expr '<' expr
	| expr '>' expr
	| '(' expr ')'
	| '<' tipo '>' '(' expr ')'	    
	;
		  
if: 'IF' '(' expr ')' '{' sentencias '}'
  | 'IF' '(' expr ')' '{' sentencias '}' 'ELSE' '{' sentencias '}'
  ;
		  
while: 'WHILE' '(' expr ')' '{' sentencias '}'
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
