/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	accesoStruct:expr -> struct:expr  string:String

public class AccesoStruct extends AbstractExpr {

	public AccesoStruct(Expr struct, String string) {
		this.struct = struct;
		this.string = string;

		searchForPositions(struct);	// Obtener linea/columna a partir de los hijos
	}

	public AccesoStruct(Object struct, Object string) {
		this.struct = (Expr) struct;
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;

		searchForPositions(struct, string);	// Obtener linea/columna a partir de los hijos
	}

	public Expr getStruct() {
		return struct;
	}
	public void setStruct(Expr struct) {
		this.struct = struct;
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

	private Expr struct;
	private String string;
}

