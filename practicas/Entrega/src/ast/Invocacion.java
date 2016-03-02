/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	invocacion:sent_func, expr -> string:String  expr:expr*

public class Invocacion extends AbstractTraceable implements Sent_func, Expr {

	public Invocacion(String string, List<Expr> expr) {
		this.string = string;
		this.expr = expr;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Invocacion(Object string, Object expr) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.expr = (List<Expr>) expr;

		searchForPositions(string, expr);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	public List<Expr> getExpr() {
		return expr;
	}
	public void setExpr(List<Expr> expr) {
		this.expr = expr;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
	private List<Expr> expr;
}

