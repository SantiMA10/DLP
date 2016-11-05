/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	funcion:sentencia -> string:String  parametro:parametro*  defvar:defVar*  sent_func:sent_func*  tipo:tipo

public class Funcion extends AbstractSentencia {

	public Funcion(String string, List<Parametro> parametro, List<DefVar> defvar, List<Sent_func> sent_func, Tipo tipo) {
		this.string = string;
		this.parametro = parametro;
		this.defvar = defvar;
		this.sent_func = sent_func;
		this.tipo = tipo;

		searchForPositions(parametro, defvar, sent_func, tipo);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Funcion(Object string, Object parametro, Object defvar, Object sent_func, Object tipo) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.parametro = (List<Parametro>) parametro;
		this.defvar = (List<DefVar>) defvar;
		this.sent_func = (List<Sent_func>) sent_func;
		this.tipo = (Tipo) tipo;

		searchForPositions(string, parametro, defvar, sent_func, tipo);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	public List<Parametro> getParametro() {
		return parametro;
	}
	public void setParametro(List<Parametro> parametro) {
		this.parametro = parametro;
	}

	public List<DefVar> getDefvar() {
		return defvar;
	}
	public void setDefvar(List<DefVar> defvar) {
		this.defvar = defvar;
	}

	public List<Sent_func> getSent_func() {
		return sent_func;
	}
	public void setSent_func(List<Sent_func> sent_func) {
		this.sent_func = sent_func;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
	private List<Parametro> parametro;
	private List<DefVar> defvar;
	private List<Sent_func> sent_func;
	private Tipo tipo;
}

