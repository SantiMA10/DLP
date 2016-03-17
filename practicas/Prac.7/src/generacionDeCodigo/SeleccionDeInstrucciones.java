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
	 * Poner aqu� los visit necesarios.
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

	
	
	
	

	
	
	
	
	
	
	// M�todo auxiliar recomendado -------------
	private void genera(String instruccion) {
		writer.println(instruccion);
	}

	private PrintWriter writer;
	private String sourceFile;
}
