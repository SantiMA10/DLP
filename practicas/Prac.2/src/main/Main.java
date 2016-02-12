package main;

import java.io.*;
import sintactico.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	
    	//Yylex lex = new Yylex(new FileReader("src\\programa1.txt"));
    	Yylex lex = new Yylex(new FileReader("src\\programa2.txt"));

        Parser parser = new Parser(lex, false);

        if (parser.parse() != 0) {
            System.out.println("Programa no válido");
            return;
        }
        System.out.println("Programa correcto sintácticamente");

    }
}
