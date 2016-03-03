package recorrerarboles;
import AST.*;

/*
 * Inconvenientes: Todo el c�digo de todos los nodos en un solo metodo
 */

public class RecorridoRecursivo {
    
    public void visit(AST nodo) {
        if (nodo instanceof Programa) {
            Programa programa = (Programa) nodo;
            for (int i =0; i < programa.sentencias.size(); i++)
                visit((AST)programa.sentencias.get(i));
            
        } else if (nodo instanceof Print) {
            System.out.print("print ");
            visit(((Print)nodo).expr);
            System.out.println(";");
            
        } else if (nodo instanceof Read) {
            System.out.print("read ");
            visit(((Read)nodo).var);
            System.out.println(";");
            
        } else if (nodo instanceof Suma) {
            visit(((Suma)nodo).left);
            System.out.print(" + ");
            visit(((Suma)nodo).right);
            
        } else if (nodo instanceof RefVar) {
            System.out.print(((RefVar)nodo).name);
        }
    }
}