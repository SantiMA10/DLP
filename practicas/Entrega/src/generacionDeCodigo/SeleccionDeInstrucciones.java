package generacionDeCodigo;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import visitor.DefaultVisitor;
import ast.AccesoArray;
import ast.AccesoStruct;
import ast.ArrayType;
import ast.Asignacion;
import ast.Cast;
import ast.CharType;
import ast.DefVar;
import ast.Expr;
import ast.ExpresionLogica;
import ast.ExpresionNumerica;
import ast.If;
import ast.IntType;
import ast.Invocacion;
import ast.Lintchar;
import ast.Lintent;
import ast.Lintreal;
import ast.OperacionUnaria;
import ast.Parametro;
import ast.Print;
import ast.Programa;
import ast.Read;
import ast.RealType;
import ast.Return;
import ast.Sent_func;
import ast.StructType;
import ast.Tipo;
import ast.Var;
import ast.While;

enum Funcion {
	DIRECCION, VALOR
}

public class SeleccionDeInstrucciones extends DefaultVisitor {

	private Map<String, String> instruccion = new HashMap<String, String>();
	private int contadorIfs = 0;
	private int contadorWhile = 0;

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
	
	//	class Programa { List<Sentencia> sentencia; }
	public Object visit(Programa node, Object param) {
		
		genera("CALL main");
		genera("HALT");

		super.visit(node, param);
		
		return null;
	
	}

	//	class Funcion { String string;  List<Parametro> parametro;  List<DefVar> defvar;  List<Sent_func> sent_func;  Tipo tipo; }
	public Object visit(ast.Funcion node, Object param) {

		// super.visit(node, param);

		int sizeLocales = 0;
		int sizeParametros = 0;

		genera(node.getString() + ":");

		for (Parametro child : node.getParametro()){
			sizeParametros += child.getTipo().getMemSize();
		}

		for (DefVar child : node.getDefvar()){
			sizeLocales += child.getTipo().getMemSize();
		}

		genera("ENTER " + sizeLocales);

		if (node.getSent_func() != null)
			for (Sent_func child : node.getSent_func())
				child.accept(this, param);

		if (node.getTipo() == null)
			genera("RET 0, " + sizeLocales + ", " + sizeParametros);

		return null;
	}

	//	class Return { Expr expr; }
	public Object visit(Return node, Object param) {

		// super.visit(node, param);

		int sizeLocales = 0;
		int sizeParametros = 0;

		for (Parametro child : node.getFuncion().getParametro()){
			sizeParametros += child.getTipo().getMemSize();
		}

		for (DefVar child : node.getFuncion().getDefvar()){
			sizeLocales += child.getTipo().getMemSize();
		}

		if (node.getExpr() == null)
			genera("RET 0, " + sizeLocales + ", " + sizeParametros);
		else{
			node.getExpr().accept(this, Funcion.VALOR);
			genera("RET " + node.getExpr().getTipo().getMemSize() + ", " + sizeLocales + ", " + sizeParametros);
		}

		return null;
	}

	//	class Invocacion { String nombre;  List<Expr> expr;  String ambito; }
	public Object visit(Invocacion node, Object param) {

		for(Expr child: node.getExpr()){
			child.accept(this, Funcion.VALOR);
		}
		genera("CALL " + node.getNombre());
		if(node.getAmbito().equals("llamada") && node.getTipo() != null){
			genera("POP");
		}

		return null;
	}

	//	class If { Expr expr;  List<Sent_func> verdadero;  List<Sent_func> falso; }
	public Object visit(If node, Object param) {

		int contadorIfs = this.contadorIfs;
		this.contadorIfs++;
		// super.visit(node, param);
		node.getExpr().accept(this, Funcion.VALOR);
		
		if(node.getFalso() != null){
			genera("JZ else" + contadorIfs);
		}
		else{
			genera("JZ finif" + contadorIfs);
		}
		
		for(int i = 0; i < node.getVerdadero().size(); i++){
			node.getVerdadero().get(i).accept(this, param);
			if(i == node.getVerdadero().size() -1 && !(node.getVerdadero().get(i) instanceof Return)){
				genera("JMP finif" + contadorIfs);
			}
		}

		if(node.getFalso() != null){
			genera("else"+contadorIfs+":");
			for (Sent_func child : node.getFalso())
				child.accept(this, param);
		}

		genera("finif" + contadorIfs + ":");
		

		return null;
	}

	//	class While { Expr expr;  List<Sent_func> sent_func; }
	public Object visit(While node, Object param) {

		int contadorWhile = this.contadorWhile;
		this.contadorWhile++;
		genera("while" + contadorWhile+":");
		node.getExpr().accept(this, Funcion.VALOR);
		genera("JZ finwhile" + contadorWhile);

		for (Sent_func child : node.getSent_func())
			child.accept(this, param);
		genera("JMP while"+contadorWhile);
		
		genera("finwhile"+contadorWhile+":");

		return null;
	}
	
