// @author Ra�l Izquierdo
// @author Santiago Martin

/* No es necesario modificar esta secci�n ------------------ */
%{
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import main.*;
%}

/* Precedencias aqu� --------------------------------------- */
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

/* A�adir las reglas en esta secci�n ----------------------- */

programa: sentencias 				{ raiz = new Programa($1); }
		;

sentencias: 						{ $$ = new ArrayList<Sentencia>(); }
		  | sentencias sentencia  	{ ((ArrayList<Sentencia>) $1).add($2); $$ = $1; }
		  ;

sentencia: 'VAR' definicion 		{ $$ = $1; }
		 | struct 					{ $$ = $1; }
		 | funcion 					{ $$ = $1; }
		 ;

struct: 'STRUCT' 'IDENT' '{' definiciones '}' ';' { $$ = new Struct($2, $3); }
	  ;

definiciones: definicion 				{ $$ = new ArrayList<DefVar>(); }
			| definiciones definicion 	{ ((ArrayList<DefVar>) $1).add($2); $$ = $1; }
			;

definicion: 'IDENT' ':' tipo ';' 		{ $$ = new DefVar($1, $3); }
		  ;

funcion: 'IDENT' '(' parametros_ ')' ':' tipo '{' definiciones_funcion sentencias_locales '}'	{ $$ = new Funcion($1, $3, $6, $8, $9); }
		| 'IDENT' '(' parametros_ ')' '{' definiciones_funcion sentencias_locales '}'			{ $$ = new Funcion($1, $3, $6, $7); }
		;

definiciones_funcion: 										{ $$ = new ArrayList<DefVar>(); }
					| definiciones_funcion 'VAR' definicion { ((ArrayList<DefVar>) $1).add($2); $$ = $1; }
					;

parametros_: parametros { $$ = new ArrayList<Parametro>(); }
			|
			;

parametros: 'IDENT' ':' tipo 				{ $$ = new Parametro($1, $3); }
		  | parametros ',' IDENT' ':' tipo 	{ ((ArrayList<Parametro>) $1).add(new Parametro($3, $5)); $$ = $1; }
		  ;

tipo: 'IDENT'				 {}
	| 'INT'					 { $$ = new Int($1); }
	| 'FLOAT'				 { $$ = new Float($1); }
	| 'CHAR'				 { $$ = new Char($1); }
	| '[' 'LITENT' ']' tipo  { $$ = new Array($2, $4); }
	;

sentencias_locales : sentencia_local  					{ $$ = $1; }
				   | sentencias_locales sentencia_local { ((ArrayList<Parametro>) $1).add($2); $$ = $1; }
				   ;

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
	| '!' expr							{ $$ = new Op_un("!", $2); }
	| '(' expr ')'						{ $$ = new Op_un("()", $2); }
	| expr '.' expr						{ $$ = new Op_bin( $1, ".", $3 );}
	| expr '[' expr ']'					{ $$ = new Op_bin( $1, "[]", $3 ); }
	| 'IDENT' '(' paso_parametros_ ')' 	{ $$ = new Invocacion( $3 ); }
	;

paso_parametros_: paso_parametros { $$ = new ArrayList<Expr>(); }
				|
				;	

paso_parametros: expr 						{ $$ = $1; }
			   | paso_parametros ',' expr 	{ ((ArrayList<Expr>) $1).add($2); $$ = $1; }
			   ;

%%
/* No es necesario modificar esta secci�n ------------------ */
public Parser(Yylex lex, GestorErrores gestor, boolean debug) {
	this(debug);
	this.lex = lex;
	this.gestor = gestor;
}
// M�todos de acceso para el main -------------
public int parse() { return yyparse(); }
public AST getAST() { return raiz; }
// Funciones requeridas por Yacc --------------
void yyerror(String msg) {
	Token lastToken = (Token) yylval;
	gestor.error("Sint�ctico", "Token = " + lastToken.getToken() + ", lexema = \"" + lastToken.getLexeme() + "\". " + msg, lastToken.getStart());
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