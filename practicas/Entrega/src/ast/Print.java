/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	print:sent_func -> expr:expr

public class Print extends AbstractSent_func {

	public Print(Expr expr) {
		this.expr = expr;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
	}

	public Print(Object expr) {
		this.expr = (Expr) expr;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
	}

	public Expr getExpr() {
		return expr;
	}
	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expr expr;
}

