/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	return:sent_func -> expr:expr

public class Return extends AbstractSent_func {

	public Return(Expr expr) {
		this.expr = expr;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
	}

	public Return(Object expr) {
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
	private Funcion funcion;
	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

}

