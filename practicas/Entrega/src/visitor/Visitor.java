/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;

public interface Visitor {
	public Object visit(Programa node, Object param);
	public Object visit(DefVar node, Object param);
	public Object visit(Struct node, Object param);
	public Object visit(Funcion node, Object param);
	public Object visit(IntType node, Object param);
	public Object visit(RealType node, Object param);
	public Object visit(CharType node, Object param);
	public Object visit(StructType node, Object param);
	public Object visit(ArrayType node, Object param);
	public Object visit(Parametro node, Object param);
	public Object visit(If node, Object param);
	public Object visit(While node, Object param);
	public Object visit(Print node, Object param);
	public Object visit(Read node, Object param);
	public Object visit(Asignacion node, Object param);
	public Object visit(Invocacion node, Object param);
	public Object visit(Return node, Object param);
	public Object visit(ExpresionLogica node, Object param);
	public Object visit(ExpresionNumerica node, Object param);
	public Object visit(AccesoArray node, Object param);
	public Object visit(OperacionUnaria node, Object param);
	public Object visit(AccesoStruct node, Object param);
	public Object visit(Lintent node, Object param);
	public Object visit(Lintreal node, Object param);
	public Object visit(Lintchar node, Object param);
	public Object visit(Cast node, Object param);
	public Object visit(Var node, Object param);
}
