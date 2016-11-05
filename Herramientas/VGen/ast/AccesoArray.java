/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	accesoArray:expr -> izq:expr  der:expr

public class AccesoArray extends AbstractExpr {

	public AccesoArray(Expr izq, Expr der) {
		this.izq = izq;
		this.der = der;

		searchForPositions(izq, der);	// Obtener linea/columna a partir de los hijos
	}

	public AccesoArray(Object izq, Object der) {
		this.izq = (Expr) izq;
		this.der = (Expr) der;

		searchForPositions(izq, der);	// Obtener linea/columna a partir de los hijos
	}

	public Expr getIzq() {
		return izq;
	}
	public void setIzq(Expr izq) {
		this.izq = izq;
	}

	public Expr getDer() {
		return der;
	}
	public void setDer(Expr der) {
		this.der = der;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expr izq;
	private Expr der;
}

