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
%left 'AND' 'OR' '!'
%left '.'
%left '[' ']'
%left '(' ')'

%%

/* Añadir las reglas en esta sección ----------------------- */

programa: sentencias 				{ raiz = new Programa($1); }
		;

sentencias: 
		  | sentencia sentencias 	{ }
		  ;

sentencia: 'VAR' definicion
		 | struct
		 | funcion
		 ;

struct: 'STRUCT' 'IDENT' '{' definiciones '}' ';' {  }
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

parametros: 'IDENT' ':' tipo
		  | 'IDENT' ':' tipo ',' parametros
		  ;

tipo: 'IDENT'				 {}
	| 'INT'					 { $$ = new Int($1); }
	| 'FLOAT'				 { $$ = new Float($1); }
	| 'CHAR'				 { $$ = new Char($1); }
	| '[' 'LITENT' ']' tipo  { $$ = new Array($2, $4); }
	;

sentencias_locales : sentencia_local
				   | sentencia_local sentencias_locales;

sentencia_local: expr '=' expr ';'																	{ $$ = new Asignacion($1, $3); }
			   | 'PRINT' expr ';'																	{ $$ = new Print($2); }
			   | 'READ' expr ';'																	{ $$ = new Read($2); }
			   | 'IF' '(' expr ')' '{' sentencias_locales '}'										{ $$ = new If($3, $&, null ); }
			   | 'IF' '(' expr ')' '{' sentencias_locales '}' 'ELSE' '{' sentencias_locales '}'		{ $$ = new If($3, $&, $10 ); }
			   | 'WHILE' '(' expr ')' '{' sentencias_locales '}'									{ $$ = new While($3, $6); }
			   | 'IDENT' '(' paso_parametros_ ')' ';'												{ $$ = new Invocacion($3); }
			   | 'RETURN' expr ';'																	{ $$ = new Return($2); }
			   | 'RETURN' ';'																		{ $$ = new Return(null); }
			   ;

expr: 'LITENT'							{ $$ = new Lintent($1); }
	| 'LITREAL'							{ $$ = new Lintreal($1); }
	| 'LITCHAR'							{ $$ = new Lintchar($1); }
	| 'IDENT'							{ $$ = new Var($1); }
	| 'CAST' '<' tipo '>' '(' expr ')'	{ $$ = new Cast( $3, $6); }
	| expr '*' expr						{ $$ = new Op_bin( $1, "*", $3 ); }
	| expr '/' expr						{ $$ = new Op_bin( $1, "/", $3 ); }
	| expr '+' expr						{ $$ = new Op_bin( $1, "+", $3 ); }
	| expr '-' expr						{ $$ = new Op_bin( $1, "-", $3 ); }
	| expr '<' expr						{ $$ = new Op_bin( $1, "<", $3 ); }
	| expr '>' expr						{ $$ = new Op_bin( $1, ">", $3 ); }
	| expr 'MAYOR_IGUAL' expr			{ $$ = new Op_bin( $1, ">=", $3 ); }
	| expr 'MENOR_IGUAL' expr			{ $$ = new Op_bin( $1, "<=", $3 ); }
	| expr 'IGUAL' expr					{ $$ = new Op_bin( $1, "==", $3 ); }
	| expr 'DISTINTO' expr				{ $$ = new Op_bin( $1, "!=", $3 ); }
	| expr 'AND' expr					{ $$ = new Op_bin( $1, "&&", $3 ); }
	| expr 'OR' expr					{ $$ = new Op_bin( $1, "||", $3 ); }
	| '!' expr							{ $$ = new Op_un($2); }
	| '(' expr ')'
	| expr '.' expr						{ $$ = new Op_bin( $1, ".", $3 );}
	| expr '[' expr ']'					{ $$ = new Op_bin( $1, "[]", $3 ); }
	| 'IDENT' '(' paso_parametros_ ')' 	{ $$ = new Invocacion( $3 ); }
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