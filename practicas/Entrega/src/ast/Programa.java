/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	programa -> definiciones:defVariable*  sentencias:sentencia*

public class Programa extends AbstractTraceable implements AST {

	public Programa(List<DefVariable> definiciones, List<Sentencia> sentencias) {
		this.definiciones = definiciones;
		this.sentencias = sentencias;

		searchForPositions(definiciones, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Programa(Object definiciones, Object sentencias) {
		this.definiciones = (List<DefVariable>) definiciones;
		this.sentencias = (List<Sentencia>) sentencias;

		searchForPositions(definiciones, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public List<DefVariable> getDefiniciones() {
		return definiciones;
	}
	public void setDefiniciones(List<DefVariable> definiciones) {
		this.definiciones = definiciones;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}
	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<DefVariable> definiciones;
	private List<Sentencia> sentencias;
}

