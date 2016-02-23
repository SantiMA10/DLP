// @author Raúl Izquierdo
// @author Santiago Martin

/* No es necesario modificar esta sección ------------------ */
%{
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import main.*;
%}

/* Precedencias aquí --------------------------------------- */
%left '+' '-'
%left '*' '/'
%left '<' '>'
%left 'MAYOR_IGUAL' 'MENOR_IGUAL'
%left 'IGUAL' 'DISTINTO'
%left 'AND' 'OR' 'NOT'

%%

/* Añadir las reglas en esta sección ----------------------- */

programa: sentencias
		| sentencias programa 
		;

sentencias: definiciones
		  | struct
		  | funciones
		  ;

struct: 'STRUCT' 'IDENT' '{' definiciones_struct '}' ';'
	  ;

definiciones_struct:  definicion_struct
			 	   | definicion_struct definiciones_struct
			 	   ;

definicion_struct: 'IDENT' ':' size tipo
 ';'
			;

definiciones:  
			 | definicion definiciones
			 ;

definicion: 'VAR' 'IDENT' ':' size tipo ';'
			;

size: 
	| '[' expr_num ']' size
	;

tipo : tipo_basico
	 | 'IDENT'
	 ;

tipo_basico: 'INT'
	| 'FLOAT'
	| 'CHAR'
	;			

funciones: funcion
		 | funcion funciones
		 ;

funcion: 'IDENT' '(' parametrosP ')' ':' tipo_basico '{' definiciones sentencias 'RETURN' expr ';' '}'
	   | 'IDENT' '(' parametrosP ')' '{' definiciones sentencias '}'
	   ;

parametrosP: parametros
		  | 
		  ;

parametros: parametro
		  | parametro ',' parametros
		  ;

parametro: 'IDENT' ':' tipo_basico
		 ;

sentencias: sentencia
		  | sentencia sentencias
		  ;

sentencia: 'PRINT' expr ';'
		 | 'READ'  expr ';'
		 | 'IDENT' '=' expr ';'
		 | while
		 | if
		 ;   

expr: 'LITCHAR'
	|  expr '[' expr_num ']'
	|  expr 'PUNTO' expr
	|  expr_num
	|  'CAST' '<' tipo_basico '>' '(' expr ')'
	;

expr_num: 'LITENT'
		| 'LITREAL'
		| 'IDENT'
	 	| expr_num '*' expr_num
		| expr_num '/' expr_num
		| expr_num '-' expr_num
		| expr_num '+' expr_num
		| expr_num '>' expr_num
		| expr_num 'MAYOR_IGUAL' expr_num
		| expr_num '<' expr_num
		| expr_num 'MENOR_IGUAL' expr_num
		| expr_num 'IGUAL' expr_num
		| expr_num 'DISTINTO' expr_num
		| expr_num 'AND' expr_num
		| expr_num 'OR' expr_num
		| expr_num 'NOT' expr_num
		| '(' expr_num  ')'
		;


if: 'IF' '(' expr_num ')' '{' sentencias '}'
  | 'IF' '(' expr_num ')' '{' sentencias '}' 'ELSE' '{' sentencias '}'
  ;

while: 'WHILE' '(' expr ')' '{' sentencias '}'
	 ;

%%
/* No es necesario modificar esta sección ------------------ */

public Parser(Yylex lex, GestorErrores gestor, boolean debug) {
	this(debug);
	this.lex = lex;
	this.gestor = gestor;
}

// Métodos de acceso para el main -------------
public int parse() { return yyparse(); }
public AST getAST() { return raiz; }

// Funciones requeridas por Yacc --------------
void yyerror(String msg) {
	Token lastToken = (Token) yylval;
	gestor.error("Sintáctico", "Token = " + lastToken.getToken() + ", lexema = \"" + lastToken.getLexeme() + "\". " + msg, lastToken.getStart());
}

int yylex() {
	try {
		int token = lex.yylex();
		yylval = new Token(token, lex.lexeme(), lex.line(), lex.column());
		return token;
	} catch (IOException e) {
		return -1;
	}
}

private Yylex lex;
private GestorErrores gestor;
private AST raiz;
