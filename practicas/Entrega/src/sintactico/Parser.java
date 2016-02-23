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
public final static short PRINT=271;
public final static short READ=272;
public final static short LITCHAR=273;
public final static short PUNTO=274;
public final static short CAST=275;
public final static short LITENT=276;
public final static short LITREAL=277;
public final static short IF=278;
public final static short ELSE=279;
public final static short WHILE=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    3,    5,    5,    6,    2,
    2,    9,    7,    7,    8,    8,   11,   11,   11,    4,
    4,   12,   12,   13,   13,   15,   15,   16,    1,    1,
   17,   17,   17,   17,   17,   14,   14,   14,   14,   14,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   19,   19,   18,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    6,    1,    2,    5,    0,
    2,    6,    0,    4,    1,    1,    1,    1,    1,    1,
    2,   13,    8,    1,    0,    1,    3,    3,    1,    2,
    3,    3,    4,    1,    1,    1,    4,    3,    1,    7,
    1,    1,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    7,   11,    7,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,
    4,    5,    0,    0,    0,   34,   35,    0,    0,    0,
    0,   43,    0,   36,    0,   41,   42,    0,    0,    0,
    0,    0,    2,   11,    0,   21,   30,    0,    0,    0,
   24,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   31,
    0,    0,   32,    0,    0,    0,    0,    0,    0,    0,
    0,   33,    0,    0,   57,   17,   18,   19,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   54,
   55,   56,    0,    0,    0,    0,    0,    0,    8,   28,
    0,    0,   27,    0,   16,    0,   15,    0,   37,    0,
    0,    0,    6,    0,    0,    0,   12,    0,    0,    0,
    0,    0,    0,   14,    0,    0,   60,    9,   23,    0,
   40,    0,    0,    0,    0,    0,    0,   59,    0,   22,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   12,   67,   68,   74,  106,   13,   28,
  107,   14,   40,   29,   41,   42,   15,   16,   17,
};
final static short yysindex[] = {                        44,
 -248,  -37, -242,  -40,  -40,  -14,  -11,    0,   44,    0,
    0,    0, -234, -229,   44,    0,    0,  -81, -220,  -40,
   -8,    0,  -38,    0,   -6,    0,    0,  251,  -51,  -34,
  -38,  -40,    0,    0,   23,    0,    0, -196,   12,   31,
    0,   49,  -24,    5,  123, -247,  -38,  -38,  -38,  -38,
  -38,  -38,  -38,  -38,  -38,  -38,  -38,  -38,  -38,    0,
  -38,  -40,    0,  130,  -31,   25,  -27, -196, -247,  -52,
 -220,    0,  -38, -187,    0,    0,    0,    0,   33,  156,
  156,   71,   71,  186,  186,  147,  147,  -49,  -49,    0,
    0,    0,  139,  -86,  -23,  -16,    5,   53,    0,    0,
 -234, -247,    0,  164,    0,   57,    0,   74,    0,   44,
   44, -187,    0,   44,   -5,    5,    0,  -40,   17,   27,
   61,   28, -234,    0,  -30, -131,    0,    0,    0,   44,
    0,   35, -120,   44,  -40,   66,  -15,    0,   69,    0,
};
final static short yyrindex[] = {                       160,
    0,    0,    0,    0,    0,    0,    0,    0,  174,    0,
    0,    0,    4,   19,    7,    0,    0,    0,  152,    0,
    0,    0,    0,    0,    0,    0,    0,  -32,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  155,    0,  -89,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   77,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   63,
   76,   96,  104,   32,   68,    6,   43,  -29,   -4,    0,
    0,    0,    0,  -13,    0,    0,  -89,    0,    0,    0,
   75,    0,    0,    0,    0,    0,    0,    0,    0,   79,
   79,    0,    0,   79,    0,  -89,    0,    0,    0,    0,
    0,    0,   87,    0,    0,    1,    0,    0,    0,  -65,
    0,    0,    0,   79,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                       201,
  193,   39,    0,  203,  151,    0,  -82,  103,    0,  381,
   55,    0,    0,  343,  149,    0,    0,    0,    0,
};
final static int YYTABLESIZE=514;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         23,
   58,   23,   19,   10,   61,  102,   10,   60,   39,   96,
  131,   52,   52,   52,  112,   52,   18,   52,   20,   76,
   77,   78,   21,   20,   63,   31,   39,   38,   32,   52,
   52,    3,   52,  124,   72,   35,   53,   53,   53,   61,
   53,   38,   53,  139,   39,   38,   49,   49,   49,   44,
   49,   34,   49,   46,   53,   53,   61,   53,   39,   61,
   61,   52,   19,   52,   49,   49,   61,   49,   66,   69,
  101,   70,   50,   50,   50,   61,   50,  105,   50,   76,
   77,   78,   97,   51,   51,   51,   53,   51,   53,   51,
   50,   50,   71,   50,  108,   73,   49,   98,   49,  110,
   79,   51,   51,   47,   51,   47,  111,   47,   48,   48,
   48,  113,   48,  118,   48,  117,   46,  123,   46,  128,
   46,   47,   50,  100,   50,   58,   48,   48,   10,   48,
   51,   10,   52,   51,   46,   51,   44,   44,   44,  114,
   44,  126,   44,   20,   45,   45,   45,  132,   45,  135,
   45,  127,  129,   47,   44,   47,  115,  134,   48,   10,
   48,  130,   45,   75,   49,   47,   46,   48,   46,   50,
   95,   49,   47,    1,   48,   13,   50,   13,   13,   13,
   49,   47,   51,   48,   52,   50,   44,   62,   44,   51,
  138,   52,   25,  140,   45,   26,   45,   49,   51,   10,
   52,    7,   50,   10,   10,   49,   47,   37,   48,   33,
   50,   57,   58,   59,  121,   51,   36,   52,   99,  103,
    0,    0,   62,   51,   22,   52,   22,   52,   52,   52,
   52,  109,   24,    0,   25,   26,   27,   26,   27,   62,
    0,   39,   62,   62,   52,    0,    0,    0,    0,   62,
    0,    0,   53,   53,   53,   53,  116,    0,   62,    0,
    0,    0,   49,   49,   58,   58,   58,   10,   10,   53,
   58,   58,   58,   10,   10,   10,   10,    0,   58,   49,
   58,   10,   20,   10,   20,    0,    0,    0,   20,   20,
   20,    0,   49,   47,    0,   48,   20,   50,   20,   51,
   51,    0,  119,  120,    0,   50,  122,    1,    2,    3,
   51,    0,   52,    0,    4,    5,   51,    0,    0,    0,
    0,    6,  133,    7,    0,    0,  136,   53,   54,   55,
   56,   57,   58,   59,    0,    0,   47,    0,   10,   10,
    0,   48,    0,    0,    0,   10,   10,   30,    0,   46,
   10,   10,   10,    0,   10,    0,   10,   10,   10,    0,
    0,    0,   43,    0,   10,    0,   10,    0,    0,   44,
    0,    0,    0,    0,   65,    0,    0,   45,    0,   53,
   54,   55,   56,   57,   58,   59,   53,   54,   55,   56,
   57,   58,   59,    0,    0,   53,   54,   55,   56,   57,
   58,   59,    0,   45,   94,   55,   56,   57,   58,   59,
    0,   64,   53,   54,   55,   56,   57,   58,   59,    0,
   53,   54,   55,   56,   57,   58,   59,   80,   81,   82,
   83,   84,   85,   86,   87,   88,   89,   90,   91,   92,
    0,   93,   53,   54,   55,   56,   57,   58,   59,    0,
    0,    0,    0,  104,    0,    0,    0,    0,    0,    0,
  125,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  137,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   54,   55,
   56,   57,   58,   59,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   40,   40,    0,   91,   58,    0,   59,   41,   41,
   41,   41,   42,   43,   97,   45,  265,   47,    0,  267,
  268,  269,  265,   61,   59,   40,   59,   41,   40,   59,
   60,  266,   62,  116,   59,  265,   41,   42,   43,   91,
   45,  123,   47,   59,  265,   59,   41,   42,   43,   58,
   45,   13,   47,   60,   59,   60,   91,   62,   91,   91,
   91,   91,   40,   93,   59,   60,   91,   62,  265,   58,
  123,   41,   41,   42,   43,   91,   45,  265,   47,  267,
  268,  269,   58,   41,   42,   43,   91,   45,   93,   47,
   59,   60,   44,   62,   62,   91,   91,  125,   93,  123,
   46,   59,   60,   41,   62,   43,  123,   45,   41,   42,
   43,   59,   45,   40,   47,   59,   41,  123,   43,   59,
   45,   59,   91,   69,   93,  125,   59,   60,  125,   62,
   60,  125,   62,   91,   59,   93,   41,   42,   43,  101,
   45,  125,   47,  125,   41,   42,   43,  279,   45,  270,
   47,  125,  125,   91,   59,   93,  102,  123,   91,    0,
   93,  123,   59,   41,   42,   43,   91,   45,   93,   47,
   41,   42,   43,    0,   45,  265,   47,  267,  268,  269,
   42,   43,   60,   45,   62,   47,   91,  274,   93,   60,
  125,   62,   41,  125,   91,   41,   93,   42,   60,  125,
   62,  125,   47,  125,  270,   42,   43,   15,   45,    9,
   47,  261,  262,  263,  112,   60,   14,   62,   68,   71,
   -1,   -1,  274,   60,  265,   62,  265,  257,  258,  259,
  260,   93,  273,   -1,  275,  276,  277,  276,  277,  274,
   -1,  274,  274,  274,  274,   -1,   -1,   -1,   -1,  274,
   -1,   -1,  257,  258,  259,  260,   93,   -1,  274,   -1,
   -1,   -1,  257,  258,  264,  265,  266,  264,  265,  274,
  270,  271,  272,  270,  271,  272,  270,   -1,  278,  274,
  280,  278,  264,  280,  266,   -1,   -1,   -1,  270,  271,
  272,   -1,   42,   43,   -1,   45,  278,   47,  280,  257,
  258,   -1,  110,  111,   -1,  274,  114,  264,  265,  266,
   60,   -1,   62,   -1,  271,  272,  274,   -1,   -1,   -1,
   -1,  278,  130,  280,   -1,   -1,  134,  257,  258,  259,
  260,  261,  262,  263,   -1,   -1,  274,   -1,  264,  265,
   -1,  274,   -1,   -1,   -1,  271,  272,    5,   -1,  274,
  264,  265,  278,   -1,  280,   -1,  270,  271,  272,   -1,
   -1,   -1,   20,   -1,  278,   -1,  280,   -1,   -1,  274,
   -1,   -1,   -1,   -1,   32,   -1,   -1,  274,   -1,  257,
  258,  259,  260,  261,  262,  263,  257,  258,  259,  260,
  261,  262,  263,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,   -1,   23,   62,  259,  260,  261,  262,  263,
   -1,   31,  257,  258,  259,  260,  261,  262,  263,   -1,
  257,  258,  259,  260,  261,  262,  263,   47,   48,   49,
   50,   51,   52,   53,   54,   55,   56,   57,   58,   59,
   -1,   61,  257,  258,  259,  260,  261,  262,  263,   -1,
   -1,   -1,   -1,   73,   -1,   -1,   -1,   -1,   -1,   -1,
  118,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  135,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,
};
}
final static short YYFINAL=8;
final static short YYMAXTOKEN=280;
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
"\"IGUAL\"","\"DISTINTO\"","\"AND\"","\"OR\"","\"NOT\"","\"STRUCT\"",
"\"IDENT\"","\"VAR\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"RETURN\"","\"PRINT\"",
"\"READ\"","\"LITCHAR\"","\"PUNTO\"","\"CAST\"","\"LITENT\"","\"LITREAL\"",
"\"IF\"","\"ELSE\"","\"WHILE\"",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencias",
"programa : sentencias programa",
"sentencias : definiciones",
"sentencias : struct",
"sentencias : funciones",
"struct : \"STRUCT\" \"IDENT\" '{' definiciones_struct '}' ';'",
"definiciones_struct : definicion_struct",
"definiciones_struct : definicion_struct definiciones_struct",
"definicion_struct : \"IDENT\" ':' size tipo ';'",
"definiciones :",
"definiciones : definicion definiciones",
"definicion : \"VAR\" \"IDENT\" ':' size tipo ';'",
"size :",
"size : '[' expr_num ']' size",
"tipo : tipo_basico",
"tipo : \"IDENT\"",
"tipo_basico : \"INT\"",
"tipo_basico : \"FLOAT\"",
"tipo_basico : \"CHAR\"",
"funciones : funcion",
"funciones : funcion funciones",
"funcion : \"IDENT\" '(' parametrosP ')' ':' tipo_basico '{' definiciones sentencias \"RETURN\" expr ';' '}'",
"funcion : \"IDENT\" '(' parametrosP ')' '{' definiciones sentencias '}'",
"parametrosP : parametros",
"parametrosP :",
"parametros : parametro",
"parametros : parametro ',' parametros",
"parametro : \"IDENT\" ':' tipo_basico",
"sentencias : sentencia",
"sentencias : sentencia sentencias",
"sentencia : \"PRINT\" expr ';'",
"sentencia : \"READ\" expr ';'",
"sentencia : \"IDENT\" '=' expr ';'",
"sentencia : while",
"sentencia : if",
"expr : \"LITCHAR\"",
"expr : expr '[' expr_num ']'",
"expr : expr \"PUNTO\" expr",
"expr : expr_num",
"expr : \"CAST\" '<' tipo_basico '>' '(' expr ')'",
"expr_num : \"LITENT\"",
"expr_num : \"LITREAL\"",
"expr_num : \"IDENT\"",
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
"expr_num : '(' expr_num ')'",
"if : \"IF\" '(' expr_num ')' '{' sentencias '}'",
"if : \"IF\" '(' expr_num ')' '{' sentencias '}' \"ELSE\" '{' sentencias '}'",
"while : \"WHILE\" '(' expr ')' '{' sentencias '}'",
};

//#line 131 "sintac.y"
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
//#line 428 "Parser.java"
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
