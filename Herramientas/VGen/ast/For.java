/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	for:sent_func -> i:asignacion  condicion:expr  cambio:asignacion  sent_func:sent_func*

public class For extends AbstractSent_func {

	public For(Asignacion i, Expr condicion, Asignacion cambio, List<Sent_func> sent_func) {
		this.i = i;
		this.condicion = condicion;
		this.cambio = cambio;
		this.sent_func = sent_func;

		searchForPositions(i, condicion, cambio, sent_func);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public For(Object i, Object condicion, Object cambio, Object sent_func) {
		this.i = (Asignacion) i;
		this.condicion = (Expr) condicion;
		this.cambio = (Asignacion) cambio;
		this.sent_func = (List<Sent_func>) sent_func;

		searchForPositions(i, condicion, cambio, sent_func);	// Obtener linea/columna a partir de los hijos
	}

	public Asignacion getI() {
		return i;
	}
	public void setI(Asignacion i) {
		this.i = i;
	}

	public Expr getCondicion() {
		return condicion;
	}
	public void setCondicion(Expr condicion) {
		this.condicion = condicion;
	}

	public Asignacion getCambio() {
		return cambio;
	}
	public void setCambio(Asignacion cambio) {
		this.cambio = cambio;
	}

	public List<Sent_func> getSent_func() {
		return sent_func;
	}
	public void setSent_func(List<Sent_func> sent_func) {
		this.sent_func = sent_func;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Asignacion i;
	private Expr condicion;
	private Asignacion cambio;
	private List<Sent_func> sent_func;
}