	//	class Read { Expr expr; }
	public Object visit(Read node, Object param) {

		node.getExpr().accept(this, Funcion.DIRECCION);
		genera("IN");
		genera("STORE", node.getExpr().getTipo());

		return null;
	}

	//	class Print { Expr expr; }
	public Object visit(Print node, Object param) {

		node.getExpr().accept(this, Funcion.VALOR);
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

		genera(instruccion.get(node.getString()), node.getIzq().getTipo());

		return null;
	}

	//	class AccesoArray { Expr izq;  Expr der; }
	public Object visit(AccesoArray node, Object param) {

		if (node.getIzq() != null)
			node.getIzq().accept(this, Funcion.DIRECCION);
		genera("PUSH " + ((ArrayType)(node.getIzq().getTipo())).getTipo().getMemSize());
		if (node.getDer() != null){
			node.getDer().accept(this, Funcion.VALOR);
		}
		genera("MUL");
		genera("ADD");
		if(Funcion.VALOR.equals(param)){
			genera("LOAD", ((ArrayType)node.getIzq().getTipo()).getTipo());
		}

		return null;
	}

	//	class OperacionUnaria { String string;  Expr der; }
	public Object visit(OperacionUnaria node, Object param) {

		node.getDer().accept(this, Funcion.VALOR);
		if (node.getDer() != null && node.getString().equals("!")){
			genera("NOT");
		}

		return null;
	}

	//	class AccesoStruct { Expr struct;  String string; }
	public Object visit(AccesoStruct node, Object param) {

		node.getStruct().accept(this, Funcion.DIRECCION);
		//genera("PUSH " + ((Var)node.getStruct()).getDefinicion().getDireccion());
		List<DefVar> lista = ((StructType)(node.getStruct()).getTipo()).getDefinicion().getDefvar();
		Tipo tipo = null;
		for(DefVar var : lista){
			if(var.getNombre().equals(node.getString())){
				genera("PUSH " + var.getDireccion());
				tipo = var.getTipo();
			}
		}
		genera("ADD");
		if(Funcion.VALOR.equals(param)){
			genera("LOAD", tipo);
		}

		return null;
	}

	//	class Lintent { String string; }
	public Object visit(Lintent node, Object param) {
		genera("PUSH " + node.getString());

		return null;
	}

	//	class Lintreal { String string; }
	public Object visit(Lintreal node, Object param) {
		genera("PUSHF " + node.getString());

		return null;
	}

	//	class Lintchar { String string; }
	public Object visit(Lintchar node, Object param) {
		if("'\\n'".equals(node.getString())){
			genera("PUSHB 10");
		}
		else{
			genera("PUSHB " + (int)(node.getString().charAt(1)));
		}

		return null;
	}

	//	class Cast { Tipo tipo;  Expr expr; }
	public Object visit(Cast node, Object param) {

		if (node.getExpr() != null)
			node.getExpr().accept(this, Funcion.VALOR);

		if(node.getTipo() instanceof IntType && node.getExpr().getTipo() instanceof CharType){
			genera("b2i");
		}
		else if(node.getTipo() instanceof IntType && node.getExpr().getTipo() instanceof RealType){
			genera("f2i");
		}
		else if(node.getTipo() instanceof CharType && node.getExpr().getTipo() instanceof IntType){
			genera("i2b");
		}
		else if(node.getTipo() instanceof RealType && node.getExpr().getTipo() instanceof IntType){
			genera("i2f");
		}

		return null;
	}

	//	class Var { String string; }
	public Object visit(Var node, Object param) {

		if(Funcion.DIRECCION.equals(param)){
			if(node.getDefinicion().getAmbito().equals("var")){
				genera("PUSHA BP");
				genera("PUSH " + node.getDefinicion().getDireccion());
				genera("ADD");

			}
			else if(node.getDefinicion().getAmbito().equals("param")){
				genera("PUSHA BP");
				genera("PUSH " + node.getDefinicion().getParametro().getDireccion());
				genera("ADD");

			}
			else{
				genera("PUSHA " + node.getDefinicion().getDireccion());
			}
		}
		if(Funcion.VALOR.equals(param)){
			visit(node, Funcion.DIRECCION);
			genera("LOAD", node.getTipo());
		}

		return null;
	}

	// Método auxiliar recomendado -------------
	private void genera(String instruccion) {
		System.out.println(instruccion);
		writer.println(instruccion);
	}

	private void genera(String instruccion, Tipo tipo) {
		writer.println(instruccion + tipo.getSufijo());
	}

	private PrintWriter writer;
	private String sourceFile;
}
