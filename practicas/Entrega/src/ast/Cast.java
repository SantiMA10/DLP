/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	cast:expr -> tipo:tipo  expr:expr

public class Cast extends AbstractExpr {

	public Cast(Tipo tipo, Expr expr) {
		this.tipo = tipo;
		this.expr = expr;

		searchForPositions(tipo, expr);	// Obtener linea/columna a partir de los hijos
	}

	public Cast(Object tipo, Object expr) {
		this.tipo = (Tipo) tipo;
		this.expr = (Expr) expr;

		searchForPositions(tipo, expr);	// Obtener linea/columna a partir de los hijos
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
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

	private Tipo tipo;
	private Expr expr;
}

