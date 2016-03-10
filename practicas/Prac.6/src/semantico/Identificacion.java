package semantico;

import java.util.*;

import ast.*;
import main.*;
import visitor.*;

/**
 * @author Raúl Izquierdo
 */
public class Identificacion extends DefaultVisitor {

	public Identificacion(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}


	
	//	class DefVariable { Tipo tipo;  String nombre; }
	public Object visit(DefVariable node, Object param) {
		DefVariable definicion = variables.get(node.getNombre());
		predicado(definicion == null, "Variable ya definida: " + node.getNombre(), node.getStart());
		variables.put(node.getNombre(), node);
		return null;
	}

	//	class Variable { String nombre; }
	public Object visit(Variable node, Object param) {
		DefVariable definicion = variables.get(node.getNombre());
		predicado(definicion != null, "Variable no definida: " + node.getNombre(), node.getStart());
		node.setDefinicion(definicion); // Enlazar referencia con definición
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
	private Map<String, DefVariable> variables = new HashMap<String, DefVariable>();

}
