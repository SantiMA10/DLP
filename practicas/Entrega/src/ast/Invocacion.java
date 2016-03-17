/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	invocacion:sent_func, expr -> nombre:String  expr:expr*  ambito:String

public class Invocacion extends AbstractTraceable implements Sent_func, Expr {

	public Invocacion(String nombre, List<Expr> expr, String ambito) {
		this.nombre = nombre;
		this.expr = expr;
		this.ambito = ambito;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Invocacion(Object nombre, Object expr, Object ambito) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getLexeme() : (String) nombre;
		this.expr = (List<Expr>) expr;
		this.ambito = (ambito instanceof Token) ? ((Token)ambito).getLexeme() : (String) ambito;

		searchForPositions(nombre, expr, ambito);	// Obtener linea/columna a partir de los hijos
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Expr> getExpr() {
		return expr;
	}
	public void setExpr(List<Expr> expr) {
		this.expr = expr;
	}

	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
	private List<Expr> expr;
	private String ambito;
	private Funcion definicion;
	
	public void setDefinicion(Funcion ref) {
		this.definicion = ref;
		
	}

	public Funcion getDefinicion() {
		return definicion;
	}
}

