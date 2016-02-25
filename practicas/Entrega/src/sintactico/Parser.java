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
public final static short VAR=263;
public final static short STRUCT=264;
public final static short IDENT=265;
public final static short INT=266;
public final static short FLOAT=267;
public final static short CHAR=268;
public final static short LITENT=269;
public final static short PRINT=270;
public final static short READ=271;
public final static short IF=272;
public final static short ELSE=273;
public final static short WHILE=274;
public final static short RETURN=275;
public final static short LITREAL=276;
public final static short LITCHAR=277;
public final static short CAST=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    4,    6,    6,    3,
    5,    5,    9,    9,    8,    8,   11,   11,    7,    7,
    7,    7,    7,   10,   10,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   14,   14,   15,   15,
};
final static short yylen[] = {                            2,
    1,    0,    2,    2,    1,    1,    6,    1,    2,    4,
   10,    8,    0,    3,    1,    0,    3,    5,    1,    1,
    1,    1,    4,    1,    2,    4,    3,    3,    7,   11,
    7,    5,    3,    2,    1,    1,    1,    1,    7,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    2,    3,    3,    4,    4,    1,    0,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    5,    6,    0,    4,
    0,    0,    3,    0,    0,    0,    0,   15,    0,   19,
   20,   21,   22,    0,    0,    0,    0,    0,    0,   10,
    9,    0,    0,    0,    0,    0,    7,    0,    0,    0,
    0,   23,   18,    0,    0,    0,    0,   35,    0,    0,
    0,    0,    0,   36,   37,    0,    0,    0,    0,    0,
   14,    0,    0,    0,    0,    0,    0,    0,    0,   34,
    0,    0,   12,   25,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   53,    0,    0,   57,   27,   28,    0,    0,   33,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   55,   26,   11,   56,   60,   32,    0,
    0,    0,    0,    0,    0,    0,   31,   39,    0,    0,
    0,   30,
};
final static short yydgoto[] = {                          4,
    5,    6,   25,    7,    8,   26,   24,   17,   40,   57,
   18,   58,   59,   94,   95,
};
final static short yysindex[] = {                      -246,
 -252, -245,  -18,    0,    0, -246,    0,    0,  -29,    0,
  -96, -235,    0,  -67, -252,  -27,   -7,    0, -233,    0,
    0,    0,    0,  -15, -252,  -79,  -67,  -57,  -43,    0,
    0,   -8,   15, -210,  -67,  -67,    0, -235, -252,  -19,
  -62,    0,    0, -210,   12,   12,   22,    0,   12,   12,
   23,   27,    9,    0,    0,   10,  -56,  -19,  262, -210,
    0,   31,  -31,  -37,   12,  326,  363,   12,   12,    0,
  369,  -67,    0,    0,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,  -19,
   12,    0,  390,   32,    0,    0,    0,  105,  135,    0,
   28,  455,  455,  413,  413,   53,   53,   58,   58,  -44,
  -44,  -31,  -31,  -17,  396,  402,  -53,   50,   12,   38,
  -16,  -14,   68,    0,    0,    0,    0,    0,    0,  -19,
  -19,   12,   -3,   -1,  142, -175,    0,    0,    3,  -19,
    7,    0,
};
final static short yyrindex[] = {                       133,
    0,    0,    0,    0,    0,  133,    0,    0,    0,    0,
    0,  112,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   29,    0,    0,    0,    0,    0,
    0,    0,  114,   -5,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   -5,    0,    0,  423,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   36,    0,   -5,
    0,   34,   69,    0,  115,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  115,    0,  122,    0,    0,    0,    0,    0,    0,    0,
    0,  574,  754,  735,  742,   -4,  552,  169,  517,  488,
  510,   76,   98,   41,    0,    0,    0,    0,    0,  449,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -33,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  158,    0,   25,    0,    0,  141,  -24,    0,  -28,  -25,
  130,    0,  674,   79,   52,
};
final static int YYTABLESIZE=847;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   35,   87,   33,   92,   77,   75,   29,   76,   87,   78,
   41,   42,    9,   45,   87,   61,    1,    2,    3,   11,
   46,   12,   79,   19,   80,   10,   15,   13,   14,   16,
   27,   90,   74,   28,   13,   29,   44,   44,   44,   44,
   44,   45,   44,   30,   45,   32,   88,  101,   46,   36,
   37,   46,   39,   88,   44,   44,   44,   44,   38,   88,
   60,   65,   68,   44,  117,   34,   69,   70,   73,   72,
   91,  126,  120,   88,   38,   38,   38,   38,   38,   38,
   38,   54,   54,   54,   54,   54,   54,   54,   44,  123,
  127,   29,   38,   38,   38,   38,  129,  139,   87,   54,
   54,   54,   54,   87,  133,  134,  130,  132,  131,   52,
   52,   52,   52,   52,  141,   52,   50,   50,   50,   50,
   50,  136,   50,  137,   38,  140,   38,   52,   52,   52,
   52,  142,    2,   54,   50,   50,   50,   50,   51,   51,
   51,   51,   51,   88,   51,  121,   77,   75,   88,   76,
   87,   78,   16,    8,   17,   58,   51,   51,   51,   51,
   24,   52,   59,   13,   79,   31,   80,   43,   50,  118,
  128,    0,    0,    0,    0,  122,   77,   75,    0,   76,
   87,   78,  138,   77,   75,    0,   76,   87,   78,    0,
   51,    0,    0,    0,   79,   88,   80,   20,   21,   22,
   23,   79,    0,   80,    0,    0,    0,    0,    0,   46,
   46,   46,   46,   46,    0,   46,   85,   86,    0,   81,
   82,   83,   84,   85,   86,   88,    0,   46,   46,   46,
   46,   29,   88,    0,    0,   29,   29,   29,   29,    0,
   29,   29,   29,   29,   29,   47,    0,    0,    0,   48,
   49,   50,   51,    0,   52,   53,   54,   55,   56,   13,
    0,   46,    0,   13,   13,   13,   13,    0,   13,   13,
   13,   13,   13,   62,    0,    0,   62,   48,    0,    0,
   48,    0,    0,    0,   54,   55,   56,   54,   55,   56,
   38,   38,   38,   38,   38,   38,    0,   54,   54,   54,
   54,   54,   54,   77,   75,    0,   76,   87,   78,   81,
   82,   83,   84,   85,   86,    0,   83,   84,   85,   86,
    0,   79,   89,   80,    0,   52,   52,   52,   52,   52,
   52,    0,   50,   50,   50,   50,   50,   50,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   88,    0,   51,   51,   51,   51,   51,   51,
    0,   81,   82,   83,   84,   85,   86,   77,   75,    0,
   76,   87,   78,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   96,   79,    0,   80,    0,    0,
    0,   81,   82,   83,   84,   85,   86,    0,   81,   82,
   83,   84,   85,   86,   77,   75,    0,   76,   87,   78,
   77,   75,    0,   76,   87,   78,   88,    0,    0,    0,
    0,   97,   79,    0,   80,   46,   46,  100,   79,    0,
   80,   77,   75,  119,   76,   87,   78,   77,   75,    0,
   76,   87,   78,   77,   75,    0,   76,   87,   78,   79,
    0,   80,    0,   88,    0,   79,    0,   80,   87,   88,
  125,   79,    0,   80,   38,   38,    0,   38,   38,   38,
    0,    0,   79,    0,   80,    0,    0,    0,    0,    0,
   88,    0,   38,   38,   38,    0,   88,    0,  124,    0,
   56,   56,   88,   56,   56,   56,   77,    0,    0,    0,
   87,   78,    0,   88,    0,    0,    0,    0,   56,   56,
   56,    0,    0,   38,   79,    0,   80,    0,   81,   82,
   83,   84,   85,   86,    0,    0,    0,    0,   48,   48,
   48,   48,   48,    0,   48,    0,    0,    0,    0,   56,
    0,    0,    0,    0,    0,   88,   48,   48,   48,   48,
   49,   49,   49,   49,   49,    0,   49,   47,   47,   47,
   47,   47,    0,   47,    0,    0,    0,    0,   49,   49,
   49,   49,    0,    0,    0,   47,   47,   47,   47,    0,
   48,    0,   81,   82,   83,   84,   85,   86,    0,    0,
    0,    0,   45,   45,   45,   45,   45,    0,   45,    0,
    0,    0,   49,    0,    0,    0,    0,    0,    0,   47,
   45,   45,   45,   45,   42,    0,   42,   42,   42,   81,
   82,   83,   84,   85,   86,   81,   82,   83,   84,   85,
   86,    0,   42,    0,   42,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   45,    0,   81,   82,   83,   84,
   85,   86,   81,   82,   83,   84,   85,   86,   81,   82,
   83,   84,   85,   86,    0,    0,   42,    0,    0,   81,
   82,   83,   84,   85,   86,    0,    0,    0,    0,   38,
   38,   38,   38,   38,   38,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   56,   56,   56,   56,   56,
   56,   81,   82,   83,   84,   85,   86,    0,   63,   64,
    0,    0,   66,   67,    0,    0,   71,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   93,    0,
    0,   98,   99,    0,   48,   48,   48,   48,  102,  103,
  104,  105,  106,  107,  108,  109,  110,  111,  112,  113,
  114,  115,  116,    0,   93,    0,   49,   49,   49,   49,
    0,    0,    0,   47,   47,   40,   40,   40,   40,   40,
    0,   40,   41,   41,   41,   41,   41,    0,   41,    0,
    0,    0,   93,   40,   43,   40,   43,   43,   43,    0,
   41,    0,   41,    0,    0,  135,    0,    0,    0,    0,
    0,    0,   43,    0,   43,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   40,    0,    0,
    0,    0,    0,    0,   41,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   58,   46,   27,   41,   42,   43,   40,   45,   46,   47,
   35,   36,  265,   33,   46,   44,  263,  264,  265,  265,
   40,   40,   60,   91,   62,    1,  123,   33,   58,  265,
   58,   60,   58,   41,   40,  269,   41,   42,   43,   44,
   45,   33,   47,   59,   33,  125,   91,   72,   40,   93,
   59,   40,  263,   91,   59,   60,   61,   62,   44,   91,
  123,   40,   40,   39,   90,  123,   40,   59,  125,   60,
   40,  125,   41,   91,   41,   42,   43,   44,   45,   46,
   47,   41,   42,   43,   44,   45,   46,   47,   93,   62,
   41,  125,   59,   60,   61,   62,   59,  273,   46,   59,
   60,   61,   62,   46,  130,  131,  123,   40,  123,   41,
   42,   43,   44,   45,  140,   47,   41,   42,   43,   44,
   45,  125,   47,  125,   91,  123,   93,   59,   60,   61,
   62,  125,    0,   93,   59,   60,   61,   62,   41,   42,
   43,   44,   45,   91,   47,   41,   42,   43,   91,   45,
   46,   47,   41,  125,   41,   41,   59,   60,   61,   62,
  125,   93,   41,    6,   60,   25,   62,   38,   93,   91,
  119,   -1,   -1,   -1,   -1,   41,   42,   43,   -1,   45,
   46,   47,   41,   42,   43,   -1,   45,   46,   47,   -1,
   93,   -1,   -1,   -1,   60,   91,   62,  265,  266,  267,
  268,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   41,
   42,   43,   44,   45,   -1,   47,  261,  262,   -1,  257,
  258,  259,  260,  261,  262,   91,   -1,   59,   60,   61,
   62,  265,   91,   -1,   -1,  269,  270,  271,  272,   -1,
  274,  275,  276,  277,  278,  265,   -1,   -1,   -1,  269,
  270,  271,  272,   -1,  274,  275,  276,  277,  278,  265,
   -1,   93,   -1,  269,  270,  271,  272,   -1,  274,  275,
  276,  277,  278,  265,   -1,   -1,  265,  269,   -1,   -1,
  269,   -1,   -1,   -1,  276,  277,  278,  276,  277,  278,
  257,  258,  259,  260,  261,  262,   -1,  257,  258,  259,
  260,  261,  262,   42,   43,   -1,   45,   46,   47,  257,
  258,  259,  260,  261,  262,   -1,  259,  260,  261,  262,
   -1,   60,   61,   62,   -1,  257,  258,  259,  260,  261,
  262,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   -1,  257,  258,  259,  260,  261,  262,
   -1,  257,  258,  259,  260,  261,  262,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,  257,  258,
  259,  260,  261,  262,   42,   43,   -1,   45,   46,   47,
   42,   43,   -1,   45,   46,   47,   91,   -1,   -1,   -1,
   -1,   59,   60,   -1,   62,  257,  258,   59,   60,   -1,
   62,   42,   43,   44,   45,   46,   47,   42,   43,   -1,
   45,   46,   47,   42,   43,   -1,   45,   46,   47,   60,
   -1,   62,   -1,   91,   -1,   60,   -1,   62,   46,   91,
   59,   60,   -1,   62,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   60,   61,   62,   -1,   91,   -1,   93,   -1,
   42,   43,   91,   45,   46,   47,   42,   -1,   -1,   -1,
   46,   47,   -1,   91,   -1,   -1,   -1,   -1,   60,   61,
   62,   -1,   -1,   91,   60,   -1,   62,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   41,   42,
   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   91,
   -1,   -1,   -1,   -1,   -1,   91,   59,   60,   61,   62,
   41,   42,   43,   44,   45,   -1,   47,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   59,   60,   61,   62,   -1,
   93,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   59,   60,   61,   62,   41,   -1,   43,   44,   45,  257,
  258,  259,  260,  261,  262,  257,  258,  259,  260,  261,
  262,   -1,   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,  257,  258,  259,  260,
  261,  262,  257,  258,  259,  260,  261,  262,  257,  258,
  259,  260,  261,  262,   -1,   -1,   93,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  257,  258,  259,  260,  261,  262,   -1,   45,   46,
   -1,   -1,   49,   50,   -1,   -1,   53,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   65,   -1,
   -1,   68,   69,   -1,  257,  258,  259,  260,   75,   76,
   77,   78,   79,   80,   81,   82,   83,   84,   85,   86,
   87,   88,   89,   -1,   91,   -1,  257,  258,  259,  260,
   -1,   -1,   -1,  257,  258,   41,   42,   43,   44,   45,
   -1,   47,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,  119,   59,   41,   61,   43,   44,   45,   -1,
   59,   -1,   61,   -1,   -1,  132,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=278;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"\"MAYOR_IGUAL\"",
