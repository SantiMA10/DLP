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
%left '(' ')'

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
		  | 'IDENT' ':' tam_array tipo ';'
		  ;

funcion: 'IDENT' '(' parametros_ ')' ':' tipo_basico '{' definiciones_funcion sentencias_locales 'RETURN' expr ';' '}'
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

parametro: 'IDENT' ':' tipo_basico
		  ;

tipo: 'IDENT'
	| tipo_basico
	;

tipo_basico: 'INT'
			| 'FLOAT'
			| 'CHAR'
			;

tam_array: '[' expr ']'
		 | '[' expr ']' tam_array
		 ;

sentencias_locales : sentencia_local
				   | sentencia_local sentencias_locales;

sentencia_local: 'IDENT' '=' expr ';'
			   | 'PRINT' expr ';'
			   | 'READ' expr ';'
			   | 'IF' '(' expr ')' '{' sentencias_locales '}'
			   | 'IF' '(' expr ')' '{' sentencias_locales '}' 'ELSE' '{' sentencias_locales '}'
			   | 'WHILE' '(' expr ')' '{' sentencias_locales '}'
			   ;

expr: 'LITENT'
	| 'LITREAL'
	| 'LITCHAR'
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
