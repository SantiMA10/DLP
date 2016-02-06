/*
Añadir EN ESTE ORDEN:
- Constantes reales: 3.15, 78.23, ... (parte entera obligatoria/ parte decimal OBLIGATORIA)
- Operadores aritméticos y otros simbolos:  + - * / . : = ; [ ] ( ) { } < >
- Palabras reservadas: if, while, read, write, int y real
- Añadir al mensaje de error léxico la información de línea y columna
*/

%%
%byaccj
%unicode
%line
%column

%{
    public String lexeme() {
	return yytext();
    }
    public int line() {
	return yyline + 1;
    }
    public int column() {
	return yycolumn + 1;
    }
%}

%%

[+\-*/.:=;\[\](){}<>]	{ return yytext.charAt(0); }

if	    { return Tokens.IF; }
while	    { return Tokens.WHILE; }
read	    { return Tokens.READ; }
write	    { return Tokens.WRITE; }
int	    { return Tokens.INT; }
real	    { return Tokens.REAL; }

[a-zA-ZnÑ][a-zA-Z0-9_nÑ]*   { return Tokens.IDENT; }
[0-9]+			    { return Tokens.LITENT; }
[0-9]+"."[0-9]+		    { return Tokens.LITREAL; }

[ \t\n\r]     { }

.	  { System.out.println("Error léxico en " + line() + ":" + column() + ". Lexema = [" + yytext() + "]"); }
