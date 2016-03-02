/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	strut:sentencia -> string:String  defvar:defVar*

public class Strut extends AbstractSentencia {

	public Strut(String string, List<DefVar> defvar) {
		this.string = string;
		this.defvar = defvar;

		searchForPositions(defvar);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Strut(Object string, Object defvar) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.defvar = (List<DefVar>) defvar;

		searchForPositions(string, defvar);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	public List<DefVar> getDefvar() {
		return defvar;
	}
	public void setDefvar(List<DefVar> defvar) {
		this.defvar = defvar;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
	private List<DefVar> defvar;
}

