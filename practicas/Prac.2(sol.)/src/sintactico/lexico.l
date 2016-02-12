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
[+<>;(){}=]  { return yytext().charAt(0); }

DATA	{ return Parser.DATA; }
real	{ return Parser.REAL; }
int		{ return Parser.INT; }

CODE	{ return Parser.CODE; }
print|write { return Parser.PRINT; }
read	{ return Parser.READ; }
if		{ return Parser.IF; }
else	{ return Parser.ELSE; }
while	{ return Parser.WHILE; }

[0-9]+     				{ return Parser.LITENT; }
[0-9]+\.[0-9]*			{ return Parser.LITREAL; }
[a-zA-Z][a-zA-Z0-9_]*	{ return Parser.IDENT; }

"/*"~"*/"	{ }

[ \n\r\t]     { }

.	  { System.out.println("Error léxico en " + line() + ":" + column() + ". Lexema = \"" + yytext() + "\""); }
