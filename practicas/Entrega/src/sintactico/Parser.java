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
    5,    5,    9,    9,    8,    8,   11,   11,   12,    7,
    7,    7,    7,    7,   10,   10,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   15,   15,   16,
   16,
};
final static short yylen[] = {                            2,
    1,    0,    2,    2,    1,    1,    6,    1,    2,    4,
   10,    8,    0,    3,    1,    0,    1,    3,    3,    1,
    1,    1,    1,    4,    1,    2,    4,    3,    3,    7,
   11,    7,    5,    3,    2,    1,    1,    1,    1,    7,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    2,    3,    3,    4,    4,    1,    0,    1,
    3,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    0,    3,    5,    6,    0,    4,
    0,    0,    0,    0,    0,    0,    0,   17,    0,   20,
   21,   22,   23,    0,    8,    0,    0,    0,    0,    0,
   10,    0,    9,   19,   13,    0,   18,    0,    7,    0,
    0,   24,    0,    0,    0,    0,   36,    0,    0,    0,
    0,    0,   37,   38,    0,    0,   25,    0,   13,    0,
    0,    0,   14,    0,    0,    0,    0,    0,   35,    0,
    0,   12,   26,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   54,    0,    0,    0,   28,   29,    0,    0,   34,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,   27,   11,   57,   33,    0,    0,    0,
    0,    0,    0,    0,    0,   32,   40,    0,    0,    0,
   31,
};
final static short yydgoto[] = {                          1,
    2,    6,   10,    7,    8,   26,   24,   16,   40,   56,
   17,   18,   57,   58,   93,   94,
};
final static short yysindex[] = {                         0,
    0, -226, -252, -247,  -16,    0,    0,    0,  -32,    0,
  -89, -224,   96, -252,  -15,    3,    8,    0, -219,    0,
    0,    0,    0,   -4,    0, -123,   96,  -47, -224,  -36,
    0,   -1,    0,    0,    0,   96,    0,   96,    0,  -33,
  -64,    0,   82,   82, -252,   20,    0,   82,   82,   33,
   34,   79,    0,    0,   15,  -19,    0,  407,    0,   38,
  -30,  -37,    0,   82,  429,  435,   82,   82,    0,  457,
   96,    0,    0,   82,   82,   82,   82,   82,   82,   82,
   82,   82,   82,   82,   82,   82,   82,   82,  -33,   82,
    0,  523,   41,   42,    0,    0,  369,  379,    0,   25,
  563,  563,  -45,  -45,  388,  388,  373,  373,  -43,  -43,
  -30,  -30,    1,  463,  485,   -5,   52,   35,   82,  -28,
  -24,   60,    0,    0,    0,    0,    0,  523,   65,   65,
   82,    9,   23,  401, -172,    0,    0,  -21,   65,   37,
    0,
};
final static short yyrindex[] = {                         0,
    0,  103,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   63,    0,    0,    0,    0,   73,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  491,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  108,
  147,    0,    0,   75,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   75,
    0,  -22,    0,   77,    0,    0,    0,    0,    0,    0,
  -14,  612,   24,  886,   66,  879,  787,  809,  745,  752,
  330,  340,  136,    0,    0,    0,    0,  517,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   -8,    0,    0,
    0,    0,    0,    0,   51,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,    6,    0,    0,    0,   26,    0,   62,  -49,
    0,   94,  -44,  829,   39,    0,
};
final static int YYTABLESIZE=1067;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   86,   32,   86,   91,   76,   74,   44,   75,   86,   77,
   36,   73,    9,   43,   78,   86,   79,   11,   60,   25,
   44,   60,   78,   12,   79,   13,   43,   43,   43,   43,
   43,   33,   61,   14,   44,   61,    3,    4,    5,  116,
   15,   43,   27,   28,   43,   87,   43,   87,   44,   30,
   63,   29,   34,   87,   31,   43,   38,   39,   59,   64,
   87,   41,   44,   42,   41,   41,   41,   41,   41,   43,
   41,   73,   67,   68,   71,   35,   44,   90,   43,  132,
  133,  118,   41,   30,   41,  119,  122,   73,   73,  140,
   30,   87,  126,  127,  129,   73,  100,   43,  130,  131,
  138,  139,    1,   16,   44,   72,   45,   45,   45,   45,
   45,   43,   45,   15,   43,   59,   41,   58,   44,  125,
   89,   44,   37,    0,   45,   45,   45,   45,  117,    0,
    0,    0,    0,  135,    0,    0,    0,   69,    0,    0,
    0,    9,    0,    0,    0,    0,    0,  136,   39,   39,
   39,   39,   39,   39,   39,    0,    0,    0,   45,    0,
    0,  141,    0,    0,    0,    0,   39,   39,   39,   39,
    0,    0,    0,    0,    0,   30,   55,   55,   55,   55,
   55,   55,   55,    0,    0,    0,   19,   53,   53,   53,
   53,   53,    0,   53,   55,   55,   55,   55,   39,    0,
   39,    0,    0,    0,    0,   53,   53,   53,   53,    0,
    0,   80,   81,   82,   83,   84,   85,   84,   85,   80,
   81,   82,   83,   84,   85,    0,    0,    0,   55,   45,
    0,   46,    0,    0,    0,   47,   48,   49,   50,   53,
   51,   52,   53,   54,   55,   46,    0,    0,    0,   47,
   48,   49,   50,    0,   51,   52,   53,   54,   55,   46,
    0,    0,    0,   47,   48,   49,   50,    0,   51,   52,
   53,   54,   55,   46,    0,    0,    0,   47,   48,   49,
   50,    0,   51,   52,   53,   54,   55,   46,    0,    0,
    0,   47,   48,   49,   50,    0,   51,   52,   53,   54,
   55,   46,    0,    0,    0,   47,   48,   49,   50,    0,
   51,   52,   53,   54,   55,   30,    0,    0,    0,   30,
   30,   30,   30,    0,   30,   30,   30,   30,   30,   46,
    0,    0,    0,   47,   48,   49,   50,    0,   51,   52,
   53,   54,   55,   60,    0,    0,   60,   47,    0,    0,
   47,    0,    0,    0,   53,   54,   55,   53,   54,   55,
   20,   21,   22,   23,   39,   39,   39,   39,   39,   39,
   51,   51,   51,   51,   51,    0,   51,    0,    0,    0,
   52,   52,   52,   52,   52,    0,   52,    0,   51,   51,
   51,   51,   55,   55,   55,   55,   55,   55,   52,   52,
   52,   52,    0,   53,   53,   53,   53,   53,   53,  120,
   76,   74,    0,   75,   86,   77,    0,    0,   86,  121,
   76,   74,   51,   75,   86,   77,    0,    0,   78,    0,
   79,    0,   52,   86,    0,    0,    0,    0,   78,    0,
   79,  137,   76,   74,    0,   75,   86,   77,   76,   74,
    0,   75,   86,   77,    0,    0,    0,    0,    0,   87,
   78,    0,   79,   87,    0,    0,   78,   88,   79,   87,
   76,   74,    0,   75,   86,   77,   76,   74,   87,   75,
   86,   77,    0,    0,    0,    0,    0,   95,   78,    0,
   79,   87,    0,   96,   78,    0,   79,   87,   76,   74,
    0,   75,   86,   77,   76,   74,    0,   75,   86,   77,
    0,    0,    0,    0,    0,   99,   78,    0,   79,   87,
    0,    0,   78,    0,   79,   87,   76,   74,    0,   75,
   86,   77,   39,   39,    0,   39,   39,   39,    0,    0,
    0,    0,    0,  124,   78,    0,   79,   87,    0,    0,
   39,   39,   39,   87,    0,  123,    0,    0,   57,   57,
    0,   57,   57,   57,   76,   74,    0,   75,   86,   77,
    0,    0,    0,    0,    0,   87,   57,   57,   57,    0,
    0,   39,   78,    0,   79,    0,   51,   51,   51,   51,
   51,   51,    0,    0,    0,    0,   52,   52,   52,   52,
   52,   52,    0,    0,   76,    0,    0,   57,   86,   77,
    0,    0,    0,   87,    0,    0,    0,    0,    0,    0,
    0,    0,   78,    0,   79,   80,   81,   82,   83,   84,
   85,   82,   83,   84,   85,   80,   81,   82,   83,   84,
   85,    0,    0,    0,   80,   81,   82,   83,   84,   85,
    0,    0,   44,   87,   44,   44,   44,   80,   81,   82,
   83,   84,   85,   80,   81,   82,   83,   84,   85,    0,
   44,    0,   44,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   80,   81,   82,   83,   84,
   85,   80,   81,   82,   83,   84,   85,    0,    0,    0,
    0,    0,    0,    0,   44,    0,    0,    0,    0,    0,
    0,    0,    0,   80,   81,   82,   83,   84,   85,   80,
   81,   82,   83,   84,   85,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   80,   81,   82,   83,   84,   85,   39,   39,   39,
   39,   39,   39,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   57,   57,   57,   57,   57,   57,   80,
   81,   82,   83,   84,   85,   49,   49,   49,   49,   49,
    0,   49,   50,   50,   50,   50,   50,    0,   50,    0,
    0,    0,    0,   49,   49,   49,   49,    0,    0,    0,
   50,   50,   50,   50,    0,    0,    0,    0,    0,   80,
   81,   82,   83,   84,   85,    0,    0,   47,   47,   47,
   47,   47,    0,   47,    0,    0,    0,   49,    0,    0,
    0,    0,    0,    0,   50,   47,   47,   47,   47,   48,
   48,   48,   48,   48,    0,   48,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   48,   48,   48,
   48,   61,   62,    0,    0,    0,   65,   66,    0,   47,
   70,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   92,    0,    0,   97,   98,    0,    0,    0,
    0,   48,  101,  102,  103,  104,  105,  106,  107,  108,
  109,  110,  111,  112,  113,  114,  115,    0,   92,   46,
   46,   46,   46,   46,    0,   46,   42,   42,   42,   42,
   42,    0,   42,    0,    0,    0,    0,   46,   46,   46,
   46,    0,    0,    0,   42,    0,   42,  128,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  134,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   46,    0,    0,    0,    0,    0,    0,   42,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   49,   49,   49,   49,    0,    0,    0,   50,   50,
   50,   50,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   47,   47,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   48,   48,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   46,  125,   46,   41,   42,   43,   40,   45,   46,   47,
   58,   56,  265,   33,   60,   46,   62,  265,   41,   14,
   40,   44,   60,   40,   62,   58,   41,   33,   43,   44,
   45,   26,   41,  123,   40,   44,  263,  264,  265,   89,
  265,   33,   58,   41,   59,   91,   61,   91,   40,  269,
   45,   44,   27,   91,   59,   33,   93,   59,  123,   40,
   91,   36,   40,   38,   41,   42,   43,   44,   45,   33,
   47,  116,   40,   40,   60,  123,   40,   40,   93,  129,
  130,   41,   59,   33,   61,   44,   62,  132,  133,  139,
   40,   91,   41,   59,  123,  140,   71,   33,  123,   40,
  273,  123,    0,   41,   40,  125,   41,   42,   43,   44,
   45,   33,   47,   41,   33,   41,   93,   41,   40,  125,
   59,   40,   29,   -1,   59,   60,   61,   62,   90,   -1,
   -1,   -1,   -1,  125,   -1,   -1,   -1,   59,   -1,   -1,
   -1,  265,   -1,   -1,   -1,   -1,   -1,  125,   41,   42,
   43,   44,   45,   46,   47,   -1,   -1,   -1,   93,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,  125,   41,   42,   43,   44,
   45,   46,   47,   -1,   -1,   -1,   91,   41,   42,   43,
   44,   45,   -1,   47,   59,   60,   61,   62,   91,   -1,
   93,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,  257,  258,  259,  260,  261,  262,  261,  262,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,   93,  263,
   -1,  265,   -1,   -1,   -1,  269,  270,  271,  272,   93,
  274,  275,  276,  277,  278,  265,   -1,   -1,   -1,  269,
  270,  271,  272,   -1,  274,  275,  276,  277,  278,  265,
   -1,   -1,   -1,  269,  270,  271,  272,   -1,  274,  275,
  276,  277,  278,  265,   -1,   -1,   -1,  269,  270,  271,
  272,   -1,  274,  275,  276,  277,  278,  265,   -1,   -1,
   -1,  269,  270,  271,  272,   -1,  274,  275,  276,  277,
  278,  265,   -1,   -1,   -1,  269,  270,  271,  272,   -1,
  274,  275,  276,  277,  278,  265,   -1,   -1,   -1,  269,
  270,  271,  272,   -1,  274,  275,  276,  277,  278,  265,
   -1,   -1,   -1,  269,  270,  271,  272,   -1,  274,  275,
  276,  277,  278,  265,   -1,   -1,  265,  269,   -1,   -1,
  269,   -1,   -1,   -1,  276,  277,  278,  276,  277,  278,
  265,  266,  267,  268,  257,  258,  259,  260,  261,  262,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   -1,   47,   -1,   59,   60,
   61,   62,  257,  258,  259,  260,  261,  262,   59,   60,
   61,   62,   -1,  257,  258,  259,  260,  261,  262,   41,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   46,   41,
   42,   43,   93,   45,   46,   47,   -1,   -1,   60,   -1,
   62,   -1,   93,   46,   -1,   -1,   -1,   -1,   60,   -1,
   62,   41,   42,   43,   -1,   45,   46,   47,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   91,
   60,   -1,   62,   91,   -1,   -1,   60,   61,   62,   91,
   42,   43,   -1,   45,   46,   47,   42,   43,   91,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   91,   -1,   59,   60,   -1,   62,   91,   42,   43,
   -1,   45,   46,   47,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,   91,
   -1,   -1,   60,   -1,   62,   91,   42,   43,   -1,   45,
   46,   47,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   91,   -1,   -1,
   60,   61,   62,   91,   -1,   93,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   91,   60,   61,   62,   -1,
   -1,   91,   60,   -1,   62,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   42,   -1,   -1,   91,   46,   47,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   -1,   62,  257,  258,  259,  260,  261,
  262,  259,  260,  261,  262,  257,  258,  259,  260,  261,
  262,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   41,   91,   43,   44,   45,  257,  258,  259,
  260,  261,  262,  257,  258,  259,  260,  261,  262,   -1,
   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  257,  258,  259,
  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  257,
  258,  259,  260,  261,  262,   41,   42,   43,   44,   45,
   -1,   47,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   59,   60,   61,   62,   41,
   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   43,   44,   -1,   -1,   -1,   48,   49,   -1,   93,
   52,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   64,   -1,   -1,   67,   68,   -1,   -1,   -1,
   -1,   93,   74,   75,   76,   77,   78,   79,   80,   81,
   82,   83,   84,   85,   86,   87,   88,   -1,   90,   41,
   42,   43,   44,   45,   -1,   47,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   59,   -1,   61,  119,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  131,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,   -1,  257,  258,
  259,  260,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,
};
}
final static short YYFINAL=1;
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
"sentencias : sentencias sentencia",
"sentencia : \"VAR\" definicion",
"sentencia : struct",
"sentencia : funcion",
"struct : \"STRUCT\" \"IDENT\" '{' definiciones '}' ';'",
"definiciones : definicion",
"definiciones : definiciones definicion",
"definicion : \"IDENT\" ':' tipo ';'",
"funcion : \"IDENT\" '(' parametros_ ')' ':' tipo '{' definiciones_funcion sentencias_locales '}'",
"funcion : \"IDENT\" '(' parametros_ ')' '{' definiciones_funcion sentencias_locales '}'",
"definiciones_funcion :",
"definiciones_funcion : definiciones_funcion \"VAR\" definicion",
"parametros_ : parametros",
"parametros_ :",
"parametros : parametro",
"parametros : parametros ',' parametro",
"parametro : \"IDENT\" ':' tipo",
"tipo : \"IDENT\"",
"tipo : \"INT\"",
"tipo : \"FLOAT\"",
"tipo : \"CHAR\"",
"tipo : '[' \"LITENT\" ']' tipo",
"sentencias_locales : sentencia_local",
"sentencias_locales : sentencias_locales sentencia_local",
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
"paso_parametros : paso_parametros ',' expr",
};

