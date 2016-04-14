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
			}
		}
		
		return null;
		
	}

}
