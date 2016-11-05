/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	programa -> sentencia:sentencia*

public class Programa extends AbstractTraceable implements AST {

	public Programa(List<Sentencia> sentencia) {
		this.sentencia = sentencia;

		searchForPositions(sentencia);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Programa(Object sentencia) {
		this.sentencia = (List<Sentencia>) sentencia;

		searchForPositions(sentencia);	// Obtener linea/columna a partir de los hijos
	}

	public List<Sentencia> getSentencia() {
		return sentencia;
	}
	public void setSentencia(List<Sentencia> sentencia) {
		this.sentencia = sentencia;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<Sentencia> sentencia;
}

