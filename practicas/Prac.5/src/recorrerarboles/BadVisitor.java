package recorrerarboles;

import AST.*;

/*
 * No se puede hacer: no hay doble-dispatch en Java
 * 
public class BadVisitor {
    public void visit(Programa programa) {
        for (int i =0; i < programa.sentencias.size(); i++)
            visit(programa.sentencias.get(i));
    }
    
    public void visit(Print print) {
       System.out.print("print ");
       visit(print.expr);
       System.out.println(";");
    }
    
    public void visit(Read read) {
       System.out.print("read ");
       visit(read.var);
       System.out.println(";");
    }
    
    public void visit(Suma suma) {
        visit(suma.left);
        System.out.print(" + ");
        visit(suma.right);
    }
    
    public void visit(RefVar refVar) {
        System.out.print(refVar.name);
    }
}
*/