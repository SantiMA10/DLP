package AST;

import Visitor.*;

public class Suma implements Expresion {
	public Expresion left, right;

	public Suma(Expresion left, Expresion right) {
		this.left = left;
		this.right = right;
	}

	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
