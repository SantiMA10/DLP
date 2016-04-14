package generacionDeCodigo;

import ast.*;
import visitor.*;

/** 
 * Clase encargada de asignar direcciones a las variables 
 *
 * @author Raul Izquierdo
 */
public class GestionDeMemoria extends DefaultVisitor {

	// class Programa { List<DefVariable> definiciones; List<Sentencia> sentencias; }
	public Object visit(Programa node, Object param) {

		int sumaTama�oVariables = 0;

		for (DefVariable child : node.getDefiniciones()) 		{
			child.setDireccion(sumaTama�oVariables);
			sumaTama�oVariables += child.getTipo().getSize();
		}
		return null;
	}

}
