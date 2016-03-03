package AST;

import Visitor.*;

public class RefVar implements Expresion {
    public String name;
 
    public RefVar(String name) {
        this.name = name;
    }
    
    public Object accept(Visitor v, Object param) {
    	return v.visit(this, param);
    }
}
