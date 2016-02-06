
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try {
			//Yylex lex = new Yylex(new InputStreamReader(System.in));
			Yylex lex = new Yylex(new FileReader("ejemplo.txt"));

			int token;
			while ((token = lex.yylex()) != 0){
				System.out.println("[" +  lex.line() + ":" + lex.column() + "]" + "\tToken: " + token + " Lexema: " + lex.lexeme());
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
