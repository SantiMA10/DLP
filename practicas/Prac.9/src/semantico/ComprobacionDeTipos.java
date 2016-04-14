package semantico;

import ast.*;
import main.*;
import visitor.*;

/**
 * @author Ra�l Izquierdo
 */
public class ComprobacionDeTipos extends DefaultVisitor {

	public ComprobacionDeTipos(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	
	// class Asigna { Expresion left; Expresion right; }
	public Object visit(Asigna node, Object param) {
		super.visit(node, param);
		predicado(mismoTipo(node.getLeft(), node.getRight()), "Los operandos deben ser del mismo tipo", node.getStart());
		predicado(node.getLeft().isModificable(), "Se requiere expresi�n modificable", node.getLeft().getStart());
		return null;
	}

	// class ExprAritmetica { Expresion left; String operador; Expresion right; }
	public Object visit(ExprAritmetica node, Object param) {
		super.visit(node, param);
		predicado(mismoTipo(node.getLeft(), node.getRight()), "Los operandos deben ser del mismo tipo", node.getStart());

		node.setTipo(node.getLeft().getTipo());
		node.setModificable(false);
		return null;
	}

	// class Variable { String nombre; }
	public Object visit(Variable node, Object param) {
		node.setTipo(node.getDefinicion().getTipo());
		node.setModificable(true);
		return null;
	}

	// class LiteralInt { String valor; }
	public Object visit(LiteralInt node, Object param) {
		node.setTipo(new IntType());
		node.setModificable(false);
		return null;
	}

	// class LiteralReal { String valor; }
	public Object visit(LiteralReal node, Object param) {
		node.setTipo(new RealType());
		node.setModificable(false);
		return null;
	}
	
	// --------------------------------------------------------
	// Funciones auxiliares

	private boolean mismoTipo(Expresion a, Expresion b) {
		return (a.getTipo().getClass() == b.getTipo().getClass());
	}

	
	/**
	 * M�todo auxiliar opcional para ayudar a implementar los predicados de la Gram�tica Atribuida.
	 * 
	 * Ejemplo de uso (suponiendo implementado el m�todo "esPrimitivo"):
	 * 	predicado(esPrimitivo(expr.tipo), "La expresi�n debe ser de un tipo primitivo", expr.getStart());
	 * 	predicado(esPrimitivo(expr.tipo), "La expresi�n debe ser de un tipo primitivo", null);
	 * 
	 * NOTA: El m�todo getStart() indica la linea/columna del fichero fuente de donde se ley� el nodo.
	 * Si se usa VGen dicho m�todo ser� generado en todos los nodos AST. Si no se quiere usar getStart() se puede pasar null.
	 * 
	 * @param condicion Debe cumplirse para que no se produzca un error
	 * @param mensajeError Se imprime si no se cumple la condici�n
	 * @param posicionError Fila y columna del fichero donde se ha producido el error. Es opcional (acepta null)
	 */
	private void predicado(boolean condicion, String mensajeError, Position posicionError) {
		if (!condicion)
			gestorErrores.error("Comprobaci�n de tipos", mensajeError, posicionError);
	}
	
	
	private GestorErrores gestorErrores;
}
