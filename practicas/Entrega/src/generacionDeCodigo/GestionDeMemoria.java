package generacionDeCodigo;

import ast.*;
import visitor.*;

/** 
 * Clase encargada de asignar direcciones a las variables 
 */
public class GestionDeMemoria extends DefaultVisitor {

	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	//	class Programa { List<Sentencia> sentencia; }
	public Object visit(Programa node, Object param) {

		// super.visit(node, param);
		
		int sumaTamañoVariables = 0;

		if (node.getSentencia() != null){
			for (Sentencia child : node.getSentencia()){
				if(child instanceof DefVar){
					((DefVar) child).setDireccion(sumaTamañoVariables);
					sumaTamañoVariables += ((DefVar) child).getTipo().getMemSize();
				}
			}
		}
		
		return null;
		
	}

}
