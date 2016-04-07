package semantico;

import ast.*;
import main.*;
import visitor.*;

public class ComprobacionDeTipos extends DefaultVisitor {

	public ComprobacionDeTipos(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	/*
	 * Poner aqu� los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

//	class Programa { List<Sentencia> sentencia; }
	public Object visit(Programa node, Object param) {

		// super.visit(node, param);

		if (node.getSentencia() != null)
			for (Sentencia child : node.getSentencia())
				child.accept(this, param);

		return null;
	}

	//	class DefVar { String nombre;  Tipo tipo;  String ambito; }
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
			for (Sent_func child : node.getSent_func()){
				child.accept(this, param);
				if(child instanceof Return){
					((Return)child).setFuncion(node);
				}
			}

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

	//	class IntType {  }
	public Object visit(IntType node, Object param) {
		return null;
	}

	//	class RealType {  }
	public Object visit(RealType node, Object param) {
		return null;
	}

	//	class CharType {  }
	public Object visit(CharType node, Object param) {
		return null;
	}

	//	class StructType { String string; }
	public Object visit(StructType node, Object param) {
		return null;
	}

	//	class ArrayType { Tipo tipo;  int size; }
	public Object visit(ArrayType node, Object param) {

		// super.visit(node, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

	//	class Parametro { String string;  Tipo tipo; }
	public Object visit(Parametro node, Object param) {

		// super.visit(node, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

	//	class If { Expr expr;  List<Sent_func> verdadero;  List<Sent_func> falso; }
	public Object visit(If node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		if (node.getVerdadero() != null)
			for (Sent_func child : node.getVerdadero())
				child.accept(this, param);

		if (node.getFalso() != null)
			for (Sent_func child : node.getFalso())
				child.accept(this, param);

		return null;
	}

	//	class While { Expr expr;  List<Sent_func> sent_func; }
	public Object visit(While node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		if (node.getSent_func() != null)
			for (Sent_func child : node.getSent_func())
				child.accept(this, param);

		return null;
	}

	//	class Print { Expr expr; }
	public Object visit(Print node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		return null;
	}

	//	class Read { Expr expr; }
	public Object visit(Read node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		return null;
	}

	//	class Asignacion { Expr izq;  Expr der; }
	public Object visit(Asignacion node, Object param) {

		// super.visit(node, param);

		if (node.getIzq() != null)
			node.getIzq().accept(this, param);

		if (node.getDer() != null)
			node.getDer().accept(this, param);

		return null;
	}

	//	class Invocacion { String nombre;  List<Expr> expr;  String ambito; }
	public Object visit(Invocacion node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			for (Expr child : node.getExpr())
				child.accept(this, param);

		return null;
	}

	//	class Return { Expr expr; }
	public Object visit(Return node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		return null;
	}

	//	class ExpresionLogica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionLogica node, Object param) {

		// super.visit(node, param);

		if (node.getIzq() != null)
			node.getIzq().accept(this, param);

		if (node.getDer() != null)
			node.getDer().accept(this, param);

		return null;
	}

	//	class ExpresionNumerica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionNumerica node, Object param) {

		// super.visit(node, param);

		if (node.getIzq() != null)
			node.getIzq().accept(this, param);

		if (node.getDer() != null)
			node.getDer().accept(this, param);

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

		predicado(node.getStruct().getTipo() instanceof StructType, 
				"Debe ser un nombre correcto", node.getStart()); 
		super.visit(node, param);

		return null;
	}

	//	class Lintent { String string; }
	public Object visit(Lintent node, Object param) {
		
		node.setModificable(false);
		node.setTipo(new IntType());
		return null;
	}

	//	class Lintreal { String string; }
	public Object visit(Lintreal node, Object param) {
		
		node.setModificable(false);
		node.setTipo(new RealType());
		return null;
	}

	//	class Lintchar { String string; }
	public Object visit(Lintchar node, Object param) {
		
		node.setModificable(false);
		node.setTipo(new CharType());
		return null;
	}

	//	class Cast { Tipo tipo;  Expr expr; }
	public Object visit(Cast node, Object param) {
		
		node.setModificable(false);
		predicado(node.getTipo().equals(node.getExpr().getTipo()), 
				"No se puede hacer cast al mismo tipo", node.getStart());
		
		super.visit(node, param);

		return null;
	}

	//	class Var { String string; }
	public Object visit(Var node, Object param) {
		
		node.setModificable(true);
		node.setTipo(node.getDefinicion().getTipo());
		
		return null;
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
	
	private boolean simple(Tipo tipo){
		return tipo instanceof CharType || tipo instanceof IntType || tipo instanceof RealType;
	}
	
	
	private GestorErrores gestorErrores;
}
