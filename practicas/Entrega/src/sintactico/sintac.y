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
%left 'AND' 'OR' 
%left '<' '>' 
%left 'MAYOR_IGUAL' 'MENOR_IGUAL'
%left 'IGUAL' 'DISTINTO' '.' 
%left '+' '-'
%left '*' '/'
%left '(' '!' '[' 

%%

/* Añadir las reglas en esta sección ----------------------- */

programa: sentencias 				{ raiz = new Programa($1); }
		;

sentencias: 						{ $$ = new ArrayList<Sentencia>(); }
		  | sentencias sentencia  	{ ((ArrayList<Sentencia>) $1).add((Sentencia)$2); $$ = $1; }
		  ;

sentencia: 'VAR' definicion 		{ ((DefVar)$2).setAmbito("global"); $$ = $2; }
		 | struct 					{ $$ = $1; }
		 | funcion 					{ $$ = $1; }
		 ;

struct: 'STRUCT' 'IDENT' '{' definiciones '}' ';' { $$ = new Struct($2, $4); }
	  ;

definiciones: definicion 				{ $$ = new ArrayList<DefVar>(); ((ArrayList<DefVar>) $$).add((DefVar)$1); }
			| definiciones definicion 	{ ((ArrayList<DefVar>) $1).add((DefVar)$2); $$ = $1; }
			;

definicion: 'IDENT' ':' tipo ';' 		{ $$ = new DefVar($1, $3, ""); }
		  ;

funcion: 'IDENT' '(' parametros_ ')' ':' tipo '{' definiciones_funcion sentencias_locales '}'	{ $$ = new Funcion($1, $3, $8, $9, $6); }
		| 'IDENT' '(' parametros_ ')' '{' definiciones_funcion sentencias_locales '}'			{ $$ = new Funcion($1, $3, $6, $7, null); }
		;

definiciones_funcion: 										{ $$ = new ArrayList<DefVar>(); }
					| definiciones_funcion 'VAR' definicion { ((DefVar)$3).setAmbito("var"); ((ArrayList<DefVar>) $1).add((DefVar)$3); $$ = $1; }
					;

parametros_: parametros { $$ = $1; }
			|			{ $$ = new ArrayList<Parametro>();}
			;

parametros: parametro					{ $$ = new ArrayList<Parametro>(); ((ArrayList<Parametro>) $$).add((Parametro)$1); }
		  | parametros ',' parametro 	{ ((ArrayList<Parametro>) $1).add((Parametro)$3); $$ = $1; }
		  ;

parametro: 'IDENT' ':' tipo { $$ = new Parametro($1, $3); }
		 ;

tipo: 'IDENT'				 { $$ = new StructType($1); }
	| 'INT'					 { $$ = new IntType(); }
	| 'FLOAT'				 { $$ = new RealType(); }
	| 'CHAR'				 { $$ = new CharType(); }
	| '[' 'LITENT' ']' tipo  { $$ = new ArrayType($4, $2); }
	;

sentencias_locales :   									{  $$ = new ArrayList<Sent_func>(); }
				   | sentencias_locales sentencia_local { ((ArrayList<Sent_func>) $1).add((Sent_func)$2); $$ = $1; }
				   ;

sentencia_local: expr '=' asignado ';'																{ $$ = new Asignacion($1, $3); }
			   | 'PRINT' expr ';'																	{ $$ = new Print($2); }
			   | 'READ' expr ';'																	{ $$ = new Read($2); }
			   | 'IF' '(' expr ')' '{' sentencias_locales '}'										{ $$ = new If($3, $6, null ); }
			   | 'IF' '(' expr ')' '{' sentencias_locales '}' 'ELSE' '{' sentencias_locales '}'		{ $$ = new If($3, $6, $10 ); }
			   | 'WHILE' '(' expr ')' '{' sentencias_locales '}'									{ $$ = new While($3, $6); }
			   | 'IDENT' '(' paso_parametros_ ')' ';'												{ $$ = new Invocacion($1, $3, "llamada"); }
			   | 'RETURN' expr ';'																	{ $$ = new Return($2); }
			   | 'RETURN' ';'																		{ $$ = new Return(null); }
			   ;

asignado: expr 					{ $$ = new ArrayList<Expr>(); ((ArrayList<Expr>) $$).add((Expr)$1); }
		| asignado '=' expr		{ ((ArrayList<Expr>) $1).add((Expr)$3); $$ = $1;  }
		;

expr: 'LITENT'							{ $$ = new Lintent($1); }
	| 'LITREAL'							{ $$ = new Lintreal($1); }
	| 'LITCHAR'							{ $$ = new Lintchar($1); }
	| 'IDENT'							{ $$ = new Var($1); }
	| 'CAST' '<' tipo '>' '(' expr ')'	{ $$ = new Cast( $3, $6); }
	| expr '*' expr						{ $$ = new ExpresionNumerica( $1, "*", $3 ); }
	| expr '/' expr						{ $$ = new ExpresionNumerica( $1, "/", $3 ); }
	| expr '+' expr						{ $$ = new ExpresionNumerica( $1, "+", $3 ); }
	| expr '-' expr						{ $$ = new ExpresionNumerica( $1, "-", $3 ); }
	| expr '<' expr						{ $$ = new ExpresionNumerica( $1, "<", $3 ); }
	| expr '>' expr						{ $$ = new ExpresionNumerica( $1, ">", $3 ); }
	| expr 'MAYOR_IGUAL' expr			{ $$ = new ExpresionNumerica( $1, ">=", $3 ); }
	| expr 'MENOR_IGUAL' expr			{ $$ = new ExpresionNumerica( $1, "<=", $3 ); }
	| expr 'IGUAL' expr					{ $$ = new ExpresionNumerica( $1, "==", $3 ); }
	| expr 'DISTINTO' expr				{ $$ = new ExpresionNumerica( $1, "!=", $3 ); }
	| expr 'AND' expr					{ $$ = new ExpresionLogica( $1, "&&", $3 ); }
	| expr 'OR' expr					{ $$ = new ExpresionLogica( $1, "||", $3 ); }
	| '!' expr							{ $$ = new OperacionUnaria("!", $2); }
	| '(' expr ')'						{ $$ = new OperacionUnaria("()", $2); }
	| expr '.' 'IDENT'					{ $$ = new AccesoStruct( $1, $3 );}
	| expr '[' expr ']'					{ $$ = new AccesoArray( $1, $3 ); }
	| 'IDENT' '(' paso_parametros_ ')' 	{ $$ = new Invocacion( $1, $3, "parametro" ); }
	;

paso_parametros_: paso_parametros { $$ = $1; }
				|				  { $$ = new ArrayList<Expr>(); }
				;	

paso_parametros: expr 						{ $$ = new ArrayList<Expr>(); ((ArrayList<Expr>) $$).add((Expr)$1); }
			   | paso_parametros ',' expr 	{ ((ArrayList<Expr>) $1).add((Expr)$3); $$ = $1; }
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