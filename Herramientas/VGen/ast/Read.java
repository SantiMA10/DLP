/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	read:sent_func -> expr:expr

public class Read extends AbstractSent_func {

	public Read(Expr expr) {
		this.expr = expr;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
	}

	public Read(Object expr) {
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

