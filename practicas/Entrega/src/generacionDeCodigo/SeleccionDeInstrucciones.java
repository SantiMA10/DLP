package generacionDeCodigo;

import java.io.*;
import java.util.*;

import ast.*;
import visitor.*;

enum Funcion {
	DIRECCION, VALOR
}

public class SeleccionDeInstrucciones extends DefaultVisitor {
	
	private Map<String, String> instruccion = new HashMap<String, String>();

	public SeleccionDeInstrucciones(Writer writer, String sourceFile) {
		this.writer = new PrintWriter(writer);
		this.sourceFile = sourceFile;
		
		instruccion.put("+", "ADD");
		instruccion.put("-", "SUB");
		instruccion.put("*", "MUL");
		instruccion.put("/", "DIV");
		instruccion.put("&&", "AND");
		instruccion.put("||", "OR");
		
		instruccion.put("<", "LT");
		instruccion.put(">", "GT");
		instruccion.put("==", "EQ");
		instruccion.put("!=", "NE");
		instruccion.put(">=", "GE");
		instruccion.put("<=", "LE");
		
	}

	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	//	class Print { Expr expr; }
	public Object visit(Print node, Object param) {
		
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		genera("OUT", node.getExpr().getTipo());

		return null;
	}

	//	class Asignacion { Expr izq;  Expr der; }
	public Object visit(Asignacion node, Object param) {

		// super.visit(node, param);

		if (node.getIzq() != null)
			node.getIzq().accept(this, Funcion.DIRECCION);

		if (node.getDer() != null)
			node.getDer().accept(this, Funcion.VALOR);
		
		genera("STORE", node.getDer().getTipo());

		return null;
	}

	//	class ExpresionLogica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionLogica node, Object param) {

		if (node.getIzq() != null)
			node.getIzq().accept(this, Funcion.VALOR);

		if (node.getDer() != null)
			node.getDer().accept(this, Funcion.VALOR);
		
		genera(instruccion.get(node.getString()));

		return null;
	}

	//	class ExpresionNumerica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionNumerica node, Object param) {

		if (node.getIzq() != null)
			node.getIzq().accept(this, Funcion.VALOR);

		if (node.getDer() != null)
			node.getDer().accept(this, Funcion.VALOR);
		
		genera(instruccion.get(node.getString()));

		return null;
	}

	//	class AccesoArray { Expr izq;  Expr der; }
	public Object visit(AccesoArray node, Object param) {

		// super.visit(node, param);

		if (node.getIzq() != null)
			node.getIzq().accept(this, param);

		if (node.getDer() != null)
			node.getDer().accept(this, param);

		return null;
	}

	//	class OperacionUnaria { String string;  Expr der; }
	public Object visit(OperacionUnaria node, Object param) {

		// super.visit(node, param);

		if (node.getDer() != null)
			node.getDer().accept(this, param);

		return null;
	}

	//	class AccesoStruct { Expr struct;  String string; }
	public Object visit(AccesoStruct node, Object param) {

		// super.visit(node, param);

		if (node.getStruct() != null)
			node.getStruct().accept(this, param);

		return null;
	}

	//	class Lintent { String string; }
	public Object visit(Lintent node, Object param) {
		genera("PUSH" + node.getString());
		
		return null;
	}

	//	class Lintreal { String string; }
	public Object visit(Lintreal node, Object param) {
		genera("PUSHF" + node.getString());
		
		return null;
	}

	//	class Lintchar { String string; }
	public Object visit(Lintchar node, Object param) {
		genera("PUSHB" + node.getString());
		
		return null;
	}

	//	class Cast { Tipo tipo;  Expr expr; }
	public Object visit(Cast node, Object param) {

		// super.visit(node, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		return null;
	}

	//	class Var { String string; }
	public Object visit(Var node, Object param) {
		
		if(Funcion.DIRECCION.equals(param)){
			genera("PUSHA " + node.getDefinicion().getDireccion());
		}
		if(Funcion.VALOR.equals(param)){
			visit(node, Funcion.DIRECCION);
			genera("LOAD", node.getTipo());
		}
		
		return null;
	}

	// Método auxiliar recomendado -------------
	private void genera(String instruccion) {
		writer.println(instruccion);
	}
	
	private void genera(String instruccion, Tipo tipo) {
		writer.println(instruccion + tipo.getSufijo());
	}

	private PrintWriter writer;
	private String sourceFile;
}
