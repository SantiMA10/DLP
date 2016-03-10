// @author Raúl Izquierdo

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

%%

/* Añadir las reglas en esta sección ----------------------- */

programa: 'DATA' variables 'CODE' sentencias	{ raiz = new Programa($2, $4); }
    
//--------
variables:					{ $$ = new ArrayList(); }		
    | variables variable	{ $$ = $1; ((List)$1).add($2); }
    
variable:  tipo 'IDENT' ';'	{ $$ = new DefVariable($1, $2); }
    
tipo: 'INT'		{ $$ = new IntType(); }
    | 'REAL'	{ $$ = new RealType(); }
	
//--------
sentencias:					{ $$ = new ArrayList(); }		
    | sentencias sentencia	{ $$ = $1; ((List)$1).add($2); }

sentencia: 'PRINT' expr ';'	{ $$ = new Print($2); }
		| expr '=' expr ';'	{ $$ = new Asigna($1, $3); }

//--------
expr: expr '+' expr		{ $$ = new ExprAritmetica($1, "+", $3); }
    | expr '-' expr		{ $$ = new ExprAritmetica($1, "-", $3); }
    | expr '*' expr		{ $$ = new ExprAritmetica($1, "*", $3); }	
    | expr '/' expr		{ $$ = new ExprAritmetica($1, "/", $3); }	
    | '(' expr ')'		{ $$ = $2; }
    | 'IDENT'			{ $$ = new Variable($1); }
    | 'LITERALINT'		{ $$ = new LiteralInt($1); }
    | 'LITERALREAL'		{ $$ = new LiteralReal($1); }
	
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
