package semantico;

import ast.*;
import main.*;
import visitor.*;

public class ComprobacionDeTipos extends DefaultVisitor {

	public ComprobacionDeTipos(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	//	class Funcion { String string;  List<Parametro> parametro;  List<DefVar> defvar;  List<Sent_func> sent_func;  Tipo tipo; }
	public Object visit(Funcion node, Object param) {

		// super.visit(node, param);

		if (node.getParametro() != null)
			for (Parametro child : node.getParametro()){
				child.accept(this, param);
				predicado(simple(child.getTipo()), "Los parametros deben ser simples", node.getStart());
			}
			

		if (node.getDefvar() != null)
			for (DefVar child : node.getDefvar())
				child.accept(this, param);

		if (node.getSent_func() != null)
			for (Sent_func child : node.getSent_func()){
				child.setFuncion(node);
				child.accept(this, param);
			}

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		
		if(node.getTipo() != null)
			predicado(simple(node.getTipo()), "El tipo de retorno debe ser simple", node.getStart());
		

		return null;
	}

	//	class If { Expr expr;  List<Sent_func> verdadero;  List<Sent_func> falso; }
	public Object visit(If node, Object param) {

		// super.visit(node, param);

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		if (node.getVerdadero() != null)
			for (Sent_func child : node.getVerdadero()){
				child.setFuncion(node.getFuncion());
				child.accept(this, param);
			}

		if (node.getFalso() != null)
			for (Sent_func child : node.getFalso()){
				child.setFuncion(node.getFuncion());
				child.accept(this, param);
			}
		
		predicado(node.getExpr().getTipo() instanceof IntType,
				"El tipo de la condición debe ser Int", node.getStart());

		return null;
	}

	//	class While { Expr expr;  List<Sent_func> sent_func; }
	public Object visit(While node, Object param) {

		if (node.getExpr() != null)
			node.getExpr().accept(this, param);

		if (node.getSent_func() != null)
			for (Sent_func child : node.getSent_func()){
				child.setFuncion(node.getFuncion());
				child.accept(this, param);
			}
		
		predicado(node.getExpr().getTipo() instanceof IntType,
				"El tipo de la condición debe ser Int", node.getStart());

		return null;
	}

	//	class Print { Expr expr; }
	public Object visit(Print node, Object param) {

		super.visit(node, param);

		predicado(simple(node.getExpr().getTipo()), "Debe ser un tipo simple", node.getStart());

		return null;
	}

	//	class Read { Expr expr; }
	public Object visit(Read node, Object param) {

		super.visit(node, param);

		predicado(simple(node.getExpr().getTipo()), "Debe ser un tipo simple", node.getStart());
		predicado(node.getExpr().getModificable(), "Debe ser moficable para read", node.getStart());

		return null;
	}

	//	class Asignacion { Expr izq;  Expr der; }
	public Object visit(Asignacion node, Object param) {

		super.visit(node, param);

		predicado(node.getIzq().getModificable(), "La variable debe ser modificable", node.getStart());
		predicado(simple(node.getIzq().getTipo()), "La variable debe ser simple", node.getStart());
		predicado(isIgualTipo(node.getIzq().getTipo(), node.getDer().getTipo()),
				"Los tipos debe coincidir [" + node.getIzq().getTipo() +", " + node.getDer().getTipo() + "]", node.getStart());
		
		return null;
	}

	//	class Invocacion { String nombre;  List<Expr> expr;  String ambito; }
	public Object visit(Invocacion node, Object param) {

		super.visit(node, param);
		
		predicado(node.getDefinicion().getParametro().size() == node.getExpr().size(),
				"Debe coincidir el numero de parametros [" + node.getDefinicion().getParametro().size() +
				", " + node.getExpr().size() + "]", node.getStart());
		
		if(node.getDefinicion().getParametro().size() == node.getExpr().size()){
			for(int i = 0; i < node.getExpr().size(); i++){
				predicado(isIgualTipo(
							node.getDefinicion().getParametro().get(i).getTipo(), 
							node.getExpr().get(i).getTipo()),
						"Deben coincidir los tipos de los parametros", node.getStart());
			}
		}
		
		
		node.setTipo(node.getDefinicion().getTipo());
		if(!node.getAmbito().equals("llamada")){
			node.setModificable(false);
		}

		return null;
	}

	//	class Return { Expr expr; }
	public Object visit(Return node, Object param) {

		super.visit(node, param);
		
		if(node.getFuncion().getTipo() == null){
			predicado(node.getExpr() == null, "La funcion no debe tener return", node.getStart());
		}
		else{
			if(node.getExpr() != null){
				predicado(isIgualTipo(node.getExpr().getTipo(), node.getFuncion().getTipo()),
						"El tipo de retorno debe coincidir", node.getStart());
			}
			else{
				predicado(node.getFuncion().getTipo() == null,
						"La funcion debe tener un retorno", node.getStart());
			}
		}

		return null;
	}

	//	class ExpresionLogica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionLogica node, Object param) {

		super.visit(node, param);

		predicado((node.getDer().getTipo() instanceof IntType) &&
				(node.getIzq().getTipo() instanceof IntType), 
					"Las operaciones logicas solo son para Ints", node.getStart());
		
		node.setModificable(true);
		node.setTipo(node.getDer().getTipo());

		return null;
	}

