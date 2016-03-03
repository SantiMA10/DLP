package recorrerarboles;

import AST.*;
import Visitor.PrintVisitor;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        /*
        read a;
        print a + b;
         */

        List<Sentencia> sentencias = new ArrayList<Sentencia>();
        sentencias.add(new Read(new RefVar("a")));

        Suma suma = new Suma(new RefVar("a"), new RefVar("b"));
        sentencias.add(new Print(suma));

        Programa prog = new Programa(sentencias);


        RecorridoRecursivo rr = new RecorridoRecursivo();
        rr.visit(prog);

//        BadVisitor bad = new BadVisitor(); // No compila
//        bad.visit(prog);

        PrintVisitor pv = new PrintVisitor();
        pv.visit(prog, null);

    }
}