"\"MENOR_IGUAL\"","\"IGUAL\"","\"DISTINTO\"","\"AND\"","\"OR\"","\"VAR\"",
"\"STRUCT\"","\"IDENT\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"LITENT\"",
"\"PRINT\"","\"READ\"","\"IF\"","\"ELSE\"","\"WHILE\"","\"RETURN\"",
"\"LITREAL\"","\"LITCHAR\"","\"CAST\"",
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
"funcion : \"IDENT\" '(' parametros_ ')' ':' tipo '{' definiciones_funcion sentencias_locales '}'",
"funcion : \"IDENT\" '(' parametros_ ')' '{' definiciones_funcion sentencias_locales '}'",
"definiciones_funcion :",
"definiciones_funcion : \"VAR\" definicion definiciones_funcion",
"parametros_ : parametros",
"parametros_ :",
"parametros : \"IDENT\" ':' tipo",
"parametros : \"IDENT\" ':' tipo ',' parametros",
"tipo : \"IDENT\"",
"tipo : \"INT\"",
"tipo : \"FLOAT\"",
"tipo : \"CHAR\"",
"tipo : '[' \"LITENT\" ']' tipo",
"sentencias_locales : sentencia_local",
"sentencias_locales : sentencia_local sentencias_locales",
"sentencia_local : expr '=' expr ';'",
"sentencia_local : \"PRINT\" expr ';'",
"sentencia_local : \"READ\" expr ';'",
"sentencia_local : \"IF\" '(' expr ')' '{' sentencias_locales '}'",
"sentencia_local : \"IF\" '(' expr ')' '{' sentencias_locales '}' \"ELSE\" '{' sentencias_locales '}'",
"sentencia_local : \"WHILE\" '(' expr ')' '{' sentencias_locales '}'",
"sentencia_local : \"IDENT\" '(' paso_parametros_ ')' ';'",
"sentencia_local : \"RETURN\" expr ';'",
"sentencia_local : \"RETURN\" ';'",
"expr : \"LITENT\"",
"expr : \"LITREAL\"",
"expr : \"LITCHAR\"",
"expr : \"IDENT\"",
"expr : \"CAST\" '<' tipo '>' '(' expr ')'",
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
"expr : '!' expr",
"expr : '(' expr ')'",
"expr : expr '.' expr",
"expr : expr '[' expr ']'",
"expr : \"IDENT\" '(' paso_parametros_ ')'",
"paso_parametros_ : paso_parametros",
"paso_parametros_ :",
"paso_parametros : expr",
"paso_parametros : expr ',' paso_parametros",
};

//#line 120 "sintac.y"
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
//#line 490 "Parser.java"
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
