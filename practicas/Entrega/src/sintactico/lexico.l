// @author Ra˙l Izquierdo

/* -- No es necesario modificar esta parte ----------------------------------------------- */
package sintactico;

import java.io.*;
import main.*;
import ast.Position;

%%
%byaccj
%unicode
%line
%column
%public

%{
	public Yylex(Reader in, GestorErrores gestor) {
		this(in);
		this.gestor = gestor;
	}

	public int line() { return yyline + 1; }
	public int column() { return yycolumn + 1; }
	public String lexeme() { return yytext(); }

	// Traza para probar el lÈxico de manera independiente al sint·ctico
	public static void main(String[] args) throws Exception {
		Yylex lex = new Yylex(new FileReader(Main.programa), new GestorErrores());
		int token;
		while ((token = lex.yylex()) != 0)
			System.out.println("\t[" + lex.line() + ":" + lex.column() + "] Token: " + token + ". Lexema: " + lex.lexeme());
	}

	private GestorErrores gestor;
%}

%%

/* -- Modificar aquÌ. AÒadir reglas en esta secciÛn ----------------------------------------- */

[";+-/*><!{}()[]"]			{ return yytext().charAt(0); }
"<="						{ return Tokens.MENOR_IGUAL; }
">="						{ return Tokens.MAYOR_IGUAL; }
"=="						{ return Tokens.IGUAL; }
"!="						{ return Tokens.DISTINTO; }
"&&"						{ return Tokens.AND; }
"||"						{ return Tokens.OR; }
[0-9]+						{ return Tokens.LITENT; }
[0-9]+.[0-9]+				{ return Tokens.LITREAL; }
'[a-zA-ZÒ—0-9]'	| '\\n'		{ return Tokens.LITCHAR; }

int 						{ return Tokens.INT; }
float						{ return Tokens.FLOAT; }
char						{ return Tokens.CHAR; }
if 							{ return Tokens.IF; }
else 						{ return Tokens.ELSE; }
while						{ return Tokens.WHILE; }
read 						{ return Tokens.READ; }
print 						{ return Tokens.PRINT; }



/* -- Se aconseja dejar estas reglas al final ----------------------------------------------- */

"/*"([^*]|\*+[^*/])*\*+"/"		{ }		/* Comentario de varias lineas como este */
"//".*							{ }		// Comentario de una linea como este

[ \n\r]		{ }
"\t"		{ yycolumn += 3; } // Para que coincida con la info del editor de Eclipse (opcional). En eclipse: \t == 4 caracteres. En Jflex: \t == 1 car·cter.

.			{ gestor.error("LÈxico", "Cadena \"" + yytext() +"\" no reconocida.", new Position(line(), column())); }
