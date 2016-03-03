package Visitor;
import AST.*;

public interface Visitor {
    public Object visit(Programa p, Object param);
    public Object visit(Print p, Object param);
    public Object visit(Read r, Object param);
    public Object visit(Suma s, Object param);
    public Object visit(RefVar r, Object param);
}
