/*
Añadir EN ESTE ORDEN:
- Constantes reales: 3.15, 78.23, ... (parte entera obligatoria/parte decimal OPCIONAL)
- Operadores aritméticos y otros simbolos:  + - * / . : = ; [ ] ( ) { } < >
- Palabras reservadas: if, while, read, write, int y real
 */

import java.io.IOException;
import java.io.Reader;

public class Yylex {
	private Reader input;
	private String lexeme;
	
	private String simbolosYOperadores = ";+-*/.:=;[](){}<>";

	public Yylex(Reader reader) throws IOException {
		input = reader;
		readNext();
	}

	public String lexeme() {
		return lexeme;
	}

	public int yylex() throws IOException {

		while (true) {
			while (Character.isWhitespace(getChar()))
				readNext();

			if (noMoreChars())
				return 0;

			if (simbolosYOperadores.contains(getChar()+"")) {
				char char_ = getChar();
				lexeme = getChar()+"";
				readNext();
				return char_;
			}

			if (Character.isDigit(getChar())) {
				StringBuffer buffer = new StringBuffer();
				boolean real = false;
				buffer.append(getChar());
				readNext();
				do{
					buffer.append(getChar());
					real = real || getChar() == '.';
					readNext();
				}
				while (Character.isDigit(getChar()) || getChar() == '.');
				lexeme = buffer.toString();
				return real ? Tokens.LITREAL : Tokens.LITINT;
			}

			if (Character.isLetter(getChar())) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(getChar());
				while (Character.isLetterOrDigit(readNext()))
					buffer.append(getChar());
				lexeme = buffer.toString();
				return checkPalabrasReservadas(lexeme);
			}

			System.out.println("Error léxico: " + getChar());
			readNext();
		}
	}

	private int currentChar; // Se inicializa en constructor

	// Esta funcion devuelve:
	//  Si hay caracteres => el carácter leído
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
	
	private int checkPalabrasReservadas(String entrada){
		switch (entrada.toLowerCase()) {
		case "if":
			return Tokens.IF;
		case "int":
			return Tokens.INT;
		case "real":
			return Tokens.REAL;
		case "read":
			return Tokens.READ;
		case "while":
			return Tokens.WHILE;
		case "write":
			return Tokens.WRITE;
		default:
			return Tokens.IDENT;
		}
		
	}
}
