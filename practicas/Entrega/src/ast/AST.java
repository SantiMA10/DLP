package ast;

import visitor.*;

/*
 *  Esta clase se completar� en la fase de An�lisis Sint�ctico
 */
public interface AST {
	public Object accept(Visitor visitor, Object param);
}

