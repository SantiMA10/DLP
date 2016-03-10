/**
 * @generated VGen 1.3.2
 */

package ast;

import visitor.*;

//	literalInt:expresion -> valor:String

public class LiteralInt extends AbstractExpresion {

	public LiteralInt(String valor) {
		this.valor = valor;
	}

	public LiteralInt(Object valor) {
		this.valor = (valor instanceof Token) ? ((Token)valor).getLexeme() : (String) valor;

		searchForPositions(valor);	// Obtener linea/columna a partir de los hijos
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String valor;
}