	//	class ExpresionNumerica { Expr izq;  String string;  Expr der; }
	public Object visit(ExpresionNumerica node, Object param) {

		super.visit(node, param);
		
		predicado(!(node.getDer().getTipo() instanceof CharType), 
					"No se puede operar con chars", node.getStart());
		
		predicado(isIgualTipo(node.getDer().getTipo(), node.getIzq().getTipo()),
				"Los dos operandos deben ser del mismo tipo", node.getStart());
		
		node.setModificable(false);
		node.setTipo(node.getDer().getTipo());
		

		return null;
	}

	//	class AccesoArray { Expr izq;  Expr der; }
	public Object visit(AccesoArray node, Object param) {

		super.visit(node, param);

		predicado(node.getIzq().getTipo() instanceof ArrayType, "Eso no es una array", node.getStart());
		predicado(node.getDer().getTipo() instanceof IntType, 
				"Para acceder a una array necesitas un entero", node.getStart());
		
		node.setModificable(false);
		if(node.getIzq().getTipo() instanceof ArrayType)
			node.setTipo(((ArrayType)node.getIzq().getTipo()).getTipo());

		return null;
	}

	//	class OperacionUnaria { String string;  Expr der; }
	public Object visit(OperacionUnaria node, Object param) {

		super.visit(node, param);
		
		if(node.getString().equals("!")){
			predicado(node.getDer().getTipo() instanceof IntType, 
					"Solo se puede operar con Ints", node.getStart());
			
			node.setTipo(node.getDer().getTipo());
			node.setModificable(false);
		}

		return null;
	}

	//	class AccesoStruct { Expr struct;  String string; }
	public Object visit(AccesoStruct node, Object param) {

		super.visit(node, param);

		predicado(node.getStruct().getTipo() instanceof StructType, 
				"Debe ser un nombre correcto", node.getStart()); 
		node.setModificable(false);

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
		
		super.visit(node, param);
		
		node.setModificable(false);
		predicado(isIgualTipo(node.getTipo(), node.getExpr().getTipo()), 
				"No se puede hacer cast al mismo tipo", node.getStart());
		predicado(simple(node.getTipo()), "No se puede hacer cast a tipos complejos",
				node.getStart());
		

		return null;
	}

	//	class Var { String string; }
	public Object visit(Var node, Object param) {
		
		node.setModificable(true);
		node.setTipo(node.getDefinicion().getTipo());
		
		return null;
	}
	
	/**
	 * Método auxiliar opcional para ayudar a implementar los predicados de la Gramática Atribuida.
	 * 
	 * Ejemplo de uso (suponiendo implementado el método "esPrimitivo"):
	 * 	predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo", expr.getStart());
	 * 	predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo", null);
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
			gestorErrores.error("Comprobación de tipos", mensajeError, posicionError);
	}
	
	private boolean simple(Tipo tipo){
		return tipo instanceof CharType || tipo instanceof IntType || tipo instanceof RealType;
	}
	
	private boolean isIgualTipo(Tipo tipo1, Tipo tipo2){
		return tipo1.getClass().isAssignableFrom(tipo2.getClass());
	}
	
	
	private GestorErrores gestorErrores;
}
