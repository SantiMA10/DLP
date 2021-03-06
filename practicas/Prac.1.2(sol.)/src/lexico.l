/*
A�adir EN ESTE ORDEN:
- Constantes reales: 3.15, 78.23, ... (parte entera obligatoria/ parte decimal OBLIGATORIA)
- Operadores aritm�ticos y otros simbolos:  + - * / . : = ; [ ] ( ) { } < >
- Palabras reservadas: if, while, read, write, int y real
- A�adir al mensaje de error l�xico la informaci�n de l�nea y columna
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

[a-zA-Zn�][a-zA-Z0-9_n�]*   { return Tokens.IDENT; }
[0-9]+			    { return Tokens.LITENT; }
[0-9]+"."[0-9]+		    { return Tokens.LITREAL; }

[ \t\n\r]     { }

.	  { System.out.println("Error l�xico en " + line() + ":" + column() + ". Lexema = [" + yytext() + "]"); }
