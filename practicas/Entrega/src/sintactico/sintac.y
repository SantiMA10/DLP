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
%left '<' '>' 'MAYOR_IGUAL' 'MENOR_IGUAL' 'IGUAL' 'DISTINTO' 'AND' 'OR' 'NOT'

%%

/* Añadir las reglas en esta sección ----------------------- */

programa: sentencias
		;

sentencias: 
		  | sentencia sentencias;

sentencia: 'VAR' definicion
		 | struct
		 | funcion
		 ;

struct: 'STRUCT' 'IDENT' '{' definiciones '}' ';'
	  ;

definiciones: definicion
			| definicion definiciones
			;

definicion: 'IDENT' ':' tipo ';'
		  ;

funcion: 'IDENT' '(' parametros_ ')' ':' tipo '{' definiciones_funcion sentencias_locales '}'
		| 'IDENT' '(' parametros_ ')' '{' definiciones_funcion sentencias_locales '}'
		;

definiciones_funcion: 
			| 'VAR' definicion definiciones_funcion
			;

parametros_: parametros
			|
			;

parametros: parametro
		  | parametro ',' parametros
		  ;

parametro: 'IDENT' ':' tipo
		  ;

tipo: 'IDENT'
	| 'INT'
	| 'FLOAT'
	| 'CHAR'
	| '[' 'LITENT' ']' tipo
	;

sentencias_locales : sentencia_local
				   | sentencia_local sentencias_locales;

sentencia_local: expr '=' expr ';'
			   | 'PRINT' expr ';'
			   | 'READ' expr ';'
			   | 'IF' '(' expr ')' '{' sentencias_locales '}'
			   | 'IF' '(' expr ')' '{' sentencias_locales '}' 'ELSE' '{' sentencias_locales '}'
			   | 'WHILE' '(' expr ')' '{' sentencias_locales '}'
			   | 'IDENT' '(' paso_parametros_ ')' ';'
			   | 'RETURN' expr ';'
			   | 'RETURN' ';'
			   ;

expr: 'LITENT'
	| 'LITREAL'
	| 'LITCHAR'
	| 'IDENT'
	| 'CAST' '<' tipo_basico '>' '(' expr ')'
	| expr '*' expr
	| expr '/' expr
	| expr '+' expr
	| expr '-' expr
	| expr '<' expr
	| expr '>' expr
	| expr 'MAYOR_IGUAL' expr
	| expr 'MENOR_IGUAL' expr
	| expr 'IGUAL' expr
	| expr 'DISTINTO' expr
	| expr 'AND' expr
	| expr 'OR' expr
	| expr 'NOT' expr
	| '(' expr ')'
	| expr '.' expr
	| expr '[' expr ']'
	| 'IDENT' '(' paso_parametros_ ')'
	;

paso_parametros_: paso_parametros
			|
			;

paso_parametros: expr
		  | expr ',' paso_parametros
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
