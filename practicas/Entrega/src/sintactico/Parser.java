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
public final static short INT=267;
public final static short FLOAT=268;
public final static short CHAR=269;
public final static short LITENT=270;
public final static short PRINT=271;
public final static short READ=272;
public final static short IF=273;
public final static short ELSE=274;
public final static short WHILE=275;
public final static short RETURN=276;
public final static short LITREAL=277;
public final static short LITCHAR=278;
public final static short CAST=279;
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
    3,    3,    3,    3,    4,    4,    1,    0,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    5,    6,    0,    4,
    0,    0,    3,    0,    0,    0,    0,   15,    0,   19,
   20,   21,   22,    0,    0,    0,    0,    0,    0,   10,
    9,    0,    0,    0,    0,    0,    7,    0,    0,    0,
    0,   23,   18,    0,    0,    0,   35,    0,    0,    0,
    0,    0,   36,   37,    0,    0,    0,    0,    0,   14,
    0,    0,    0,    0,    0,    0,    0,   34,    0,    0,
   12,   25,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   53,    0,    0,   57,   27,   28,    0,    0,   33,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   55,   26,   11,   56,   60,   32,    0,
    0,    0,    0,    0,    0,    0,   31,   39,    0,    0,
    0,   30,
};
final static short yydgoto[] = {                          4,
    5,    6,   25,    7,    8,   26,   24,   17,   40,   56,
   18,   57,   58,   93,   94,
};
final static short yysindex[] = {                      -248,
 -255, -253,  -21,    0,    0, -248,    0,    0,  -32,    0,
  -96, -237,    0,  -66, -255,  -28,   -5,    0, -231,    0,
    0,    0,    0,  -19, -255,  -84,  -66,  -57,  -50,    0,
    0,  -15,    4, -214,  -66,  -66,    0, -237, -255,  -26,
  -67,    0,    0, -214,    5,   18,    0,    5,    5,   19,
   20,    2,    0,    0,    3,  -63,  -26,  151, -214,    0,
   24,   89,    5,  356,  377,    5,    5,    0,  384,  -66,
    0,    0,    5,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,  -26,    5,
    0,  407,   26,    0,    0,    0,   96,  119,    0,    6,
  474,  474,  344,  344,  108,  108,  101,  101,  -34,  -34,
  -36,  -36,  -36,   -7,  414,  435,  -60,   45,    5,   32,
  -31,  -30,   63,    0,    0,    0,    0,    0,    0,  -26,
  -26,    5,  -13,  -11,  126, -164,    0,    0,   -8,  -26,
   -3,    0,
};
final static short yyrindex[] = {                       113,
    0,    0,    0,    0,    0,  113,    0,    0,    0,    0,
    0,   79,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    8,    0,    0,    0,    0,    0,
    0,    0,   86,  -12,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -12,    0,  442,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   15,    0,  -12,    0,
  -39,    0,   88,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   88,
    0,  103,    0,    0,    0,    0,    0,    0,    0,    0,
  494,  567,  -10,  808,  775,  786,  529,  536,  500,  507,
   35,   57,   64,   28,    0,    0,    0,    0,    0,  467,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -40,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  139,    0,   14,    0,    0,  121,   11,    0,  -35,  621,
  110,    0,  725,   62,   34,
};
final static int YYTABLESIZE=901;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   35,   38,   38,   38,   38,   38,   38,   38,   60,   86,
    9,   86,   11,   45,   10,    1,    2,    3,   12,   38,
   38,   38,   38,   89,   19,   14,   15,   13,   16,   27,
   40,   40,   40,   40,   40,   28,   40,   33,   29,   30,
   32,   45,   36,   37,   45,   41,   42,   38,   40,   39,
   40,   38,   44,   38,   87,   59,   87,   63,   66,   67,
   68,   71,   70,   90,  126,   34,  120,  123,   54,   54,
   54,   54,   54,   54,   54,   50,   50,   50,   50,   50,
  100,   50,   40,   87,   29,  127,   54,   54,   54,   54,
  129,  130,  131,   50,   50,   50,   50,   51,   51,   51,
   51,   51,  132,   51,   52,   52,   52,   52,   52,  139,
   52,  136,    2,  137,  140,   51,   51,   51,   51,   16,
   54,  142,   52,   52,   52,   52,   17,   50,   58,   91,
   75,   73,    8,   74,   86,   76,  121,   75,   73,   24,
   74,   86,   76,   59,   13,   31,   86,   43,   77,   51,
   78,  118,  128,   86,    0,   77,   52,   78,    0,  122,
   75,   73,    0,   74,   86,   76,  138,   75,   73,    0,
   74,   86,   76,    0,    0,    0,    0,    0,   77,   87,
   78,    0,    0,    0,    0,   77,   87,   78,    0,    0,
    0,   87,   75,   73,    0,   74,   86,   76,   87,   20,
   21,   22,   23,    0,    0,    0,    0,    0,    0,   87,
   77,   88,   78,    0,    0,    0,   87,   38,   38,   38,
   38,   38,   38,   38,    0,   29,   83,   84,   85,   29,
   29,   29,   29,    0,   29,   29,   29,   29,   29,   46,
    0,   87,    0,   47,   48,   49,   50,    0,   51,   52,
   53,   54,   55,   13,    0,    0,    0,   13,   13,   13,
   13,    0,   13,   13,   13,   13,   13,   61,    0,    0,
   61,   47,    0,    0,   47,    0,    0,    0,   53,   54,
   55,   53,   54,   55,   54,   54,   54,   54,   54,   54,
   54,   50,   50,   50,   50,   50,   50,   50,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   51,   51,   51,   51,   51,   51,   51,
   52,   52,   52,   52,   52,   52,   52,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   79,   80,   81,   82,   83,
   84,   85,   79,   80,   81,   82,   83,   84,   85,   81,
   82,   83,   84,   85,   79,   80,   81,   82,   83,   84,
   85,    0,    0,    0,    0,   79,   80,   81,   82,   83,
   84,   85,   79,   80,   81,   82,   83,   84,   85,   86,
    0,    0,    0,    0,    0,    0,    0,   75,   73,    0,
   74,   86,   76,   77,    0,   78,    0,   79,   80,   81,
   82,   83,   84,   85,   95,   77,    0,   78,   75,   73,
    0,   74,   86,   76,    0,   75,   73,    0,   74,   86,
   76,    0,    0,    0,   87,   96,   77,    0,   78,    0,
    0,    0,   99,   77,    0,   78,   87,    0,   75,   73,
  119,   74,   86,   76,    0,   75,   73,    0,   74,   86,
   76,    0,    0,    0,    0,    0,   77,   87,   78,    0,
    0,    0,    0,   77,   87,   78,   75,   73,    0,   74,
   86,   76,    0,   38,   38,    0,   38,   38,   38,    0,
    0,    0,    0,  125,   77,    0,   78,   87,    0,    0,
    0,   38,   38,   38,   87,    0,  124,    0,   56,   56,
    0,   56,   56,   56,    0,   75,    0,    0,    0,   86,
   76,    0,    0,    0,    0,   87,   56,   56,   56,    0,
    0,    0,   38,   77,   42,   78,   42,   42,   42,    0,
   48,   48,   48,   48,   48,    0,   48,   49,   49,   49,
   49,   49,   42,   49,   42,    0,    0,   56,   48,   48,
   48,   48,    0,    0,   87,   49,   49,   49,   49,   46,
   46,   46,   46,   46,    0,   46,   47,   47,   47,   47,
   47,    0,   47,    0,    0,    0,   42,   46,   46,   46,
   46,    0,   48,    0,   47,   47,   47,   47,    0,   49,
   79,   80,   81,   82,   83,   84,   85,   43,    0,   43,
   43,   43,   79,   80,   81,   82,   83,   84,   85,    0,
    0,   46,    0,    0,    0,   43,    0,   43,   47,    0,
    0,    0,    0,   79,   80,   81,   82,   83,   84,   85,
   79,   80,   81,   82,   83,   84,   85,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   43,
    0,    0,    0,   79,   80,   81,   82,   83,   84,   85,
   79,   80,   81,   82,   83,   84,   85,   72,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   79,   80,   81,   82,   83,   84,   85,   38,   38,
   38,   38,   38,   38,   38,    0,    0,    0,    0,  117,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   56,   56,   56,   56,   56,   56,   56,
   79,   80,   81,   82,   83,   84,   85,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  133,  134,    0,    0,    0,    0,   48,   48,   48,   48,
  141,    0,    0,   49,   49,   49,   49,    0,    0,   62,
    0,    0,   64,   65,    0,    0,   69,    0,    0,    0,
    0,    0,    0,    0,    0,   46,   46,   92,    0,    0,
   97,   98,   47,   47,    0,    0,    0,  101,  102,  103,
  104,  105,  106,  107,  108,  109,  110,  111,  112,  113,
  114,  115,  116,    0,   92,   44,   44,   44,   44,   44,
    0,   44,    0,    0,    0,    0,   45,   45,   45,   45,
   45,    0,   45,   44,   44,   44,   44,    0,    0,    0,
    0,    0,    0,   92,   45,   45,   45,   45,   41,   41,
   41,   41,   41,    0,   41,    0,  135,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   41,   44,   41,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   41,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   58,   41,   42,   43,   44,   45,   46,   47,   44,   46,
  266,   46,  266,   40,    1,  264,  265,  266,   40,   59,
   60,   61,   62,   59,   91,   58,  123,   40,  266,   58,
   41,   42,   43,   44,   45,   41,   47,   27,  270,   59,
  125,   40,   93,   59,   40,   35,   36,   44,   59,  264,
   61,   91,   39,   93,   91,  123,   91,   40,   40,   40,
   59,  125,   60,   40,  125,  123,   41,   62,   41,   42,
   43,   44,   45,   46,   47,   41,   42,   43,   44,   45,
   70,   47,   93,   91,  125,   41,   59,   60,   61,   62,
   59,  123,  123,   59,   60,   61,   62,   41,   42,   43,
   44,   45,   40,   47,   41,   42,   43,   44,   45,  274,
   47,  125,    0,  125,  123,   59,   60,   61,   62,   41,
   93,  125,   59,   60,   61,   62,   41,   93,   41,   41,
   42,   43,  125,   45,   46,   47,   41,   42,   43,  125,
   45,   46,   47,   41,    6,   25,   46,   38,   60,   93,
   62,   90,  119,   46,   -1,   60,   93,   62,   -1,   41,
   42,   43,   -1,   45,   46,   47,   41,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   60,   91,
   62,   -1,   -1,   -1,   -1,   60,   91,   62,   -1,   -1,
   -1,   91,   42,   43,   -1,   45,   46,   47,   91,  266,
  267,  268,  269,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   60,   61,   62,   -1,   -1,   -1,   91,  257,  258,  259,
  260,  261,  262,  263,   -1,  266,  261,  262,  263,  270,
  271,  272,  273,   -1,  275,  276,  277,  278,  279,  266,
   -1,   91,   -1,  270,  271,  272,  273,   -1,  275,  276,
  277,  278,  279,  266,   -1,   -1,   -1,  270,  271,  272,
  273,   -1,  275,  276,  277,  278,  279,  266,   -1,   -1,
  266,  270,   -1,   -1,  270,   -1,   -1,   -1,  277,  278,
  279,  277,  278,  279,  257,  258,  259,  260,  261,  262,
  263,  257,  258,  259,  260,  261,  262,  263,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
  257,  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,  257,  258,  259,  260,  261,  262,  263,  259,
  260,  261,  262,  263,  257,  258,  259,  260,  261,  262,
  263,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,  257,  258,  259,  260,  261,  262,  263,   46,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   60,   -1,   62,   -1,  257,  258,  259,
  260,  261,  262,  263,   59,   60,   -1,   62,   42,   43,
   -1,   45,   46,   47,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   91,   59,   60,   -1,   62,   -1,
   -1,   -1,   59,   60,   -1,   62,   91,   -1,   42,   43,
   44,   45,   46,   47,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   60,   91,   62,   -1,
   -1,   -1,   -1,   60,   91,   62,   42,   43,   -1,   45,
   46,   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   91,   -1,   -1,
   -1,   60,   61,   62,   91,   -1,   93,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   42,   -1,   -1,   -1,   46,
   47,   -1,   -1,   -1,   -1,   91,   60,   61,   62,   -1,
   -1,   -1,   91,   60,   41,   62,   43,   44,   45,   -1,
   41,   42,   43,   44,   45,   -1,   47,   41,   42,   43,
   44,   45,   59,   47,   61,   -1,   -1,   91,   59,   60,
   61,   62,   -1,   -1,   91,   59,   60,   61,   62,   41,
   42,   43,   44,   45,   -1,   47,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   93,   59,   60,   61,
   62,   -1,   93,   -1,   59,   60,   61,   62,   -1,   93,
  257,  258,  259,  260,  261,  262,  263,   41,   -1,   43,
   44,   45,  257,  258,  259,  260,  261,  262,  263,   -1,
   -1,   93,   -1,   -1,   -1,   59,   -1,   61,   93,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
  257,  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
  257,  258,  259,  260,  261,  262,  263,   57,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  263,  257,  258,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   89,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
  257,  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  130,  131,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  140,   -1,   -1,  257,  258,  259,  260,   -1,   -1,   45,
   -1,   -1,   48,   49,   -1,   -1,   52,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,   63,   -1,   -1,
   66,   67,  257,  258,   -1,   -1,   -1,   73,   74,   75,
   76,   77,   78,   79,   80,   81,   82,   83,   84,   85,
   86,   87,   88,   -1,   90,   41,   42,   43,   44,   45,
   -1,   47,   -1,   -1,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,  119,   59,   60,   61,   62,   41,   42,
   43,   44,   45,   -1,   47,   -1,  132,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   93,   61,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,
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
"\"IGUAL\"","\"DISTINTO\"","\"AND\"","\"OR\"","\"NOT\"","\"VAR\"","\"STRUCT\"",
"\"IDENT\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"LITENT\"","\"PRINT\"",
"\"READ\"","\"IF\"","\"ELSE\"","\"WHILE\"","\"RETURN\"","\"LITREAL\"",
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
"expr : expr \"NOT\" expr",
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
//#line 503 "Parser.java"
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
