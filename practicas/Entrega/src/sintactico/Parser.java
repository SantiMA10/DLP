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
public final static short PRINT=270;
public final static short READ=271;
public final static short IF=272;
public final static short ELSE=273;
public final static short WHILE=274;
public final static short RETURN=275;
public final static short LITENT=276;
public final static short LITREAL=277;
public final static short LITCHAR=278;
public final static short CAST=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    4,    6,    6,    3,
    3,    5,    5,   11,   11,    9,    9,   13,   13,   14,
    7,    7,   10,   10,   10,    8,    8,   12,   12,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   17,   17,   18,   18,
};
final static short yylen[] = {                            2,
    1,    0,    2,    2,    1,    1,    6,    1,    2,    4,
    5,   10,    8,    0,    3,    1,    0,    1,    3,    3,
    1,    1,    1,    1,    1,    3,    4,    1,    2,    4,
    3,    3,    7,   11,    7,    5,    3,    2,    1,    1,
    1,    1,    7,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    4,    4,
    1,    0,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    5,    6,    0,    4,
    0,    0,    3,    0,    0,    0,    0,   16,    0,    0,
   21,   23,   24,   25,    0,    0,   22,    0,    0,    0,
    0,    0,    0,    0,   39,   40,   41,    0,    0,   10,
    0,    9,    0,   20,    0,    0,   19,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   11,    7,    0,    0,
    0,   57,    0,    0,   61,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   27,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   60,    0,   59,   15,    0,    0,
    0,    0,    0,   38,    0,   13,    0,   29,    0,   64,
    0,    0,   31,   32,    0,    0,   37,    0,    0,    0,
    0,    0,    0,   30,   12,   43,   36,    0,    0,    0,
    0,    0,   35,    0,    0,    0,   34,
};
final static short yydgoto[] = {                          4,
    5,    6,   28,    7,    8,   29,   25,   26,   17,   27,
   70,  100,   18,   19,  101,  102,   74,   75,
};
final static short yysindex[] = {                      -227,
 -255, -253,  -14,    0,    0, -227,    0,    0,  -31,    0,
  -94, -225,    0,  -66, -255,   -6,   17,    0,   15,    2,
    0,    0,    0,    0,    5, -250,    0, -255,  -60, -206,
  -57, -225,    2,   40,    0,    0,    0,    7,  150,    0,
   25,    0,   31,    0, -173, -206,    0,   88,    2, -206,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    1,    0,    0, -255,  -26,
  -21,    0,  354,   68,    0,   49,  493,  493,  539,  539,
  107,  107,  100,  100,  -34,  -34,  -44,  -44,  -44,   21,
  375,    0, -173,   73,    2,    2,   74,   79,   -5,   -4,
  382,  -26, -173,    2,    0,   86,    0,    0,    2,  405,
  412,    2,    2,    0,  433,    0,    2,    0,  -26,    0,
    2,   87,    0,    0,   95,  118,    0,  440,   18,  125,
   80,    9,   24,    0,    0,    0,    0,  -26,  -26,   19,
   26, -121,    0,   35,  -26,   29,    0,
};
final static short yyrindex[] = {                       169,
    0,    0,    0,    0,    0,  169,    0,    0,    0,    0,
    0,  132,    0,    0,    0,    0,    0,    0,  133,    0,
    0,    0,    0,    0,    0,    0,    0,   50,    0,    0,
    0,    0,    0,  -38,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -12,    0,    0,    0,  135,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -223,    0,    0,    0,    0,
    0,    0,  136,    0,    0,    0,  785,  804,  562,  702,
  -11,  780,  529,  536,  500,  507,   34,   56,   63,   27,
    0,    0,  -12,  465,    0,    0,    0,    0,    0,    0,
    0,   58,  -12,    0,    0,    0,    0,    0,  135,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  472,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -40,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  178,    0,   14,    0,    0,  161,  164,  128,    0,   10,
  -83,   43,  167,    0,  755,    0,   96,  102,
};
final static int YYTABLESIZE=897;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   46,   64,   42,   42,   42,   42,   42,   42,   42,  108,
    9,   64,   11,   33,   10,   21,   22,   23,   24,  119,
   42,   42,   42,   42,   20,   12,   14,   14,   15,   48,
   48,   48,   48,   48,   33,   48,    1,    2,    3,   44,
   16,   33,   26,   26,   26,   26,   65,   48,   48,   48,
   48,   30,   42,  114,   42,   71,   65,   31,   32,   76,
   22,   23,   24,   40,   43,   45,   50,   58,   58,   58,
   58,   58,   58,   58,   54,   54,   54,   54,   54,   49,
   54,   48,   93,   67,   33,   58,   58,   58,   58,   68,
   69,   20,   54,   54,   54,   54,   55,   55,   55,   55,
   55,  103,   55,   56,   56,   56,   56,   56,  105,   56,
  106,   65,  109,  112,   55,   55,   55,   55,  113,   58,
  116,   56,   56,   56,   56,  121,   54,  131,   72,   53,
   51,  138,   52,   64,   54,  132,   53,   51,  137,   52,
   64,   54,  135,  142,  118,   64,  139,   55,   55,   56,
  143,  144,   64,  147,   55,   56,   56,  145,  133,   53,
   51,  129,   52,   64,   54,  136,   53,   51,    2,   52,
   64,   54,   17,   18,    8,   62,   63,   55,   65,   56,
  140,  141,   28,   13,   55,   65,   56,  146,   42,   41,
   65,   53,   51,   92,   52,   64,   54,   65,   47,   21,
   22,   23,   24,    0,  122,  120,    0,    0,   65,   55,
    0,   56,    0,    0,    0,   65,    0,    0,   42,   42,
   42,   42,   42,   42,   42,   33,   61,   62,   63,   33,
   33,   33,    0,   33,   33,   33,   33,   33,   33,   94,
   65,    0,   66,   95,   96,   97,    0,   98,   99,   35,
   36,   37,   38,   14,    0,    0,    0,   14,   14,   14,
   34,   14,   14,   14,   14,   14,   14,   34,    0,    0,
   35,   36,   37,   38,    0,    0,    0,   35,   36,   37,
   38,    0,    0,   58,   58,   58,   58,   58,   58,   58,
   54,   54,   54,   54,   54,   54,   54,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   55,   55,   55,   55,   55,   55,   55,   56,
   56,   56,   56,   56,   56,   56,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   57,   58,   59,   60,   61,   62,
   63,   57,   58,   59,   60,   61,   62,   63,   59,   60,
   61,   62,   63,   57,   58,   59,   60,   61,   62,   63,
    0,    0,    0,    0,   57,   58,   59,   60,   61,   62,
   63,   57,   58,   59,   60,   61,   62,   63,    0,    0,
    0,    0,    0,    0,    0,   53,   51,  104,   52,   64,
   54,    0,    0,    0,    0,    0,   57,   58,   59,   60,
   61,   62,   63,   55,    0,   56,   53,   51,    0,   52,
   64,   54,    0,   53,   51,    0,   52,   64,   54,    0,
    0,    0,    0,    0,   55,    0,   56,    0,    0,    0,
    0,   55,  117,   56,   65,    0,   53,   51,    0,   52,
   64,   54,    0,   53,   51,    0,   52,   64,   54,    0,
    0,    0,    0,  123,   55,   65,   56,  107,    0,    0,
  124,   55,   65,   56,   53,   51,    0,   52,   64,   54,
    0,   53,   51,    0,   52,   64,   54,    0,    0,    0,
    0,  127,   55,    0,   56,   65,    0,    0,  134,   55,
    0,   56,   65,    0,    0,    0,   42,   42,    0,   42,
   42,   42,    0,   60,   60,    0,   60,   60,   60,    0,
    0,    0,    0,   65,   42,   42,   42,    0,    0,    0,
   65,   60,   60,   60,   53,    0,    0,    0,   64,   54,
   52,   52,   52,   52,   52,    0,   52,   53,   53,   53,
   53,   53,   55,   53,   56,   42,    0,    0,   52,   52,
   52,   52,   60,    0,    0,   53,   53,   53,   53,   50,
   50,   50,   50,   50,    0,   50,   51,   51,   51,   51,
   51,    0,   51,   65,   64,    0,    0,   50,   50,   50,
   50,    0,   52,    0,   51,   51,   51,   51,   55,   53,
   56,    0,   44,   44,   44,   44,   44,    0,   44,    0,
   57,   58,   59,   60,   61,   62,   63,    0,    0,    0,
   44,   50,   44,    0,    0,    0,    0,    0,   51,   65,
    0,   57,   58,   59,   60,   61,   62,   63,   57,   58,
   59,   60,   61,   62,   63,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   44,    0,    0,    0,    0,    0,
    0,   57,   58,   59,   60,   61,   62,   63,   57,   58,
   59,   60,   61,   62,   63,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   58,   59,   60,   61,   62,   63,   57,   58,   59,   60,
   61,   62,   63,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   42,   42,   42,   42,   42,   42,   42,   60,   60,
   60,   60,   60,   60,   60,    0,    0,    0,    0,    0,
    0,    0,   45,   45,   45,   45,   45,    0,   45,   57,
   58,   59,   60,   61,   62,   63,   52,   52,   52,   52,
   45,    0,   45,   53,   53,   53,   53,    0,    0,    0,
    0,    0,    0,    0,   39,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,   50,   48,    0,    0,
    0,    0,   51,   51,   45,   57,   58,   59,   60,   61,
   62,   63,    0,   73,    0,   77,   78,   79,   80,   81,
   82,   83,   84,   85,   86,   87,   88,   89,   90,   91,
   49,   49,   49,   49,   49,   46,   49,   46,   46,   46,
    0,    0,    0,    0,    0,    0,    0,    0,   49,   49,
   49,   49,    0,   46,   47,   46,   47,   47,   47,  110,
  111,    0,    0,  115,    0,    0,    0,    0,   73,    0,
    0,    0,   47,   73,   47,    0,  125,  126,    0,    0,
    0,  128,   49,    0,    0,  130,    0,   46,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   47,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   58,   46,   41,   42,   43,   44,   45,   46,   47,   93,
  266,   46,  266,   40,    1,  266,  267,  268,  269,  103,
   59,   60,   61,   62,   91,   40,   58,   40,  123,   41,
   42,   43,   44,   45,   40,   47,  264,  265,  266,   30,
  266,   40,  266,  267,  268,  269,   91,   59,   60,   61,
   62,   58,   91,   59,   93,   46,   91,   41,   44,   50,
  267,  268,  269,   59,  125,  123,   60,   41,   42,   43,
   44,   45,   46,   47,   41,   42,   43,   44,   45,   40,
   47,   93,   69,   59,  125,   59,   60,   61,   62,   59,
  264,   91,   59,   60,   61,   62,   41,   42,   43,   44,
   45,  123,   47,   41,   42,   43,   44,   45,   41,   47,
   62,   91,   40,   40,   59,   60,   61,   62,   40,   93,
  125,   59,   60,   61,   62,   40,   93,   41,   41,   42,
   43,  123,   45,   46,   47,   41,   42,   43,   59,   45,
   46,   47,  125,  125,  102,   46,  123,   60,   93,   62,
  125,  273,   46,  125,   60,   93,   62,  123,   41,   42,
   43,  119,   45,   46,   47,   41,   42,   43,    0,   45,
   46,   47,   41,   41,  125,   41,   41,   60,   91,   62,
  138,  139,  125,    6,   60,   91,   62,  145,   28,   26,
   91,   42,   43,   66,   45,   46,   47,   91,   32,  266,
  267,  268,  269,   -1,  109,  104,   -1,   -1,   91,   60,
   -1,   62,   -1,   -1,   -1,   91,   -1,   -1,  257,  258,
  259,  260,  261,  262,  263,  266,  261,  262,  263,  270,
  271,  272,   -1,  274,  275,  276,  277,  278,  279,  266,
   91,   -1,   93,  270,  271,  272,   -1,  274,  275,  276,
  277,  278,  279,  266,   -1,   -1,   -1,  270,  271,  272,
  266,  274,  275,  276,  277,  278,  279,  266,   -1,   -1,
  276,  277,  278,  279,   -1,   -1,   -1,  276,  277,  278,
  279,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
  257,  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,  263,  257,
  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,  257,  258,  259,  260,  261,  262,  263,  259,  260,
  261,  262,  263,  257,  258,  259,  260,  261,  262,  263,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,  257,  258,  259,  260,  261,  262,  263,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   42,   43,   44,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   60,   -1,   62,   42,   43,   -1,   45,
   46,   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,
   -1,   60,   61,   62,   91,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   59,   60,   91,   62,   93,   -1,   -1,
   59,   60,   91,   62,   42,   43,   -1,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   59,   60,   -1,   62,   91,   -1,   -1,   59,   60,
   -1,   62,   91,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   91,   60,   61,   62,   -1,   -1,   -1,
   91,   60,   61,   62,   42,   -1,   -1,   -1,   46,   47,
   41,   42,   43,   44,   45,   -1,   47,   41,   42,   43,
   44,   45,   60,   47,   62,   91,   -1,   -1,   59,   60,
   61,   62,   91,   -1,   -1,   59,   60,   61,   62,   41,
   42,   43,   44,   45,   -1,   47,   41,   42,   43,   44,
   45,   -1,   47,   91,   46,   -1,   -1,   59,   60,   61,
   62,   -1,   93,   -1,   59,   60,   61,   62,   60,   93,
   62,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
  257,  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,
   59,   93,   61,   -1,   -1,   -1,   -1,   -1,   93,   91,
   -1,  257,  258,  259,  260,  261,  262,  263,  257,  258,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  263,  257,  258,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,  257,  258,  259,  260,
  261,  262,  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  263,  257,  258,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,  257,
  258,  259,  260,  261,  262,  263,  257,  258,  259,  260,
   59,   -1,   61,  257,  258,  259,  260,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   20,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,   33,   -1,   -1,
   -1,   -1,  257,  258,   93,  257,  258,  259,  260,  261,
  262,  263,   -1,   49,   -1,   51,   52,   53,   54,   55,
   56,   57,   58,   59,   60,   61,   62,   63,   64,   65,
   41,   42,   43,   44,   45,   41,   47,   43,   44,   45,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   59,   41,   61,   43,   44,   45,   95,
   96,   -1,   -1,   99,   -1,   -1,   -1,   -1,  104,   -1,
   -1,   -1,   59,  109,   61,   -1,  112,  113,   -1,   -1,
   -1,  117,   93,   -1,   -1,  121,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,
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
"\"IDENT\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"PRINT\"","\"READ\"","\"IF\"",
"\"ELSE\"","\"WHILE\"","\"RETURN\"","\"LITENT\"","\"LITREAL\"","\"LITCHAR\"",
"\"CAST\"",
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
"funcion : \"IDENT\" '(' parametros_ ')' ':' tipo_basico '{' definiciones_funcion sentencias_locales '}'",
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
"expr : expr '.' expr",
"expr : expr '[' expr ']'",
"expr : \"IDENT\" '(' paso_parametros_ ')'",
"paso_parametros_ : paso_parametros",
"paso_parametros_ :",
"paso_parametros : expr",
"paso_parametros : expr ',' paso_parametros",
};

//#line 130 "sintac.y"
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
//#line 512 "Parser.java"
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
