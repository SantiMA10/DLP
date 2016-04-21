package generacionDeCodigo;

import ast.*;
import visitor.*;

/** 
 * Clase encargada de asignar direcciones a las variables 
 */
public class GestionDeMemoria extends DefaultVisitor {

	/*
	 * Poner aqu� los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	//	class Programa { List<Sentencia> sentencia; }
	public Object visit(Programa node, Object param) {

		// super.visit(node, param);

		int sumaTama�oVariables = 0;

		if (node.getSentencia() != null){
			for (Sentencia child : node.getSentencia()){
				if(child instanceof DefVar){
					((DefVar) child).setDireccion(sumaTama�oVariables);
					sumaTama�oVariables += ((DefVar) child).getTipo().getMemSize();
				}
				child.accept(this, param);
			}
		}

		return null;

	}

	//	class Struct { String string;  List<DefVar> defvar; }
	public Object visit(Struct node, Object param) {

		// super.visit(node, param);
		
		int sumaTama�oVariables = 0;

		if (node.getDefvar() != null)
			for (DefVar child : node.getDefvar()){
				child.setDireccion(sumaTama�oVariables);
				sumaTama�oVariables += child.getTipo().getMemSize();
				child.accept(this, param);
			}

		return null;
	}
	
	//	class Funcion { String string;  List<Parametro> parametro;  List<DefVar> defvar;  List<Sent_func> sent_func;  Tipo tipo; }
	public Object visit(ast.Funcion node, Object param) {

		// super.visit(node, param);

		if (node.getParametro() != null){
			
			int sumaTama�oVariables = 4;
		
			for (int i = (node.getParametro().size() - 1); i >= 0; i--){
				node.getParametro().get(i).setDireccion(sumaTama�oVariables);
				sumaTama�oVariables += node.getParametro().get(i).getTipo().getMemSize();
				node.getParametro().get(i).accept(this, param);
			}
		}

		if (node.getDefvar() != null){
			int sumaTama�oVariables = 0;

			for (DefVar child : node.getDefvar()){
				sumaTama�oVariables -= child.getTipo().getMemSize();
				child.setDireccion(sumaTama�oVariables);
				child.accept(this, param);
			}
		}

		if (node.getSent_func() != null)
			for (Sent_func child : node.getSent_func())
				child.accept(this, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}

}
