/* The following code was generated by JFlex 1.4.1 on 9/03/16 10:31 */

// @author Ra�l Izquierdo

/* -- No es necesario modificar esta parte ----------------------------------------------- */
package sintactico;

import java.io.*;
import main.*;
import ast.Position;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 9/03/16 10:31 from the specification file
 * <tt>lexico.l</tt>
 */
public class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\41\1\11\2\0\1\40\22\0\1\40\1\5\4\0\1\6"+
    "\1\12\2\1\1\37\4\1\1\36\12\10\2\1\1\2\1\3\1\4"+
    "\2\0\32\13\1\1\1\14\1\1\3\0\1\23\1\13\1\24\1\32"+
    "\1\27\1\20\1\13\1\25\1\16\2\13\1\21\1\13\1\15\1\22"+
    "\1\33\1\13\1\26\1\30\1\17\1\34\1\35\1\31\3\13\1\1"+
    "\1\7\1\1\123\0\1\13\37\0\1\13\uff0e\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\5\2\2\1\1\3\1\4\1\1\12\5"+
    "\1\2\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\3\0\1\5\1\15\11\5\1\4\1\0\1\16\1\17"+
    "\1\20\11\5\1\21\1\0\1\5\1\22\1\23\1\5"+
    "\1\24\1\25\3\5\1\26\2\5\1\27\1\30\1\31"+
    "\1\32";

  private static int [] zzUnpackAction() {
    int [] result = new int[76];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\42\0\42\0\104\0\146\0\210\0\252\0\314"+
    "\0\356\0\u0110\0\42\0\u0132\0\u0154\0\u0176\0\u0198\0\u01ba"+
    "\0\u01dc\0\u01fe\0\u0220\0\u0242\0\u0264\0\u0286\0\u02a8\0\42"+
    "\0\42\0\42\0\42\0\42\0\42\0\42\0\u02ca\0\u02ec"+
    "\0\u030e\0\u0330\0\u0154\0\u0352\0\u0374\0\u0396\0\u03b8\0\u03da"+
    "\0\u03fc\0\u041e\0\u0440\0\u0462\0\u0484\0\u04a6\0\u02ca\0\42"+
    "\0\u0154\0\u04c8\0\u04ea\0\u050c\0\u052e\0\u0550\0\u0572\0\u0594"+
    "\0\u05b6\0\u05d8\0\u0154\0\u05fa\0\u061c\0\u0154\0\u0154\0\u063e"+
    "\0\u0154\0\u0154\0\u0660\0\u0682\0\u06a4\0\u0154\0\u06c6\0\u06e8"+
    "\0\u0154\0\u0154\0\u0154\0\u0154";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[76];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\2\1\15\1\16\1\15"+
    "\1\17\3\15\1\20\1\15\1\21\1\22\1\23\1\24"+
    "\1\15\1\25\1\15\1\26\1\27\1\3\1\13\1\30"+
    "\45\0\1\31\41\0\1\32\41\0\1\33\41\0\1\34"+
    "\44\0\1\35\42\0\1\36\32\0\10\37\1\12\1\0"+
    "\30\37\10\0\1\40\2\0\1\40\1\41\21\40\14\0"+
    "\1\15\2\0\1\15\1\0\21\15\14\0\1\15\2\0"+
    "\1\15\1\0\1\42\2\15\1\43\15\15\14\0\1\15"+
    "\2\0\1\15\1\0\4\15\1\44\14\15\14\0\1\15"+
    "\2\0\1\15\1\0\6\15\1\45\1\15\1\46\10\15"+
    "\14\0\1\15\2\0\1\15\1\0\12\15\1\47\6\15"+
    "\14\0\1\15\2\0\1\15\1\0\4\15\1\50\14\15"+
    "\14\0\1\15\2\0\1\15\1\0\2\15\1\51\16\15"+
    "\14\0\1\15\2\0\1\15\1\0\10\15\1\52\10\15"+
    "\14\0\1\15\2\0\1\15\1\0\11\15\1\53\7\15"+
    "\14\0\1\15\2\0\1\15\1\0\6\15\1\54\12\15"+
    "\42\0\1\55\1\56\12\0\1\57\43\0\1\60\44\0"+
    "\1\40\34\0\1\15\2\0\1\15\1\0\2\15\1\61"+
    "\16\15\14\0\1\15\2\0\1\15\1\0\5\15\1\62"+
    "\13\15\14\0\1\15\2\0\1\15\1\0\13\15\1\63"+
    "\5\15\14\0\1\15\2\0\1\15\1\0\6\15\1\64"+
    "\12\15\14\0\1\15\2\0\1\15\1\0\2\15\1\65"+
    "\3\15\1\66\12\15\14\0\1\15\2\0\1\15\1\0"+
    "\13\15\1\67\5\15\14\0\1\15\2\0\1\15\1\0"+
    "\11\15\1\70\7\15\14\0\1\15\2\0\1\15\1\0"+
    "\1\15\1\71\17\15\14\0\1\15\2\0\1\15\1\0"+
    "\1\15\1\72\17\15\14\0\1\15\2\0\1\15\1\0"+
    "\11\15\1\73\7\15\4\0\11\55\1\0\30\55\37\56"+
    "\1\74\2\56\10\0\1\15\2\0\1\15\1\0\6\15"+
    "\1\75\12\15\14\0\1\15\2\0\1\15\1\0\2\15"+
    "\1\76\16\15\14\0\1\15\2\0\1\15\1\0\11\15"+
    "\1\77\7\15\14\0\1\15\2\0\1\15\1\0\17\15"+
    "\1\100\1\15\14\0\1\15\2\0\1\15\1\0\15\15"+
    "\1\101\3\15\14\0\1\15\2\0\1\15\1\0\12\15"+
    "\1\102\6\15\14\0\1\15\2\0\1\15\1\0\17\15"+
    "\1\103\1\15\14\0\1\15\2\0\1\15\1\0\4\15"+
    "\1\104\14\15\14\0\1\15\2\0\1\15\1\0\1\105"+
    "\20\15\4\0\36\56\1\13\1\74\2\56\10\0\1\15"+
    "\2\0\1\15\1\0\2\15\1\106\16\15\14\0\1\15"+
    "\2\0\1\15\1\0\11\15\1\107\7\15\14\0\1\15"+
    "\2\0\1\15\1\0\7\15\1\110\11\15\14\0\1\15"+
    "\2\0\1\15\1\0\12\15\1\111\6\15\14\0\1\15"+
    "\2\0\1\15\1\0\2\15\1\112\16\15\14\0\1\15"+
    "\2\0\1\15\1\0\1\113\20\15\14\0\1\15\2\0"+
    "\1\15\1\0\2\15\1\114\16\15\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1802];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\7\1\1\11\14\1\7\11\3\0\14\1"+
    "\1\0\1\1\1\11\13\1\1\0\20\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[76];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	public Yylex(Reader in, GestorErrores gestor) {
		this(in);
		this.gestor = gestor;
	}

	public int line() { return yyline + 1; }
	public int column() { return yycolumn + 1; }
	public String lexeme() { return yytext(); }

	// Traza para probar el l�xico de manera independiente al sint�ctico
	public static void main(String[] args) throws Exception {
		Yylex lex = new Yylex(new FileReader(Main.programa), new GestorErrores());
		int token;
		while ((token = lex.yylex()) != 0)
			System.out.println("\t[" + lex.line() + ":" + lex.column() + "] Token: " + token + ". Lexema: " + lex.lexeme());
	}

	private GestorErrores gestor;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 114) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 22: 
          { return Parser.FLOAT;
          }
        case 27: break;
        case 19: 
          { return Parser.CHAR;
          }
        case 28: break;
        case 13: 
          { return Parser.IF;
          }
        case 29: break;
        case 15: 
          { return Parser.LITCHAR;
          }
        case 30: break;
        case 20: 
          { return Parser.READ;
          }
        case 31: break;
        case 9: 
          { return Parser.MAYOR_IGUAL;
          }
        case 32: break;
        case 2: 
          { return yytext().charAt(0);
          }
        case 33: break;
        case 5: 
          { return Parser.IDENT;
          }
        case 34: break;
        case 26: 
          { return Parser.STRUCT;
          }
        case 35: break;
        case 1: 
          { gestor.error("L�xico", "Cadena \"" + yytext() +"\" no reconocida.", new Position(line(), column()));
          }
        case 36: break;
        case 11: 
          { return Parser.AND;
          }
        case 37: break;
        case 24: 
          { return Parser.PRINT;
          }
        case 38: break;
        case 25: 
          { return Parser.RETURN;
          }
        case 39: break;
        case 7: 
          { return Parser.MENOR_IGUAL;
          }
        case 40: break;
        case 21: 
          { return Parser.ELSE;
          }
        case 41: break;
        case 12: 
          { return Parser.OR;
          }
        case 42: break;
        case 8: 
          { return Parser.IGUAL;
          }
        case 43: break;
        case 18: 
          { return Parser.CAST;
          }
        case 44: break;
        case 3: 
          { return Parser.LITENT;
          }
        case 45: break;
        case 23: 
          { return Parser.WHILE;
          }
        case 46: break;
        case 6: 
          { yycolumn += 3;
          }
        case 47: break;
        case 14: 
          { return Parser.LITREAL;
          }
        case 48: break;
        case 10: 
          { return Parser.DISTINTO;
          }
        case 49: break;
        case 16: 
          { return Parser.INT;
          }
        case 50: break;
        case 17: 
          { return Parser.VAR;
          }
        case 51: break;
        case 4: 
          { 
          }
        case 52: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
