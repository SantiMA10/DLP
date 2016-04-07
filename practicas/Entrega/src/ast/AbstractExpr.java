/**
 * @generated VGen 1.3.3
 */

package ast;

public abstract class AbstractExpr extends AbstractTraceable implements Expr {
	
	private Tipo tipo;
	private Boolean modificable;
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Boolean getModificable() {
		return modificable;
	}
	
	public void setModificable(Boolean modificable) {
		this.modificable = modificable;
	}	

}

