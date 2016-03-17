/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	var:expr -> string:String

public class Var extends AbstractExpr {

	public Var(String string) {
		this.string = string;
	}

	public Var(Object string) {
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
	private DefVar definicion;

	public void setDefinicion(DefVar ref) {
		this.definicion = ref;
		
	}

	public DefVar getDefinicion() {
		return definicion;
	}
	
	
}

