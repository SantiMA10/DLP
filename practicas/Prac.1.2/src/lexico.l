/*
Añadir EN ESTE ORDEN:
- Constantes reales: 3.15, 78.23, ... (parte entera obligatoria/ parte decimal OBLIGATORIA)
- Operadores aritméticos y otros simbolos:  + - * / . : = ; [ ] ( ) { } < >
- Palabras reservadas: if, while, read, write, int y real
- Añadir al mensaje de error léxico la información de línea y columna
*/

import java.util.HashMap;
import java.util.Map;
%%
%byaccj
%unicode

%line
%column

%{

	public int line() { return yyline+1; }
	public int column() { return yycolumn+1; }
    public String lexeme() {
		return yytext();
    }
    
%}

%%

while										{ return Tokens.WHILE; }
if 											{ return Tokens.IF; }
int											{ return Tokens.INT; }
real										{ return Tokens.REAL; }
write										{ return Tokens.WRITE; }
read										{ return Tokens.READ; }
["+-*/.:=;[](){}<>"]						{ return yytext().charAt(0); }
[a-zA-Z][a-zA-Z0-9_]*						{ return Tokens.IDENT; }
[0-9]+										{ return Tokens.LITENT; }
[0-9]+"."[0-9]+ 							{ return Tokens.LITREAL; }


[ \t\n\r]     { }
.	  { System.out.println("[" + line() + ":" + column() + "]\tError lexico " + ": " + yytext());}

