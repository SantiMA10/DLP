/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	lintreal:expr -> string:String

public class Lintreal extends AbstractExpr {

	public Lintreal(String string) {
		this.string = string;
	}

	public Lintreal(Object string) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;

		searchForPositions(string);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
}

