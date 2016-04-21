package generacionDeCodigo;

import java.io.*;
import java.util.*;

import ast.*;
import visitor.*;

/**
 * @author Raul Izquierdo
 */

enum Funcion {
	DIRECCION, VALOR
}

public class SeleccionDeInstrucciones extends DefaultVisitor {

	public SeleccionDeInstrucciones(Writer writer, String sourceFile) {
		this.writer = new PrintWriter(writer);
		this.sourceFile = sourceFile;

		instruccion.put("+", "add");
		instruccion.put("-", "sub");
		instruccion.put("*", "mul");
		instruccion.put("/", "div");
	}

	// class Programa { List<DefVariable> definiciones; List<Sentencia> sentencias; }
	public Object visit(Programa node, Object param) {
		genera("#source \"" + sourceFile + "\"");
		visitChildren(node.getDefiniciones(), param);
		visitChildren(node.getSentencias(), param);
		genera("halt");
		return null;
	}

	// class DefVariable { Tipo tipo; String nombre; }
	public Object visit(DefVariable node, Object param) {
		genera("#VAR " + node.getNombre() + ":" + node.getTipo().getNombreMAPL());
		return null;
	}

	// class Print { Expresion expresion; }
	public Object visit(Print node, Object param) {
		genera("#line " + node.getEnd().getLine());
		node.getExpresion().accept(this, Funcion.VALOR);
		genera("out", node.getExpresion().getTipo());
		return null;
	}

	// class Asigna { Expresion left; Expresion right; }
	public Object visit(Asigna node, Object param) {
		genera("#line " + node.getEnd().getLine());
		node.getLeft().accept(this, Funcion.DIRECCION);
		node.getRight().accept(this, Funcion.VALOR);
		genera("store", node.getLeft().getTipo());

		return null;
	}

	// class ExprAritmetica { Expresion left; String operador; Expresion right; }
	public Object visit(ExprAritmetica node, Object param) {
		assert (param == Funcion.VALOR);
		node.getLeft().accept(this, Funcion.VALOR);
		node.getRight().accept(this, Funcion.VALOR);
		genera(instruccion.get(node.getOperador()), node.getTipo());
		return null;
	}

	// class Variable { String nombre; }
	public Object visit(Variable node, Object param) {
		if (((Funcion) param) == Funcion.VALOR) {
			visit(node, Funcion.DIRECCION);
			genera("load", node.getDefinicion().getTipo());
		} else { // Funcion.DIRECCION
			assert (param == Funcion.DIRECCION);
			genera("pusha " + node.getDefinicion().getDireccion());
		}
		return null;
	}

	// class LiteralInt { String valor; }
	public Object visit(LiteralInt node, Object param) {
		assert (param == Funcion.VALOR);
		genera("push " + node.getValor());
		return null;
	}

	// class LiteralReal { String valor; }
	public Object visit(LiteralReal node, Object param) {
		assert (param == Funcion.VALOR);
		genera("pushf " + node.getValor());
		return null;
	}

	private Map<String, String> instruccion = new HashMap<String, String>();

	
	// Método auxiliar recomendado -------------
	private void genera(String instruccion) {
		writer.println(instruccion);
	}

	private void genera(String instruccion, Tipo tipo) {
		genera(instruccion + tipo.getSufijo());
	}

	private PrintWriter writer;
	private String sourceFile;
}
