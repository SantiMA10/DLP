/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	arrayType:tipo -> tipo:tipo  size:int

public class ArrayType extends AbstractTipo {

	public ArrayType(Tipo tipo, int size) {
		this.tipo = tipo;
		this.size = size;

		searchForPositions(tipo);	// Obtener linea/columna a partir de los hijos
	}

	public ArrayType(Object tipo, Object size) {
		this.tipo = (Tipo) tipo;
		this.size = (size instanceof Token) ? Integer.parseInt(((Token)size).getLexeme()) : (Integer) size;

		searchForPositions(tipo, size);	// Obtener linea/columna a partir de los hijos
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo tipo;
	private int size;
	
	@Override
	public int getMemSize() {
		return tipo.getMemSize()*size;
	}
	
}

