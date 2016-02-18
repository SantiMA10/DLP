package sintactico;

public interface Tokens {
	
	static final int LITENT = 257;
	static final int LITREAL = 258;
	static final int LITCHAR = 266;
	static final int IDENT = 259;

	static final int WHILE = 260;
	static final int IF = 261;
	static final int ELSE = 274;


	static final int PRINT = 262;
	static final int READ = 263;

	static final int INT = 264;
	static final int FLOAT = 265;
	static final int CHAR = 267;
	
	static final int IGUAL = 268;
	static final int DISTINTO = 269;
	static final int MAYOR_IGUAL = 270;
	static final int MENOR_IGUAL = 271;
	static final int AND = 272;
	static final int OR = 273;
	
	static final int STRUCT = 275;
	static final int VAR = 276;
	static final int CAST = 277;
	static final int RETURN = 278;
	
}
