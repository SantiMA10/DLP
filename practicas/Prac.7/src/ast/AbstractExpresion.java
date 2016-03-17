/**
 * @generated VGen 1.3.2
 */

package ast;

public abstract class AbstractExpresion extends AbstractTraceable implements Expresion {
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}

	public boolean isModificable() {
		return modificable;
	}

	private Tipo tipo;
	private boolean modificable;

}
