package semantico;

import java.util.*;

import ast.*;
import main.*;
import visitor.*;


public class Identificacion extends DefaultVisitor {

	public Identificacion(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	//Para estructuras y funciones tabla hash
	//Para variables contextMap

	//	class DefVar { String string;  Tipo tipo; }
	public Object visit(DefVar node, Object param) {

		// super.visit(node, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

	//	class Struct { String string;  List<DefVar> defvar; }
	public Object visit(Struct node, Object param) {

		// super.visit(node, param);

		if (node.getDefvar() != null)
			for (DefVar child : node.getDefvar())
				child.accept(this, param);

		return null;
	}

	//	class Funcion { String string;  List<Parametro> parametro;  List<DefVar> defvar;  List<Sent_func> sent_func;  Tipo tipo; }
	public Object visit(Funcion node, Object param) {

		// super.visit(node, param);

		if (node.getParametro() != null)
			for (Parametro child : node.getParametro())
				child.accept(this, param);

		if (node.getDefvar() != null)
			for (DefVar child : node.getDefvar())
				child.accept(this, param);

		if (node.getSent_func() != null)
			for (Sent_func child : node.getSent_func())
				child.accept(this, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

	//	class StructType { String string; }
	public Object visit(StructType node, Object param) {
		return null;
	}

	//	class Invocacion { String string;  List<Expr> expr; }
	public Object visit(Invocacion node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			for (Expr child : node.getExpr())
				child.accept(this, param);

		return null;
	}

	//	class Var { String string; }
	public Object visit(Var node, Object param) {
		return null;
	}
	
	/**
	 * Método auxiliar opcional para ayudar a implementar los predicados de la Gramática Atribuida.
	 * 
	 * Ejemplo de uso:
	 * 	predicado(variables.get(nombre), "La variable no ha sido definida", expr.getStart());
	 * 	predicado(variables.get(nombre), "La variable no ha sido definida", null);
	 * 
	 * NOTA: El método getStart() indica la linea/columna del fichero fuente de donde se leyó el nodo.
	 * Si se usa VGen dicho método será generado en todos los nodos AST. Si no se quiere usar getStart() se puede pasar null.
	 * 
	 * @param condicion Debe cumplirse para que no se produzca un error
	 * @param mensajeError Se imprime si no se cumple la condición
	 * @param posicionError Fila y columna del fichero donde se ha producido el error. Es opcional (acepta null)
	 */
	private void predicado(boolean condicion, String mensajeError, Position posicionError) {
		if (!condicion)
			gestorErrores.error("Identificación", mensajeError, posicionError);
	}


	private GestorErrores gestorErrores;
	private ContextMap<String, DefVar> variables = new ContextMap<>();
	private Map<String, Funcion> funciones = new HashMap<>();
	private Map<String, Struct> estructuras = new HashMap<>();
	
}
