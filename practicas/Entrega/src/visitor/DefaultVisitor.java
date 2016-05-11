/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;
import java.util.*;

/*
DefaultVisitor. Implementación base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementación por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Programa { List<Sentencia> sentencia; }
	public Object visit(Programa node, Object param) {
		visitChildren(node.getSentencia(), param);
		return null;
	}

	//	class DefVar { String nombre;  Tipo tipo;  String ambito; }
	public Object visit(DefVar node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class Struct { String string;  List<DefVar> defvar; }
	public Object visit(Struct node, Object param) {
		visitChildren(node.getDefvar(), param);
		return null;
	}

	//	class Funcion { String string;  List<Parametro> parametro;  List<DefVar> defvar;  List<Sent_func> sent_func;  Tipo tipo; }
	public Object visit(Funcion node, Object param) {
		visitChildren(node.getParametro(), param);
		visitChildren(node.getDefvar(), param);
		visitChildren(node.getSent_func(), param);
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
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class Parametro { String string;  Tipo tipo; }
	public Object visit(Parametro node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class If { Expr expr;  List<Sent_func> verdadero;  List<Sent_func> falso; }
	public Object visit(If node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		visitChildren(node.getVerdadero(), param);
		visitChildren(node.getFalso(), param);
		return null;
	}

	//	class While { Expr expr;  List<Sent_func> sent_func; }
	public Object visit(While node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		visitChildren(node.getSent_func(), param);
		return null;
	}

	//	class For { Asignacion i;  Expr condicion;  Asignacion cambio;  List<Sent_func> sent_func; }
	public Object visit(For node, Object param) {
		if (node.getI() != null)
			node.getI().accept(this, param);
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		if (node.getCambio() != null)
			node.getCambio().accept(this, param);
		visitChildren(node.getSent_func(), param);
		return null;
	}

	//	class Print { Expr expr; }
	public Object visit(Print node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class Read { Expr expr; }
	public Object visit(Read node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class Asignacion { Expr izq;  List<Expr> der; }
	public Object visit(Asignacion node, Object param) {
		if (node.getIzq() != null)
			node.getIzq().accept(this, param);
		visitChildren(node.getDer(), param);
		return null;
	}

	//	class Invocacion { String nombre;  List<Expr> expr;  String ambito; }
	public Object visit(Invocacion node, Object param) {
		visitChildren(node.getExpr(), param);
		return null;
	}

	//	class Return { Expr expr; }
	public Object visit(Return node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class ExpresionLogica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionLogica node, Object param) {
		if (node.getIzq() != null)
			node.getIzq().accept(this, param);
		if (node.getDer() != null)
			node.getDer().accept(this, param);
		return null;
	}

	//	class ExpresionNumerica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionNumerica node, Object param) {
		if (node.getIzq() != null)
			node.getIzq().accept(this, param);
		if (node.getDer() != null)
			node.getDer().accept(this, param);
		return null;
	}

	//	class AccesoArray { Expr izq;  Expr der; }
	public Object visit(AccesoArray node, Object param) {
		if (node.getIzq() != null)
			node.getIzq().accept(this, param);
		if (node.getDer() != null)
			node.getDer().accept(this, param);
		return null;
	}

	//	class OperacionUnaria { String string;  Expr der; }
	public Object visit(OperacionUnaria node, Object param) {
		if (node.getDer() != null)
			node.getDer().accept(this, param);
		return null;
	}

	//	class AccesoStruct { Expr struct;  String string; }
	public Object visit(AccesoStruct node, Object param) {
		if (node.getStruct() != null)
			node.getStruct().accept(this, param);
		return null;
	}

	//	class Lintent { String string; }
	public Object visit(Lintent node, Object param) {
		return null;
	}

	//	class Lintreal { String string; }
	public Object visit(Lintreal node, Object param) {
		return null;
	}

	//	class Lintchar { String string; }
	public Object visit(Lintchar node, Object param) {
		return null;
	}

	//	class Cast { Tipo tipo;  Expr expr; }
	public Object visit(Cast node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class Var { String string; }
	public Object visit(Var node, Object param) {
		return null;
	}
	
	// Método auxiliar -----------------------------
	protected void visitChildren(List<? extends AST> children, Object param) {
		if (children != null)
			for (AST child : children)
				child.accept(this, param);
	}
}
