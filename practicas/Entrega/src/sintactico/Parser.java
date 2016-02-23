//### This file created by BYACC 1.8(/Java extension  1.14)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 6 "sintac.y"
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import main.*;
//#line 24 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short MAYOR_IGUAL=257;
public final static short MENOR_IGUAL=258;
public final static short IGUAL=259;
public final static short DISTINTO=260;
public final static short AND=261;
public final static short OR=262;
public final static short NOT=263;
public final static short VAR=264;
public final static short STRUCT=265;
public final static short IDENT=266;
public final static short RETURN=267;
public final static short INT=268;
public final static short FLOAT=269;
public final static short CHAR=270;
public final static short PRINT=271;
public final static short READ=272;
public final static short IF=273;
public final static short ELSE=274;
public final static short WHILE=275;
public final static short LITENT=276;
public final static short LITREAL=277;
public final static short LITCHAR=278;
public final static short CAST=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    4,    6,    6,    3,
    3,    5,    5,   11,   11,    9,    9,   14,   14,   15,
    7,    7,   10,   10,   10,    8,    8,   12,   12,   16,
   16,   16,   16,   16,   16,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,
};
final static short yylen[] = {                            2,
    1,    0,    2,    2,    1,    1,    6,    1,    2,    4,
    5,   13,    8,    0,    3,    1,    0,    1,    3,    3,
    1,    1,    1,    1,    1,    3,    4,    1,    2,    4,
    3,    3,    7,   11,    7,    1,    1,    1,    7,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    5,    6,    0,    4,
    0,    0,    3,    0,    0,    0,    0,   16,    0,   21,
   23,   24,   25,    0,    0,    0,   22,    0,    0,    0,
    0,    0,    0,   36,   37,   38,    0,    0,   10,    0,
    9,    0,   20,    0,    0,   19,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   11,    7,    0,    0,    0,   53,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   50,
   51,   52,   27,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   15,    0,    0,    0,    0,    0,   13,
   29,    0,    0,    0,   31,   32,    0,    0,    0,    0,
   30,    0,    0,    0,   39,    0,    0,    0,    0,    0,
    0,    0,   35,   12,    0,    0,    0,   34,
};
final static short yydgoto[] = {                          4,
    5,    6,   28,    7,    8,   29,   25,   26,   17,   27,
   66,   90,   38,   18,   19,   91,
};
final static short yysindex[] = {                      -118,
 -252, -231,   -1,    0,    0, -118,    0,    0,   10,    0,
  -81, -217,    0,  -90, -252,   11,   22,    0,   29,    0,
    0,    0,    0,  -40,   -3, -172,    0, -252,  -48,   15,
  -34, -217,  -40,    0,    0,    0,   24,   -2,    0,   27,
    0,   31,    0, -176,   15,    0,  -39,   15,  -40,  -40,
  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,
  -40,   34,    0,    0, -252,  -78,  -17,    0,   51,   19,
   19,  -55,  -55,  -46,  -46, -243, -243,  -96,  -96,    0,
    0,    0,    0, -176,   59,  -40,  -40,   87,   90,   17,
  -78, -176,   95,    0,  -40,    5,   12,  -40,  -40,    0,
    0,  -78,  -40,   33,    0,    0,  -32,  -16, -130,   -9,
    0,   21,   26,  -40,    0,  -78,  -78,   40,   46,   48,
   49, -122,    0,    0,   38,  -78,   52,    0,
};
final static short yyrindex[] = {                       156,
    0,    0,    0,    0,    0,  156,    0,    0,    0,    0,
    0,  118,    0,    0,    0,    0,    0,    0,  148,    0,
    0,    0,    0,    0,    0,    0,    0,   65,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  127,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -151,    0,    0,    0,    0,    0,    0,    0,  330,
  338,  140,  293,  318,  325,   91,   98,   62,   69,    0,
    0,    0,    0,  127,    0,    0,    0,    0,    0,    0,
 -113,  127,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -103,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  186,    0,   36,    0,    0,  168,  172,  147,    0,   78,
  -22,   84,  255,  200,    0,    0,
};
final static int YYTABLESIZE=431;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   24,   68,   51,   49,   53,   50,   54,   52,  112,   51,
   49,   28,   50,    9,   52,   57,   58,   59,   60,   61,
   53,   33,   54,   45,  113,   51,   49,   53,   50,   54,
   52,  115,   51,   49,   11,   50,   10,   52,   12,   51,
   49,   15,   50,   53,   52,   54,   51,   49,   16,   50,
   53,   52,   54,   51,   49,   39,   50,   53,   52,   54,
   51,   94,   31,  105,   53,   52,   54,   14,   30,  102,
  106,   53,   32,   54,   51,   49,   42,   50,   53,   52,
   54,   51,   49,   48,   50,   63,   52,   65,   44,   64,
   62,  111,   53,   20,   54,   21,   22,   23,  121,   53,
   84,   54,   48,   48,   48,   92,   48,   43,   48,   49,
   49,   49,   93,   49,   26,   49,   26,   26,   26,   95,
   48,   48,   67,   48,   24,   69,   98,   49,   49,   99,
   49,   46,   46,   46,  103,   46,  114,   46,   47,   47,
   47,  100,   47,  116,   47,    1,    2,    3,  117,   46,
   46,  125,   46,   28,   48,    2,   47,   47,   17,   47,
  126,   49,   33,   33,   59,   60,   61,   33,   33,   33,
  122,   33,  123,  124,  101,   20,  128,   21,   22,   23,
   40,   40,   40,   46,   40,  109,   40,   85,   18,    8,
   47,   13,   86,   87,   88,   41,   89,   40,   40,  119,
  120,   55,   56,   57,   58,   59,   60,   61,   83,  127,
   55,   56,   57,   58,   59,   60,   61,   55,   56,   57,
   58,   59,   60,   61,   55,   56,   57,   58,   59,   60,
   61,   46,   40,    0,    0,   34,   35,   36,   37,    0,
   55,   56,   57,   58,   59,   60,   61,   55,   56,   57,
   58,   59,   60,   61,   55,   56,   57,   58,   59,   60,
   61,   55,   56,   57,   58,   59,   60,   61,   55,   56,
   57,   58,   59,   60,   61,   55,   56,   57,   58,   59,
   60,   61,   21,   22,   23,    0,    0,   47,    0,   55,
   56,   57,   58,   59,   60,   61,   55,   56,   57,   58,
   59,   60,   61,   70,   71,   72,   73,   74,   75,   76,
   77,   78,   79,   80,   81,   82,    0,    0,   48,   48,
   48,   48,    0,    0,    0,   49,   49,   49,   49,    0,
    0,    0,    0,   41,   41,   41,    0,   41,    0,   41,
   96,   97,    0,    0,    0,    0,    0,   46,   46,  104,
    0,   41,  107,  108,   47,   47,    0,  110,   44,   44,
   44,    0,   44,    0,   44,   45,   45,   45,  118,   45,
   42,   45,   42,    0,   42,    0,   44,   44,   43,   44,
   43,    0,   43,   45,   45,   41,   45,    0,   42,    0,
    0,    0,   14,    0,    0,    0,   43,   14,   14,   14,
    0,   14,    0,    0,    0,    0,    0,    0,    0,    0,
   44,    0,    0,    0,    0,    0,    0,   45,    0,    0,
    0,    0,   42,    0,    0,    0,    0,    0,    0,    0,
   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   91,   41,   42,   43,   60,   45,   62,   47,   41,   42,
   43,  125,   45,  266,   47,  259,  260,  261,  262,  263,
   60,  125,   62,   58,   41,   42,   43,   60,   45,   62,
   47,   41,   42,   43,  266,   45,    1,   47,   40,   42,
   43,  123,   45,   60,   47,   62,   42,   43,  266,   45,
   60,   47,   62,   42,   43,   59,   45,   60,   47,   62,
   42,   84,   41,   59,   60,   47,   62,   58,   58,   92,
   59,   60,   44,   62,   42,   43,  125,   45,   60,   47,
   62,   42,   43,   60,   45,   59,   47,  264,  123,   59,
   93,   59,   60,  266,   62,  268,  269,  270,   59,   60,
   65,   62,   41,   42,   43,  123,   45,   30,   47,   41,
   42,   43,   62,   45,  266,   47,  268,  269,  270,   61,
   59,   60,   45,   62,   91,   48,   40,   59,   60,   40,
   62,   41,   42,   43,   40,   45,  267,   47,   41,   42,
   43,  125,   45,  123,   47,  264,  265,  266,  123,   59,
   60,  274,   62,  267,   93,    0,   59,   60,   41,   62,
  123,   93,  266,  267,  261,  262,  263,  271,  272,  273,
  125,  275,  125,  125,   91,  266,  125,  268,  269,  270,
   41,   42,   43,   93,   45,  102,   47,  266,   41,  125,
   93,    6,  271,  272,  273,   28,  275,   26,   59,  116,
  117,  257,  258,  259,  260,  261,  262,  263,   62,  126,
  257,  258,  259,  260,  261,  262,  263,  257,  258,  259,
  260,  261,  262,  263,  257,  258,  259,  260,  261,  262,
  263,   32,   93,   -1,   -1,  276,  277,  278,  279,   -1,
  257,  258,  259,  260,  261,  262,  263,  257,  258,  259,
  260,  261,  262,  263,  257,  258,  259,  260,  261,  262,
  263,  257,  258,  259,  260,  261,  262,  263,  257,  258,
  259,  260,  261,  262,  263,  257,  258,  259,  260,  261,
  262,  263,  268,  269,  270,   -1,   -1,   33,   -1,  257,
  258,  259,  260,  261,  262,  263,  257,  258,  259,  260,
  261,  262,  263,   49,   50,   51,   52,   53,   54,   55,
   56,   57,   58,   59,   60,   61,   -1,   -1,  257,  258,
  259,  260,   -1,   -1,   -1,  257,  258,  259,  260,   -1,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   -1,   47,
   86,   87,   -1,   -1,   -1,   -1,   -1,  257,  258,   95,
   -1,   59,   98,   99,  257,  258,   -1,  103,   41,   42,
   43,   -1,   45,   -1,   47,   41,   42,   43,  114,   45,
   41,   47,   43,   -1,   45,   -1,   59,   60,   41,   62,
   43,   -1,   45,   59,   60,   93,   62,   -1,   59,   -1,
   -1,   -1,  266,   -1,   -1,   -1,   59,  271,  272,  273,
   -1,  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"\"MAYOR_IGUAL\"","\"MENOR_IGUAL\"",
