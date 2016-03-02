/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	defVariable -> tipo:tipo  nombre:String

public class DefVariable extends AbstractTraceable implements AST {

	public DefVariable(Tipo tipo, String nombre) {
		this.tipo = tipo;
		this.nombre = nombre;

		searchForPositions(tipo);	// Obtener linea/columna a partir de los hijos
	}

	public DefVariable(Object tipo, Object nombre) {
		this.tipo = (Tipo) tipo;
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getLexeme() : (String) nombre;

		searchForPositions(tipo, nombre);	// Obtener linea/columna a partir de los hijos
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo tipo;
	private String nombre;
}

