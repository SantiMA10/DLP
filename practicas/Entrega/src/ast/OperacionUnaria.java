/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	operacionUnaria:expr -> string:String  der:expr

public class OperacionUnaria extends AbstractExpr {

	public OperacionUnaria(String string, Expr der) {
		this.string = string;
		this.der = der;

		searchForPositions(der);	// Obtener linea/columna a partir de los hijos
	}

	public OperacionUnaria(Object string, Object der) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.der = (Expr) der;

		searchForPositions(string, der);	// Obtener linea/columna a partir de los hijos
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

	private String string;
	private Expr der;
}

