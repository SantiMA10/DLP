package AST;

import java.util.*;
import Visitor.*;

public class Programa implements AST {
    public List<Sentencia> sentencias;
    
    public Programa(List<Sentencia> sentencias) {
        this.sentencias = sentencias;
    }
    
    public Object accept(Visitor v, Object param) {
    	return v.visit(this, param);
    }
}
