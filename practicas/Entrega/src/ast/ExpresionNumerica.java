/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	expresionNumerica:expr -> izq:expr  string:String  der:expr

public class ExpresionNumerica extends AbstractExpr {

	public ExpresionNumerica(Expr izq, String string, Expr der) {
		this.izq = izq;
		this.string = string;
		this.der = der;

		searchForPositions(izq, der);	// Obtener linea/columna a partir de los hijos
	}

	public ExpresionNumerica(Object izq, Object string, Object der) {
		this.izq = (Expr) izq;
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.der = (Expr) der;

		searchForPositions(izq, string, der);	// Obtener linea/columna a partir de los hijos
	}

	public Expr getIzq() {
		return izq;
	}
	public void setIzq(Expr izq) {
		this.izq = izq;
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
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
	private String string;
	private Expr der;
}

