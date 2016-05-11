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
public final static short AND=257;
public final static short OR=258;
public final static short MAYOR_IGUAL=259;
public final static short MENOR_IGUAL=260;
public final static short IGUAL=261;
public final static short DISTINTO=262;
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
public final static short FOR=276;
public final static short LITREAL=277;
public final static short LITCHAR=278;
public final static short CAST=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    4,    6,    6,    3,
    5,    5,    9,    9,    8,    8,   11,   11,   12,    7,
    7,    7,    7,    7,   10,   10,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   15,   15,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   16,   16,   17,   17,
};
final static short yylen[] = {                            2,
    1,    0,    2,    2,    1,    1,    6,    1,    2,    4,
   10,    8,    0,    3,    1,    0,    1,    3,    3,    1,
    1,    1,    1,    4,    0,    2,    4,    3,    3,    7,
   11,    7,    5,    3,   15,    2,    1,    3,    1,    1,
    1,    1,    7,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    2,    3,    3,    4,    4,
    1,    0,    1,    3,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    0,    3,    5,    6,    0,    4,
    0,    0,    0,    0,    0,    0,    0,   17,    0,   20,
   21,   22,   23,    0,    8,    0,    0,    0,    0,    0,
   10,    0,    9,   19,   13,    0,   18,    0,    7,    0,
    0,   24,    0,    0,   13,   14,    0,    0,    0,   12,
   39,    0,    0,    0,    0,    0,    0,   40,   41,    0,
   26,    0,    0,    0,    0,   56,    0,    0,    0,    0,
    0,   36,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   57,    0,    0,    0,   28,   29,    0,    0,
   34,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   58,    0,    0,    0,    0,    0,    0,    0,   11,
    0,    0,    0,    0,    0,    0,    0,   59,   27,    0,
   60,   33,    0,   25,   25,    0,    0,    0,    0,    0,
    0,    0,    0,   32,    0,   43,    0,    0,   25,    0,
    0,    0,   31,    0,    0,   25,    0,   35,
};
final static short yydgoto[] = {                          1,
    2,    6,   10,    7,    8,   26,   24,   16,   40,   44,
   17,   18,   61,   62,  119,   95,   96,
};
final static short yysindex[] = {                         0,
    0, -209, -260, -245,  -19,    0,    0,    0,  -17,    0,
  -95, -236,  -89, -260,   -1,    2,    6,    0, -229,    0,
    0,    0,    0,    7,    0, -121,  -89,  -55, -236,  -34,
    0,   10,    0,    0,    0,  -89,    0,  -89,    0, -199,
  -53,    0, -260,  -33,    0,    0,   90,   90,   34,    0,
    0,   90,   90,   36,   37,   87,   38,    0,    0,   19,
    0,  482, -199,   40,  364,    0,   90,  489,  515,   90,
   90,    0,  522,   90,  -89,   90,   90,   90,   90,   90,
   90,   90,   90, -184,   90,   90,   90,   90,   90,   90,
  -18,   90,    0,  831,   43,   39,    0,    0,  391,  398,
    0,  572,   24,  837,  837,  188,  188,   96,   96,  -29,
  -29,    0,  -30,  -30,    8,    8,  744,  831,  -15,    0,
   47,   30,   90,  -32,  -28,   90,   58,    0,    0,   90,
    0,    0,  831,    0,    0,  750,   90,  831,   -3,   12,
   90,  421, -173,    0,  756,    0,  -22,   90,    0,  778,
   27,   90,    0,  428,  -21,    0,   42,    0,
};
final static short yyrindex[] = {                         0,
    0,  103,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   63,    0,    0,    0,    0,   65,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
    0,    0,    0,    0,    0,    0,    0,    0,  803,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   57,  113,    0,    0,   67,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   67,    0,   -6,    0,   68,    0,    0,    0,    0,
    0,    0,    0,   -8,   52,  848,  854,  666,  844,  454,
  461,    0,  -35,  155,  335,  342,    0,  -12,    0,    0,
    0,  810,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -2,    0,    0,    0,    0,    4,    0,    0,
    0,    0,   72,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    5,    0,    0,    0,   -4,    0,   69,  -62,
    0,   81,    0,  904,    0,   23,    0,
};
final static int YYTABLESIZE=1112;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         48,
   91,   19,   36,   32,    9,   46,   47,   46,   46,   46,
   46,   87,   87,   85,   48,   86,   88,   88,   25,   11,
   12,   47,   34,   46,   46,   46,   46,   14,   15,   48,
   33,   41,   54,   42,   63,   54,   47,   63,   64,   30,
   13,   64,   28,  129,   48,  130,   37,   46,   37,   29,
   54,   47,   54,    3,    4,    5,   27,   46,   38,   48,
   89,   89,   38,   43,   38,   31,   47,   35,   39,   45,
  103,  139,  140,   67,   48,   70,   71,   74,   75,   92,
  112,   47,  123,  122,   54,  127,  151,  131,  132,   25,
  134,   50,   55,  157,  135,   55,   25,  137,   89,  147,
  149,  156,    1,   16,   30,   15,  120,   62,   61,   37,
   55,   30,   55,   63,  121,    0,    0,    0,    0,   48,
    0,  143,   48,    0,    0,    0,   47,    0,    0,   47,
    0,    0,    0,    0,    0,    0,  144,   87,   85,    0,
   86,   84,   88,    9,   55,   72,    0,    0,    0,    0,
    0,  153,    0,   42,   42,   42,   42,   42,   42,   42,
    0,    0,    0,    0,    0,    0,  158,    0,    0,    0,
    0,   42,   42,   42,   42,   20,   21,   22,   23,    0,
    0,   25,    0,    0,    0,    0,   89,    0,    0,    0,
    0,    0,    0,    0,    0,   47,   30,   47,   47,   47,
   47,    0,    0,   42,    0,   42,    0,    0,    0,    0,
    0,    0,    0,   47,   47,   47,   47,    0,    0,    0,
    0,   46,   46,   46,   46,   46,   46,    0,    0,   87,
   85,   49,   86,   84,   88,   51,   52,   53,   54,    0,
   55,   56,   57,   58,   59,   60,   49,   47,   54,   54,
   51,   52,   53,   54,    0,   55,   56,   57,   58,   59,
   60,   49,    0,    0,    0,   51,   52,   53,   54,    0,
   55,   56,   57,   58,   59,   60,   49,    0,   89,    0,
   51,   52,   53,   54,    0,   55,   56,   57,   58,   59,
   60,   49,    0,    0,    0,   51,   52,   53,   54,    0,
   55,   56,   57,   58,   59,   60,   49,    0,   55,   55,
   51,   52,   53,   54,    0,   55,   56,   57,   58,   59,
   60,   25,    0,    0,    0,   25,   25,   25,   25,    0,
   25,   25,   25,   25,   25,   25,   30,    0,    0,    0,
   30,   30,   30,   30,    0,   30,   30,   30,   30,   30,
   30,   64,    0,    0,   64,   51,   82,   83,   51,    0,
    0,    0,    0,   58,   59,   60,   58,   59,   60,   42,
   42,   42,   42,   42,   42,   44,   44,   44,   44,   44,
   44,   44,   45,   45,   45,   45,   45,   45,   45,    0,
    0,    0,    0,   44,   44,   44,   44,    0,    0,    0,
   45,   45,   45,   45,   93,   87,   85,    0,   86,   84,
   88,   47,   47,   47,   47,   47,   47,    0,    0,    0,
    0,    0,    0,   78,    0,   79,    0,   44,    0,    0,
    0,  124,   87,   85,   45,   86,   84,   88,  125,   87,
   85,    0,   86,   84,   88,    0,   80,   81,   82,   83,
   78,    0,   79,    0,   89,    0,    0,   78,    0,   79,
    0,  146,   87,   85,    0,   86,   84,   88,  155,   87,
   85,    0,   86,   84,   88,    0,    0,    0,    0,    0,
   78,   89,   79,    0,    0,    0,    0,   78,   89,   79,
    0,    0,    0,    0,   52,    0,    0,   52,    0,   52,
    0,   53,    0,    0,   53,    0,   53,    0,    0,    0,
    0,   89,   52,   52,   52,   52,    0,    0,   89,   53,
   53,   53,   53,   87,   85,    0,   86,   84,   88,    0,
   87,   85,    0,   86,   84,   88,    0,    0,    0,    0,
    0,   78,   90,   79,    0,    0,   52,   97,   78,    0,
   79,    0,    0,   53,    0,    0,   87,   85,    0,   86,
   84,   88,    0,   87,   85,    0,   86,   84,   88,    0,
    0,    0,   89,   98,   78,    0,   79,    0,    0,   89,
  101,   78,    0,   79,    0,    0,    0,    0,    0,    0,
    0,   44,   44,   44,   44,   44,   44,    0,   45,   45,
   45,   45,   45,   45,    0,   89,    0,    0,    0,    0,
    0,    0,   89,   87,   85,    0,   86,   84,   88,    0,
   76,   77,   80,   81,   82,   83,    0,    0,    0,    0,
    0,   78,  126,   79,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   76,   77,   80,
   81,   82,   83,    0,   76,   77,   80,   81,   82,   83,
    0,    0,   89,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   76,   77,   80,
   81,   82,   83,    0,   76,   77,   80,   81,   82,   83,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   50,    0,    0,   50,
   52,   52,   52,   52,   52,   52,    0,   53,   53,   53,
   53,   53,   53,    0,   50,   50,   50,   50,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   76,   77,
   80,   81,   82,   83,    0,   76,   77,   80,   81,   82,
   83,    0,    0,    0,    0,    0,    0,    0,   50,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   76,   77,   80,   81,   82,   83,    0,   76,   77,
   80,   81,   82,   83,    0,   87,   85,    0,   86,   84,
   88,   87,   85,    0,   86,   84,   88,   87,   85,    0,
   86,   84,   88,   78,    0,   79,    0,    0,  141,   78,
    0,   79,    0,    0,  148,   78,    0,   79,    0,   87,
   85,    0,   86,   84,   88,    0,    0,    0,   76,   77,
   80,   81,   82,   83,   89,    0,  128,   78,  152,   79,
   89,    0,    0,    0,   42,   42,   89,   42,   42,   42,
    0,   60,   60,    0,   60,   60,   60,    0,    0,    0,
    0,    0,   42,   42,   42,    0,    0,    0,   89,   60,
   60,   60,   87,   85,    0,   86,   84,   88,   87,   85,
    0,   86,   84,   88,   51,    0,    0,   51,   48,    0,
   78,   48,   79,   42,   49,    0,   78,   49,   79,    0,
   60,    0,   51,   51,   51,   51,   48,   48,   48,   48,
    0,    0,   49,   49,   49,   49,    0,    0,    0,    0,
    0,   89,   50,   50,   50,   50,    0,   89,    0,    0,
    0,    0,    0,    0,    0,    0,   51,    0,    0,    0,
   48,    0,    0,    0,    0,    0,   49,    0,    0,    0,
   65,   66,    0,    0,    0,   68,   69,    0,    0,   73,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   94,    0,    0,   99,  100,    0,    0,  102,    0,  104,
  105,  106,  107,  108,  109,  110,  111,    0,  113,  114,
  115,  116,  117,  118,    0,   94,    0,    0,    0,    0,
   76,   77,   80,   81,   82,   83,   76,   77,   80,   81,
   82,   83,   76,   77,   80,   81,   82,   83,    0,    0,
    0,    0,    0,    0,    0,    0,  133,    0,    0,  136,
    0,    0,    0,  138,   76,   77,   80,   81,   82,   83,
  142,    0,    0,    0,  145,    0,    0,    0,    0,    0,
    0,  150,    0,    0,    0,  154,    0,    0,    0,   42,
   42,   42,   42,   42,   42,    0,   60,   60,   60,   60,
   60,   60,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   76,   77,   80,
   81,   82,   83,    0,    0,   80,   81,   82,   83,    0,
   51,   51,   51,   51,   48,   48,    0,    0,    0,    0,
   49,   49,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   63,   91,   58,  125,  265,   41,   40,   43,   44,   45,
   46,   42,   42,   43,   33,   45,   47,   47,   14,  265,
   40,   40,   27,   59,   60,   61,   62,  123,  265,   33,
   26,   36,   41,   38,   41,   44,   40,   44,   41,  269,
   58,   44,   41,   59,   33,   61,   59,   43,   61,   44,
   59,   40,   61,  263,  264,  265,   58,   93,   93,   33,
   91,   91,   59,  263,   61,   59,   40,  123,   59,  123,
   75,  134,  135,   40,   33,   40,   40,   40,   60,   40,
  265,   40,   44,   41,   93,   62,  149,   41,   59,   33,
  123,  125,   41,  156,  123,   44,   40,   40,   91,  273,
  123,  123,    0,   41,   33,   41,  125,   41,   41,   29,
   59,   40,   61,   45,   92,   -1,   -1,   -1,   -1,   33,
   -1,  125,   33,   -1,   -1,   -1,   40,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   -1,   -1,  125,   42,   43,   -1,
   45,   46,   47,  265,   93,   59,   -1,   -1,   -1,   -1,
   -1,  125,   -1,   41,   42,   43,   44,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,  265,  266,  267,  268,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   41,  125,   43,   44,   45,
   46,   -1,   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,   42,
   43,  265,   45,   46,   47,  269,  270,  271,  272,   -1,
  274,  275,  276,  277,  278,  279,  265,   93,  257,  258,
  269,  270,  271,  272,   -1,  274,  275,  276,  277,  278,
  279,  265,   -1,   -1,   -1,  269,  270,  271,  272,   -1,
  274,  275,  276,  277,  278,  279,  265,   -1,   91,   -1,
  269,  270,  271,  272,   -1,  274,  275,  276,  277,  278,
  279,  265,   -1,   -1,   -1,  269,  270,  271,  272,   -1,
  274,  275,  276,  277,  278,  279,  265,   -1,  257,  258,
  269,  270,  271,  272,   -1,  274,  275,  276,  277,  278,
  279,  265,   -1,   -1,   -1,  269,  270,  271,  272,   -1,
  274,  275,  276,  277,  278,  279,  265,   -1,   -1,   -1,
  269,  270,  271,  272,   -1,  274,  275,  276,  277,  278,
  279,  265,   -1,   -1,  265,  269,  261,  262,  269,   -1,
   -1,   -1,   -1,  277,  278,  279,  277,  278,  279,  257,
  258,  259,  260,  261,  262,   41,   42,   43,   44,   45,
   46,   47,   41,   42,   43,   44,   45,   46,   47,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
   59,   60,   61,   62,   41,   42,   43,   -1,   45,   46,
   47,  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   60,   -1,   62,   -1,   93,   -1,   -1,
   -1,   41,   42,   43,   93,   45,   46,   47,   41,   42,
   43,   -1,   45,   46,   47,   -1,  259,  260,  261,  262,
   60,   -1,   62,   -1,   91,   -1,   -1,   60,   -1,   62,
   -1,   41,   42,   43,   -1,   45,   46,   47,   41,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   60,   91,   62,   -1,   -1,   -1,   -1,   60,   91,   62,
   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   46,
   -1,   41,   -1,   -1,   44,   -1,   46,   -1,   -1,   -1,
   -1,   91,   59,   60,   61,   62,   -1,   -1,   91,   59,
   60,   61,   62,   42,   43,   -1,   45,   46,   47,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   93,   59,   60,   -1,
   62,   -1,   -1,   93,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   91,   59,   60,   -1,   62,   -1,   -1,   91,
   59,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,  257,  258,
  259,  260,  261,  262,   -1,   91,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   42,   43,   -1,   45,   46,   47,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
  257,  258,  259,  260,  261,  262,   -1,  257,  258,  259,
  260,  261,  262,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,  257,  258,  259,  260,  261,
  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,  257,  258,
  259,  260,  261,  262,   -1,   42,   43,   -1,   45,   46,
   47,   42,   43,   -1,   45,   46,   47,   42,   43,   -1,
   45,   46,   47,   60,   -1,   62,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   59,   60,   -1,   62,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   91,   -1,   93,   60,   61,   62,
   91,   -1,   -1,   -1,   42,   43,   91,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   60,   61,   62,   -1,   -1,   -1,   91,   60,
   61,   62,   42,   43,   -1,   45,   46,   47,   42,   43,
   -1,   45,   46,   47,   41,   -1,   -1,   44,   41,   -1,
   60,   44,   62,   91,   41,   -1,   60,   44,   62,   -1,
   91,   -1,   59,   60,   61,   62,   59,   60,   61,   62,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   91,  257,  258,  259,  260,   -1,   91,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   47,   48,   -1,   -1,   -1,   52,   53,   -1,   -1,   56,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   67,   -1,   -1,   70,   71,   -1,   -1,   74,   -1,   76,
   77,   78,   79,   80,   81,   82,   83,   -1,   85,   86,
   87,   88,   89,   90,   -1,   92,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,  257,  258,  259,  260,
  261,  262,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,  126,
   -1,   -1,   -1,  130,  257,  258,  259,  260,  261,  262,
  137,   -1,   -1,   -1,  141,   -1,   -1,   -1,   -1,   -1,
   -1,  148,   -1,   -1,   -1,  152,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,   -1,  259,  260,  261,  262,   -1,
  257,  258,  259,  260,  257,  258,   -1,   -1,   -1,   -1,
  257,  258,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=279;
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
null,null,null,null,null,null,null,null,null,"\"AND\"","\"OR\"",
"\"MAYOR_IGUAL\"","\"MENOR_IGUAL\"","\"IGUAL\"","\"DISTINTO\"","\"VAR\"",
"\"STRUCT\"","\"IDENT\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"LITENT\"",
"\"PRINT\"","\"READ\"","\"IF\"","\"ELSE\"","\"WHILE\"","\"RETURN\"","\"FOR\"",
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
"sentencias_locales :",
"sentencias_locales : sentencias_locales sentencia_local",
"sentencia_local : expr '=' asignado ';'",
"sentencia_local : \"PRINT\" expr ';'",
"sentencia_local : \"READ\" expr ';'",
"sentencia_local : \"IF\" '(' expr ')' '{' sentencias_locales '}'",
"sentencia_local : \"IF\" '(' expr ')' '{' sentencias_locales '}' \"ELSE\" '{' sentencias_locales '}'",
"sentencia_local : \"WHILE\" '(' expr ')' '{' sentencias_locales '}'",
"sentencia_local : \"IDENT\" '(' paso_parametros_ ')' ';'",
"sentencia_local : \"RETURN\" expr ';'",
"sentencia_local : \"FOR\" '(' expr '=' expr ';' expr ';' expr '=' expr ')' '{' sentencias_locales '}'",
"sentencia_local : \"RETURN\" ';'",
"asignado : expr",
"asignado : asignado '=' expr",
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
"expr : expr '.' \"IDENT\"",
"expr : expr '[' expr ']'",
"expr : \"IDENT\" '(' paso_parametros_ ')'",
"paso_parametros_ : paso_parametros",
"paso_parametros_ :",
"paso_parametros : expr",
"paso_parametros : paso_parametros ',' expr",
};

