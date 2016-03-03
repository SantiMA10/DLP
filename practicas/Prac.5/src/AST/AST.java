package AST;

import Visitor.*;

public interface AST {
    public Object accept(Visitor v, Object param);
}
