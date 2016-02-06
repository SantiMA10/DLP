/*
A�adir EN ESTE ORDEN:
- Constantes reales: 3.15, 78.23, ... (parte entera obligatoria/parte decimal opcional)
- Operadores aritmeticos y otros simbolos:  + - * / . : = ; [ ] ( ) { } < >
- Palabras reservadas: if, while, read, write, int y real
 */

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Yylex {
	private Reader input;
	private String lexeme;
	private int lineCount = 1;

	private String oneCharTokens;
	private Map<String, Integer> reservedWords;

	public Yylex(Reader reader) throws IOException {

		String defaultTokens = "+-*/.:=;[](){}<>";

		Map<String, Integer> defaultWords = new HashMap<String, Integer>();
		defaultWords.put("while", Tokens.WHILE);
		defaultWords.put("if", Tokens.IF);
		defaultWords.put("int", Tokens.INT);
		defaultWords.put("real", Tokens.REAL);
		defaultWords.put("write", Tokens.WRITE);
		defaultWords.put("read", Tokens.READ);

		initialize(reader, defaultWords, defaultTokens);
	}

	public Yylex(Reader reader, Map<String, Integer> reservedWords, String oneCharTokens) throws IOException {
		initialize(reader, reservedWords, oneCharTokens);
	}

	private void initialize(Reader reader, Map<String, Integer> reservedWords, String oneCharTokens) throws IOException {
		this.oneCharTokens = oneCharTokens;
		this.reservedWords = reservedWords;

		input = reader;
		readNext();
	}

	public String lexeme() {
		return lexeme;
	}

	public int line() {
		return lineCount;
	}

	public int yylex() throws IOException {

		while (true) {
			while (Character.isWhitespace(getChar())) {
				if (getChar() == '\n')
					lineCount++;
				readNext();
			}

			if (noMoreChars())
				return 0;

			int index = oneCharTokens.indexOf(getChar());
			if (index != -1) {
				lexeme = Character.toString(getChar());
				readNext();
				return oneCharTokens.charAt(index);
			}

			if (Character.isDigit(getChar())) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(getChar());
				while (Character.isDigit(readNext()))
					buffer.append(getChar());

				if (getChar() != '.') {
					lexeme = buffer.toString();
					return Tokens.LITENT;
				}

				buffer.append(getChar());
				while (Character.isDigit(readNext()))
					buffer.append(getChar());
				lexeme = buffer.toString();
				return Tokens.LITREAL;
			}

			if (Character.isLetter(getChar())) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(getChar());
				while (Character.isLetterOrDigit(readNext()))
					buffer.append(getChar());
				lexeme = buffer.toString();

				Object token = reservedWords.get(lexeme);
				if (token != null)
					return ((Integer) token).intValue();
				return Tokens.IDENT;
			}

			System.out.println("Error lexico en linea " + line() + " '" + getChar() + "'");
			readNext();
		}
	}

	private int currentChar; // Se inicializa en constructor

	// Esta funcion devuelve:
	//  Si hay caracteres => el car�cter le�do
	//	Si no final de fichero entonces -1
	private char getChar() throws IOException {
		return (char) currentChar; // Si es -1 se convierte en 65535
	}

	private char readNext() throws IOException {
		currentChar = input.read();
		return (char) currentChar; // Si es -1 se convierte en 65535
	}

	private boolean noMoreChars() {
		return (currentChar == -1);
	}
}