//#line 135 "sintac.y"
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
//#line 554 "Parser.java"
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
//#line 27 "sintac.y"
{ raiz = new Programa(val_peek(0)); }
break;
case 2:
//#line 30 "sintac.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 3:
//#line 31 "sintac.y"
{ ((ArrayList<Sentencia>) val_peek(1)).add((Sentencia)val_peek(0)); yyval = val_peek(1); }
break;
case 4:
//#line 34 "sintac.y"
{ ((DefVar)val_peek(0)).setAmbito("global"); yyval = val_peek(0); }
break;
case 5:
//#line 35 "sintac.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 36 "sintac.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 39 "sintac.y"
{ yyval = new Struct(val_peek(4), val_peek(2)); }
break;
case 8:
//#line 42 "sintac.y"
{ yyval = new ArrayList<DefVar>(); ((ArrayList<DefVar>) yyval).add((DefVar)val_peek(0)); }
break;
case 9:
//#line 43 "sintac.y"
{ ((ArrayList<DefVar>) val_peek(1)).add((DefVar)val_peek(0)); yyval = val_peek(1); }
break;
case 10:
//#line 46 "sintac.y"
{ yyval = new DefVar(val_peek(3), val_peek(1), ""); }
break;
case 11:
//#line 49 "sintac.y"
{ yyval = new Funcion(val_peek(9), val_peek(7), val_peek(2), val_peek(1), val_peek(4)); }
break;
case 12:
//#line 50 "sintac.y"
{ yyval = new Funcion(val_peek(7), val_peek(5), val_peek(2), val_peek(1), null); }
break;
case 13:
//#line 53 "sintac.y"
{ yyval = new ArrayList<DefVar>(); }
break;
case 14:
//#line 54 "sintac.y"
{ ((DefVar)val_peek(0)).setAmbito("var"); ((ArrayList<DefVar>) val_peek(2)).add((DefVar)val_peek(0)); yyval = val_peek(2); }
break;
case 15:
//#line 57 "sintac.y"
{ yyval = val_peek(0); }
break;
case 16:
//#line 58 "sintac.y"
{ yyval = new ArrayList<Parametro>();}
break;
case 17:
//#line 61 "sintac.y"
{ yyval = new ArrayList<Parametro>(); ((ArrayList<Parametro>) yyval).add((Parametro)val_peek(0)); }
break;
case 18:
//#line 62 "sintac.y"
{ ((ArrayList<Parametro>) val_peek(2)).add((Parametro)val_peek(0)); yyval = val_peek(2); }
break;
case 19:
//#line 65 "sintac.y"
{ yyval = new Parametro(val_peek(2), val_peek(0)); }
break;
case 20:
//#line 68 "sintac.y"
{ yyval = new StructType(val_peek(0)); }
break;
case 21:
//#line 69 "sintac.y"
{ yyval = new IntType(); }
break;
case 22:
//#line 70 "sintac.y"
{ yyval = new RealType(); }
break;
case 23:
//#line 71 "sintac.y"
{ yyval = new CharType(); }
break;
case 24:
//#line 72 "sintac.y"
{ yyval = new ArrayType(val_peek(0), val_peek(2)); }
break;
case 25:
//#line 75 "sintac.y"
{  yyval = new ArrayList<Sent_func>(); }
break;
case 26:
//#line 76 "sintac.y"
{ ((ArrayList<Sent_func>) val_peek(1)).add((Sent_func)val_peek(0)); yyval = val_peek(1); }
break;
case 27:
//#line 79 "sintac.y"
{ yyval = new Asignacion(val_peek(3), val_peek(1)); }
break;
case 28:
//#line 80 "sintac.y"
{ yyval = new Print(val_peek(1)); }
break;
case 29:
//#line 81 "sintac.y"
{ yyval = new Read(val_peek(1)); }
break;
case 30:
//#line 82 "sintac.y"
{ yyval = new If(val_peek(4), val_peek(1), null ); }
break;
case 31:
//#line 83 "sintac.y"
{ yyval = new If(val_peek(8), val_peek(5), val_peek(1) ); }
break;
case 32:
//#line 84 "sintac.y"
{ yyval = new While(val_peek(4), val_peek(1)); }
break;
case 33:
//#line 85 "sintac.y"
{ yyval = new Invocacion(val_peek(4), val_peek(2), "llamada"); }
break;
case 34:
//#line 86 "sintac.y"
{ yyval = new Return(val_peek(1)); }
break;
case 35:
//#line 88 "sintac.y"
{ 	
			   										ArrayList<Expr> list1 = new ArrayList<Expr>(); 
			   										list1.add((Expr)val_peek(10));
			   										ArrayList<Expr> list2 = new ArrayList<Expr>(); 
			   										list2.add((Expr)val_peek(4));
			   										yyval = new For(new Asignacion(val_peek(12), list1), val_peek(8), new Asignacion(val_peek(6), list2), val_peek(1)); 
			   									}
