/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	if:sent_func -> expr:expr  verdadero:sent_func*  falso:sent_func*

public class If extends AbstractSent_func {

	public If(Expr expr, List<Sent_func> verdadero, List<Sent_func> falso) {
		this.expr = expr;
		this.verdadero = verdadero;
		this.falso = falso;

		searchForPositions(expr, verdadero, falso);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public If(Object expr, Object verdadero, Object falso) {
		this.expr = (Expr) expr;
		this.verdadero = (List<Sent_func>) verdadero;
		this.falso = (List<Sent_func>) falso;

		searchForPositions(expr, verdadero, falso);	// Obtener linea/columna a partir de los hijos
	}

	public Expr getExpr() {
		return expr;
	}
	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public List<Sent_func> getVerdadero() {
		return verdadero;
	}
	public void setVerdadero(List<Sent_func> verdadero) {
		this.verdadero = verdadero;
	}

	public List<Sent_func> getFalso() {
		return falso;
	}
	public void setFalso(List<Sent_func> falso) {
		this.falso = falso;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expr expr;
	private List<Sent_func> verdadero;
	private List<Sent_func> falso;
}

