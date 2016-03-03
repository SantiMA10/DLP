package Visitor;

import AST.*;

public class PrintVisitor implements Visitor {
	public Object visit(Programa programa, Object param) {
		for (AST nodo : programa.sentencias)
			nodo.accept(this, null);
		return null;
	}

	public Object visit(Print print, Object param) {
		System.out.print("print ");
		print.expr.accept(this, null);
		System.out.println(";");
		return null;
	}

	public Object visit(Read read, Object param) {
		System.out.print("read ");
		read.var.accept(this, null);
		System.out.println(";");
		return null;
	}

	public Object visit(Suma suma, Object param) {
		suma.left.accept(this, null);
		System.out.print(" + ");
		suma.right.accept(this, null);
		return null;
	}

	public Object visit(RefVar refVar, Object param) {
		System.out.print(refVar.name);
		return null;
	}

}