break;
case 36:
//#line 95 "sintac.y"
{ yyval = new Return(null); }
break;
case 37:
//#line 98 "sintac.y"
{ yyval = new ArrayList<Expr>(); ((ArrayList<Expr>) yyval).add((Expr)val_peek(0)); }
break;
case 38:
//#line 99 "sintac.y"
{ ((ArrayList<Expr>) val_peek(2)).add((Expr)val_peek(0)); yyval = val_peek(2);  }
break;
case 39:
//#line 102 "sintac.y"
{ yyval = new Lintent(val_peek(0)); }
break;
case 40:
//#line 103 "sintac.y"
{ yyval = new Lintreal(val_peek(0)); }
break;
case 41:
//#line 104 "sintac.y"
{ yyval = new Lintchar(val_peek(0)); }
break;
case 42:
//#line 105 "sintac.y"
{ yyval = new Var(val_peek(0)); }
break;
case 43:
//#line 106 "sintac.y"
{ yyval = new Cast( val_peek(4), val_peek(1)); }
break;
case 44:
//#line 107 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "*", val_peek(0) ); }
break;
case 45:
//#line 108 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "/", val_peek(0) ); }
break;
case 46:
//#line 109 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "+", val_peek(0) ); }
break;
case 47:
//#line 110 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "-", val_peek(0) ); }
break;
case 48:
//#line 111 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "<", val_peek(0) ); }
break;
case 49:
//#line 112 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), ">", val_peek(0) ); }
break;
case 50:
//#line 113 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), ">=", val_peek(0) ); }
break;
case 51:
//#line 114 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "<=", val_peek(0) ); }
break;
case 52:
//#line 115 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "==", val_peek(0) ); }
break;
case 53:
//#line 116 "sintac.y"
{ yyval = new ExpresionNumerica( val_peek(2), "!=", val_peek(0) ); }
break;
case 54:
//#line 117 "sintac.y"
{ yyval = new ExpresionLogica( val_peek(2), "&&", val_peek(0) ); }
break;
case 55:
//#line 118 "sintac.y"
{ yyval = new ExpresionLogica( val_peek(2), "||", val_peek(0) ); }
break;
case 56:
//#line 119 "sintac.y"
{ yyval = new OperacionUnaria("!", val_peek(0)); }
break;
case 57:
//#line 120 "sintac.y"
{ yyval = new OperacionUnaria("()", val_peek(1)); }
break;
case 58:
//#line 121 "sintac.y"
{ yyval = new AccesoStruct( val_peek(2), val_peek(0) );}
break;
case 59:
//#line 122 "sintac.y"
{ yyval = new AccesoArray( val_peek(3), val_peek(1) ); }
break;
case 60:
//#line 123 "sintac.y"
{ yyval = new Invocacion( val_peek(3), val_peek(1), "parametro" ); }
break;
case 61:
//#line 126 "sintac.y"
{ yyval = val_peek(0); }
break;
case 62:
//#line 127 "sintac.y"
{ yyval = new ArrayList<Expr>(); }
break;
case 63:
//#line 130 "sintac.y"
{ yyval = new ArrayList<Expr>(); ((ArrayList<Expr>) yyval).add((Expr)val_peek(0)); }
break;
case 64:
//#line 131 "sintac.y"
{ ((ArrayList<Expr>) val_peek(2)).add((Expr)val_peek(0)); yyval = val_peek(2); }
break;
//#line 964 "Parser.java"
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
