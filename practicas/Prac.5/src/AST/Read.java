package AST;

import Visitor.*;

public class Read implements Sentencia {
    public RefVar var;
 
    public Read(RefVar var) {
        this.var = var;
    }
    
    public Object accept(Visitor v, Object param) {
    	return v.visit(this, param);
    }
}