//#line 125 "sintac.y"
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
//#line 537 "Parser.java"
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
case 1:
//#line 29 "sintac.y"
{ raiz = new Programa(val_peek(0)); }
break;
case 2:
//#line 32 "sintac.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 3:
//#line 33 "sintac.y"
{ ((ArrayList<Sentencia>) val_peek(1)).add((Sentencia)val_peek(0)); yyval = val_peek(1); }
break;
case 4:
//#line 36 "sintac.y"
{ yyval = val_peek(1); }
break;
case 5:
//#line 37 "sintac.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 38 "sintac.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 41 "sintac.y"
{ yyval = new Struct(val_peek(4), val_peek(3)); }
break;
case 8:
//#line 44 "sintac.y"
{ yyval = new ArrayList<DefVar>(); }
break;
case 9:
//#line 45 "sintac.y"
{ ((ArrayList<DefVar>) val_peek(1)).add((DefVar)val_peek(0)); yyval = val_peek(1); }
break;
case 10:
//#line 48 "sintac.y"
{ yyval = new DefVar(val_peek(3), val_peek(1)); }
break;
case 11:
//#line 51 "sintac.y"
{ yyval = new Funcion(val_peek(9), val_peek(7), val_peek(4), val_peek(2), val_peek(1)); }
break;
case 12:
//#line 52 "sintac.y"
{ yyval = new Funcion(val_peek(7), val_peek(5), null, val_peek(2), val_peek(1)); }
break;
case 13:
//#line 55 "sintac.y"
{ yyval = new ArrayList<DefVar>(); }
break;
case 14:
//#line 56 "sintac.y"
{ ((ArrayList<DefVar>) val_peek(2)).add((DefVar)val_peek(1)); yyval = val_peek(2); }
break;
case 15:
//#line 59 "sintac.y"
{ yyval = new ArrayList<Parametro>(); }
break;
case 17:
//#line 63 "sintac.y"
{ yyval = val_peek(0); }
break;
case 18:
//#line 64 "sintac.y"
{ ((ArrayList<Parametro>) val_peek(2)).add((Parametro)val_peek(0)); yyval = val_peek(2); }
break;
case 19:
//#line 67 "sintac.y"
{ yyval = new Parametro(val_peek(2), val_peek(0)); }
break;
case 20:
//#line 70 "sintac.y"
{}
break;
case 21:
//#line 71 "sintac.y"
{ yyval = new IntType(); }
break;
case 22:
//#line 72 "sintac.y"
{ yyval = new RealType(); }
break;
case 23:
//#line 73 "sintac.y"
{ yyval = new CharType(); }
break;
case 24:
//#line 74 "sintac.y"
{ yyval = new ArrayType(val_peek(2), val_peek(0)); }
break;
case 25:
//#line 77 "sintac.y"
{ yyval = val_peek(0); }
break;
case 26:
//#line 78 "sintac.y"
{ ((ArrayList<Sent_func>) val_peek(1)).add((Sent_func)val_peek(0)); yyval = val_peek(1); }
break;
case 27:
//#line 81 "sintac.y"
{ yyval = new Asignacion(val_peek(3), val_peek(1)); }
break;
case 28:
//#line 82 "sintac.y"
{ yyval = new Print(val_peek(1)); }
break;
case 29:
//#line 83 "sintac.y"
{ yyval = new Read(val_peek(1)); }
break;
case 30:
//#line 84 "sintac.y"
{ yyval = new If(val_peek(4), val_peek(1), null ); }
break;
case 31:
//#line 85 "sintac.y"
{ yyval = new If(val_peek(8), val_peek(5), val_peek(1) ); }
break;
case 32:
//#line 86 "sintac.y"
{ yyval = new While(val_peek(4), val_peek(1)); }
break;
case 33:
//#line 87 "sintac.y"
{ yyval = new Invocacion(val_peek(4), val_peek(2)); }
break;
case 34:
//#line 88 "sintac.y"
{ yyval = new Return(val_peek(1)); }
break;
case 35:
//#line 89 "sintac.y"
{ yyval = new Return(null); }
break;
case 36:
//#line 92 "sintac.y"
{ yyval = new Lintent(val_peek(0)); }
break;
case 37:
//#line 93 "sintac.y"
{ yyval = new Lintreal(val_peek(0)); }
break;
case 38:
//#line 94 "sintac.y"
{ yyval = new Lintchar(val_peek(0)); }
break;
case 39:
//#line 95 "sintac.y"
{ yyval = new Var(val_peek(0)); }
break;
case 40:
//#line 96 "sintac.y"
{ yyval = new Cast( val_peek(4), val_peek(1)); }
break;
case 41:
//#line 97 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "*", val_peek(0) ); }
break;
case 42:
//#line 98 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "/", val_peek(0) ); }
break;
case 43:
//#line 99 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "+", val_peek(0) ); }
break;
case 44:
//#line 100 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "-", val_peek(0) ); }
break;
case 45:
//#line 101 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "<", val_peek(0) ); }
break;
case 46:
//#line 102 "sintac.y"
{ yyval = new Op_bin( val_peek(2), ">", val_peek(0) ); }
break;
case 47:
//#line 103 "sintac.y"
{ yyval = new Op_bin( val_peek(2), ">=", val_peek(0) ); }
break;
case 48:
//#line 104 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "<=", val_peek(0) ); }
break;
case 49:
//#line 105 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "==", val_peek(0) ); }
break;
case 50:
//#line 106 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "!=", val_peek(0) ); }
break;
case 51:
//#line 107 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "&&", val_peek(0) ); }
break;
case 52:
//#line 108 "sintac.y"
{ yyval = new Op_bin( val_peek(2), "||", val_peek(0) ); }
break;
case 53:
//#line 109 "sintac.y"
{ yyval = new Op_un("!", val_peek(0)); }
break;
case 54:
//#line 110 "sintac.y"
{ yyval = new Op_un("()", val_peek(1)); }
break;
case 55:
//#line 111 "sintac.y"
{ yyval = new Op_bin( val_peek(2), ".", val_peek(0) );}
break;
case 56:
//#line 112 "sintac.y"
{ yyval = new Op_bin( val_peek(3), "[]", val_peek(1) ); }
break;
case 57:
//#line 113 "sintac.y"
{ yyval = new Invocacion( val_peek(3), val_peek(1) ); }
break;
case 58:
//#line 116 "sintac.y"
{ yyval = new ArrayList<Expr>(); }
break;
case 60:
//#line 120 "sintac.y"
{ yyval = val_peek(0); }
break;
case 61:
//#line 121 "sintac.y"
{ ((ArrayList<Expr>) val_peek(2)).add((Expr)val_peek(1)); yyval = val_peek(2); }
break;
//#line 921 "Parser.java"
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
