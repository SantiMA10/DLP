/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	while:sent_func -> expr:expr  sent_func:sent_func*

public class While extends AbstractSent_func {

	public While(Expr expr, List<Sent_func> sent_func) {
		this.expr = expr;
		this.sent_func = sent_func;

		searchForPositions(expr, sent_func);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public While(Object expr, Object sent_func) {
		this.expr = (Expr) expr;
		this.sent_func = (List<Sent_func>) sent_func;

		searchForPositions(expr, sent_func);	// Obtener linea/columna a partir de los hijos
	}

	public Expr getExpr() {
		return expr;
	}
	public void setExpr(Expr expr) {
		this.expr = expr;
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

	private Expr expr;
	private List<Sent_func> sent_func;
}

