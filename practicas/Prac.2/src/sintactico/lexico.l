package sintactico;

%%
%byaccj
%unicode
%line
%column
%public

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
[+<>;(){}=]  		{ return yytext().charAt(0); }

DATA			{ return Parser.DATA; }
CODE			{ return Parser.CODE; }

real 			{ return Parser.REAL; }
int 			{ return Parser.INT; }
read        	{ return Parser.READ; }
print|write 	{ return Parser.PRINT; }
if				{ return Parser.IF; }
else			{ return Parser.ELSE; }
while			{ return Parser.WHILE; }

[a-zA-Z]+ 		{ return Parser.IDENT; }
[0-9]+     		{ return Parser.LITENT; }
[0-9].[0-9]+	{ return Parser.LITREAL; }

"/*"~"*/"	{ }

[ \n\r\t]     { }

.	  { System.out.println("Error léxico en " + line() + ":" + column() + ". Lexema = \"" + yytext() + "\""); }
