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
public final static short STRUCT=264;
public final static short IDENT=265;
public final static short VAR=266;
public final static short INT=267;
public final static short FLOAT=268;
public final static short CHAR=269;
public final static short RETURN=270;
public final static short LITCHAR=271;
public final static short READ=272;
public final static short PRINT=273;
public final static short CAST=274;
public final static short LITENT=275;
public final static short LITREAL=276;
public final static short IF=277;
public final static short ELSE=278;
public final static short WHILE=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    3,    2,    2,    5,    6,
    6,    7,    7,    7,    4,    4,    9,    9,   10,   10,
   12,   12,   13,   11,   11,   11,   11,   11,   11,   11,
   11,   11,    8,    8,    8,    8,    8,    8,    8,    8,
    8,    8,    8,    8,    8,    8,    8,   14,   14,   15,
   15,   16,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    5,    1,    2,    6,    0,
    4,    1,    1,    1,    1,    2,   11,    7,    1,    0,
    1,    3,    3,    1,    3,    3,    4,    1,    1,    1,
    1,    7,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    1,    3,    7,
   11,    7,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    3,    4,    5,    0,    0,
    0,    0,    0,    2,    8,   16,    0,    0,    0,   19,
    0,    0,    0,    0,    0,    0,    0,    0,    6,   12,
   13,   14,   23,    0,    0,   22,   33,   34,    0,    0,
    0,   24,    0,    0,    0,    0,    0,    0,    0,   28,
   29,   30,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    9,    0,    0,
    0,    0,    0,    0,   18,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   45,   46,   47,
   11,   49,   25,   26,    0,    0,    0,    0,    0,    0,
    0,    0,   27,    0,    0,    0,    0,    0,    0,    0,
    0,   17,   32,    0,   52,    0,    0,    0,   51,
};
final static short yydgoto[] = {                          4,
    5,    6,    7,    8,    9,   28,   33,   48,   10,   19,
   49,   20,   21,   50,   51,   52,
};
final static short yysindex[] = {                      -244,
 -262,  -25, -238,    0, -244,    0,    0,    0, -210, -203,
  -47, -195,   30,    0,    0,    0, -210,   48,   86,    0,
   93,   50,   19, -153,  -56, -195, -178, -153,    0,    0,
    0,    0,    0,   32, -153,    0,    0,    0,  133,   80,
  114,    0,   32,   32,   97,  122,  131,  141,  -53,    0,
    0,    0,   68, -178, -178, -178, -178, -178, -178, -178,
 -178, -178, -178, -178, -178, -178,   50,    0,  -92,    7,
   14, -153, -178,   32,    0,   32,   32,   78,   78,   33,
   33,   24,   24, -218, -218,  -94,  -94,    0,    0,    0,
    0,    0,    0,    0,  119,  104,  -24,   58,  -61,  145,
   69,   71,    0,   32,   32, -244, -244,  -51,  -22,   74,
   77,    0,    0,  -73,    0,   84, -244,   81,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  208,    0,    0,    0,    4,    1,
    0,  169,    0,    0,    0,    0,    0,    0,    0,    0,
  171,  -71,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -13,    0,    0,    0,    0,    0,    0,  -12,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -71,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   49,  118,   89,
  111,   40,   62,   -7,   18,  -36,  -29,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   10,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                       210,
   83,   41,    0,  203,    0,  149,   56,  351,    0,    0,
  196,  191,    0,  150,    0,    0,
};
final static int YYTABLESIZE=424;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         76,
   15,   35,   11,    7,   43,   43,   43,   76,   43,   76,
   43,   44,   44,   44,   12,   44,  102,   44,  113,    1,
    2,    3,   43,   43,   43,   43,   13,   48,   31,   44,
   44,   44,   44,   40,   40,   40,   76,   40,   76,   40,
   62,   63,   64,   65,   66,   48,   31,   48,   31,   15,
   50,   40,   40,   40,   40,    3,   43,   23,   42,   42,
   42,    2,   42,   44,   42,   93,   34,   76,   50,   18,
   50,   75,   94,  112,   76,   17,   42,   42,   42,   42,
   41,   41,   41,   40,   41,   40,   41,   22,   43,   38,
   53,   38,   58,   38,   59,   44,   37,   38,   41,   41,
   41,   41,   39,   39,   39,   24,   39,   38,   39,   38,
   42,   48,   31,   30,   31,   32,  103,   40,   76,   56,
   39,   39,   39,   39,   57,   15,   25,   95,    7,   35,
   35,   35,   41,   35,   50,   35,   26,   58,   68,   59,
   27,   38,   42,   29,  101,   56,   54,   35,   55,   35,
   57,   36,   36,   36,   39,   36,   72,   36,   37,   69,
   37,   73,   37,   58,   41,   59,   64,   65,   66,   36,
   74,   36,   41,   38,   56,   54,   37,   55,   37,   57,
  100,   35,   56,   54,  105,   55,   39,   57,  110,  111,
   77,  106,   58,  107,   59,   10,   10,   10,  114,  118,
   58,  115,   59,   36,  116,  119,  117,    1,  104,   20,
   37,   21,   16,   35,   14,   91,   36,    0,   92,    0,
   43,   43,   43,   43,    0,   67,    0,   44,   44,   44,
   44,    0,    0,   43,    0,   36,    0,    0,   70,   71,
   44,    0,   37,    0,    0,    0,    0,    0,    0,   40,
   40,    0,    0,    0,    0,    0,   48,   31,    0,    0,
    0,    0,   40,    0,   15,    0,   15,    7,    7,   97,
    0,   98,   99,    0,   42,   42,    0,    0,    0,   50,
   60,   61,   62,   63,   64,   65,   66,   42,    0,   60,
   61,   62,   63,   64,   65,   66,   41,    0,    0,  108,
  109,    0,   42,   43,   44,   45,   37,   38,   46,   41,
   47,    0,    0,    0,    0,    0,    0,    0,   38,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   39,    0,    0,   60,   61,   62,   63,   64,   65,
   66,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   35,    0,
   60,   61,   62,   63,   64,   65,   66,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   39,    0,    0,
   36,    0,    0,    0,    0,    0,    0,   37,    0,   60,
   61,   62,   63,   64,   65,   66,    0,   60,   61,   62,
   63,   64,   65,   66,   78,   79,   80,   81,   82,   83,
   84,   85,   86,   87,   88,   89,   90,    0,    0,    0,
    0,    0,    0,   96,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         61,
    0,   58,  265,    0,   41,   42,   43,   61,   45,   61,
   47,   41,   42,   43,   40,   45,   41,   47,   41,  264,
  265,  266,   59,   60,   61,   62,  265,   41,   41,   59,
   60,   61,   62,   41,   42,   43,   61,   45,   61,   47,
  259,  260,  261,  262,  263,   59,   59,   61,   61,    9,
   41,   59,   60,   61,   62,  266,   93,   17,   41,   42,
   43,  265,   45,   93,   47,   59,  123,   61,   59,  265,
   61,  125,   59,  125,   61,  123,   59,   60,   61,   62,
   41,   42,   43,   28,   45,   93,   47,   58,  125,   41,
   35,   43,   60,   45,   62,  125,  275,  276,   59,   60,
   61,   62,   41,   42,   43,   58,   45,   59,   47,   61,
   93,  125,  125,  267,  268,  269,   59,  125,   61,   42,
   59,   60,   61,   62,   47,  125,   41,   72,  125,   41,
   42,   43,   93,   45,  125,   47,   44,   60,   59,   62,
   91,   93,  125,  125,   41,   42,   43,   59,   45,   61,
   47,   41,   42,   43,   93,   45,   60,   47,   41,   46,
   43,   40,   45,   60,  125,   62,  261,  262,  263,   59,
   40,   61,  265,  125,   42,   43,   59,   45,   61,   47,
   62,   93,   42,   43,   40,   45,  125,   47,  106,  107,
  123,  123,   60,  123,   62,  267,  268,  269,  125,  117,
   60,  125,   62,   93,  278,  125,  123,    0,  270,   41,
   93,   41,   10,  125,    5,   67,   26,   -1,   69,   -1,
  257,  258,  259,  260,   -1,   93,   -1,  257,  258,  259,
  260,   -1,   -1,  270,   -1,  125,   -1,   -1,   43,   44,
  270,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,   -1,   -1,  270,  270,   -1,   -1,
   -1,   -1,  270,   -1,  264,   -1,  266,  264,  265,   74,
   -1,   76,   77,   -1,  257,  258,   -1,   -1,   -1,  270,
  257,  258,  259,  260,  261,  262,  263,  270,   -1,  257,
  258,  259,  260,  261,  262,  263,  265,   -1,   -1,  104,
  105,   -1,  271,  272,  273,  274,  275,  276,  277,  270,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  270,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,   -1,
  257,  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   27,   -1,   -1,
  270,   -1,   -1,   -1,   -1,   -1,   -1,  270,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,  257,  258,  259,
  260,  261,  262,  263,   54,   55,   56,   57,   58,   59,
   60,   61,   62,   63,   64,   65,   66,   -1,   -1,   -1,
   -1,   -1,   -1,   73,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
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
"\"IGUAL\"","\"DISTINTO\"","\"AND\"","\"OR\"","\"NOT\"","\"STRUCT\"",
"\"IDENT\"","\"VAR\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"RETURN\"",
"\"LITCHAR\"","\"READ\"","\"PRINT\"","\"CAST\"","\"LITENT\"","\"LITREAL\"",
"\"IF\"","\"ELSE\"","\"WHILE\"",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencias",
"programa : sentencias programa",
"sentencias : definiciones",
"sentencias : struct",
"sentencias : funciones",
"struct : \"STRUCT\" \"IDENT\" '{' definiciones '}'",
"definiciones : definicion",
"definiciones : definicion definiciones",
"definicion : \"VAR\" \"IDENT\" ':' size tipo ';'",
"size :",
"size : '[' expr_num ']' size",
"tipo : \"INT\"",
"tipo : \"FLOAT\"",
"tipo : \"CHAR\"",
"funciones : funcion",
"funciones : funcion funciones",
"funcion : \"IDENT\" '(' parametrosP ')' ':' tipo '{' expr \"RETURN\" expr '}'",
"funcion : \"IDENT\" '(' parametrosP ')' '{' expr '}'",
"parametrosP : parametros",
"parametrosP :",
"parametros : parametro",
"parametros : parametro ',' parametros",
"parametro : \"IDENT\" ':' tipo",
"expr : \"LITCHAR\"",
"expr : \"READ\" expr ';'",
"expr : \"PRINT\" expr ';'",
"expr : expr '=' expr ';'",
"expr : punto",
"expr : if",
"expr : while",
"expr : expr_num",
"expr : \"CAST\" '<' tipo '>' '(' expr ')'",
"expr_num : \"LITENT\"",
"expr_num : \"LITREAL\"",
"expr_num : expr_num '*' expr_num",
"expr_num : expr_num '/' expr_num",
"expr_num : expr_num '-' expr_num",
"expr_num : expr_num '+' expr_num",
"expr_num : expr_num '>' expr_num",
"expr_num : expr_num \"MAYOR_IGUAL\" expr_num",
"expr_num : expr_num '<' expr_num",
"expr_num : expr_num \"MENOR_IGUAL\" expr_num",
"expr_num : expr_num \"IGUAL\" expr_num",
"expr_num : expr_num \"DISTINTO\" expr_num",
"expr_num : expr_num \"AND\" expr_num",
"expr_num : expr_num \"OR\" expr_num",
"expr_num : expr_num \"NOT\" expr_num",
"punto : \"IDENT\"",
"punto : \"IDENT\" '.' punto",
"if : \"IF\" '(' expr_num ')' '{' sentencias '}'",
"if : \"IF\" '(' expr_num ')' '{' sentencias '}' \"ELSE\" '{' sentencias '}'",
"while : \"WHILE\" '(' expr ')' '{' sentencias '}'",
};

//#line 112 "sintac.y"
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
//#line 395 "Parser.java"
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
