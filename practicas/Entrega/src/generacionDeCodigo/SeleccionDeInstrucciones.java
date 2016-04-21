package generacionDeCodigo;

import java.io.*;
import java.util.*;

import ast.*;
import visitor.*;

public class SeleccionDeInstrucciones extends DefaultVisitor {

	public SeleccionDeInstrucciones(Writer writer, String sourceFile) {
		this.writer = new PrintWriter(writer);
		this.sourceFile = sourceFile;
	}

	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	//	Ejemplo:
	//
	//	public Object visit(Programa node, Object param) {
	//		genera("#source \"" + sourceFile + "\"");
	//		genera("call main");
	//		genera("halt");
	//		super.visit(node, param);	// Recorrer los hijos
	//		return null;
	//	}

//	class Programa { List<Sentencia> sentencia; }
	public Object visit(Programa node, Object param) {

		// super.visit(node, param);

		if (node.getSentencia() != null)
			for (Sentencia child : node.getSentencia())
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
			for (Sent_func child : node.getSent_func())
				child.accept(this, param);

		if (node.getTipo() != null)
			node.getTipo().accept(this, param);

		return null;
	}
	

	
	
	
	
	
	
	// Método auxiliar recomendado -------------
	private void genera(String instruccion) {
		writer.println(instruccion);
	}

	private PrintWriter writer;
	private String sourceFile;
}