"\"IGUAL\"","\"DISTINTO\"","\"AND\"","\"OR\"","\"NOT\"","\"VAR\"","\"STRUCT\"",
"\"IDENT\"","\"RETURN\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"PRINT\"",
"\"READ\"","\"IF\"","\"ELSE\"","\"WHILE\"","\"LITENT\"","\"LITREAL\"",
"\"LITCHAR\"","\"CAST\"",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencias",
"sentencias :",
"sentencias : sentencia sentencias",
"sentencia : \"VAR\" definicion",
"sentencia : struct",
"sentencia : funcion",
"struct : \"STRUCT\" \"IDENT\" '{' definiciones '}' ';'",
"definiciones : definicion",
"definiciones : definicion definiciones",
"definicion : \"IDENT\" ':' tipo ';'",
"definicion : \"IDENT\" ':' tam_array tipo ';'",
"funcion : \"IDENT\" '(' parametros_ ')' ':' tipo_basico '{' definiciones_funcion sentencias_locales \"RETURN\" expr ';' '}'",
"funcion : \"IDENT\" '(' parametros_ ')' '{' definiciones_funcion sentencias_locales '}'",
"definiciones_funcion :",
"definiciones_funcion : \"VAR\" definicion definiciones_funcion",
"parametros_ : parametros",
"parametros_ :",
"parametros : parametro",
"parametros : parametro ',' parametros",
"parametro : \"IDENT\" ':' tipo_basico",
"tipo : \"IDENT\"",
"tipo : tipo_basico",
"tipo_basico : \"INT\"",
"tipo_basico : \"FLOAT\"",
"tipo_basico : \"CHAR\"",
"tam_array : '[' expr ']'",
"tam_array : '[' expr ']' tam_array",
"sentencias_locales : sentencia_local",
"sentencias_locales : sentencia_local sentencias_locales",
"sentencia_local : \"IDENT\" '=' expr ';'",
"sentencia_local : \"PRINT\" expr ';'",
"sentencia_local : \"READ\" expr ';'",
"sentencia_local : \"IF\" '(' expr ')' '{' sentencias_locales '}'",
"sentencia_local : \"IF\" '(' expr ')' '{' sentencias_locales '}' \"ELSE\" '{' sentencias_locales '}'",
"sentencia_local : \"WHILE\" '(' expr ')' '{' sentencias_locales '}'",
"expr : \"LITENT\"",
"expr : \"LITREAL\"",
"expr : \"LITCHAR\"",
"expr : \"CAST\" '<' tipo_basico '>' '(' expr ')'",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '<' expr",
"expr : expr '>' expr",
"expr : expr \"MAYOR_IGUAL\" expr",
"expr : expr \"MENOR_IGUAL\" expr",
"expr : expr \"IGUAL\" expr",
"expr : expr \"DISTINTO\" expr",
"expr : expr \"AND\" expr",
"expr : expr \"OR\" expr",
"expr : expr \"NOT\" expr",
"expr : '(' expr ')'",
};

//#line 113 "sintac.y"
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
//#line 401 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
