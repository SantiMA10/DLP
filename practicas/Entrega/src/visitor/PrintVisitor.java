package visitor;

import java.util.List;

import ast.ArrayType;
import ast.Asignacion;
import ast.Cast;
import ast.CharType;
import ast.DefVar;
import ast.Expr;
import ast.Funcion;
import ast.If;
import ast.IntType;
import ast.Invocacion;
import ast.Lintchar;
import ast.Lintent;
import ast.Lintreal;
import ast.Op_bin;
import ast.Op_un;
import ast.Parametro;
import ast.Print;
import ast.Programa;
import ast.Read;
import ast.RealType;
import ast.Return;
import ast.Sent_func;
import ast.Sentencia;
import ast.Struct;
import ast.StructType;
import ast.Var;
import ast.While;

public class PrintVisitor extends DefaultVisitor {

	// ---------------------------------------------------------
		// Tareas a realizar en cada método visit:
		//
		// Si en algún método visit NO SE QUIERE HACER NADA más que recorrer los hijos entonces se puede 
		// borrar (dicho método se heredará de DefaultVisitor con el código de recorrido).
		//
		// Lo siguiente es para cuando se quiera AÑADIR alguna funcionalidad adicional a un visit:
		//
		// - El código que aparece en cada método visit es aquel que recorre los hijos. Es el mismo código
		//		que está implementado en el padre (DefaultVisitor). Por tanto la llamada a 'super.visit' y el
		//		resto del código del método hacen lo mismo (por ello 'super.visit' está comentado).
		//
		// - Lo HABITUAL será borrar todo el código de recorrido dejando solo la llamada a 'super.visit'. De esta
		//		manera cada método visit se puede centrar en la tarea que tiene que realizar sobre su nodo del AST.
		//
		// - La razón de que aparezca el código de recorrido de los hijos es por si se necesita realizar alguna
		//		tarea DURANTE el mismo (por ejemplo ir comprobando su tipo). En este caso ya se tiene implementado
		//		dicho recorrido y solo habrá que incrustar las acciones adicionales en el mismo. En este caso
		//		la llamada a 'super.visit' deberá ser borrada.
		// ---------------------------------------------------------


		//	class Programa { List<Sentencia> sentencia; }
		public Object visit(Programa node, Object param) {

			// super.visit(node, param);

			if (node.getSentencia() != null)
				for (Sentencia child : node.getSentencia())
					child.accept(this, param);

			return null;
		}

		//	class DefVar { String string;  Tipo tipo; }
		public Object visit(DefVar node, Object param) {

			// super.visit(node, param);
			System.out.print("var " + node.getString() + ":");
			if (node.getTipo() != null)
				node.getTipo().accept(this, param);
			
			System.out.println(";");

			return null;
		}

		//	class Struct { String string;  List<DefVar> defvar; }
		public Object visit(Struct node, Object param) {

			System.out.println("struct " + node.getString() + "{");

			if (node.getDefvar() != null)
				for (DefVar child : node.getDefvar())
					child.accept(this, param);
			
			System.out.println("};");

			return null;
		}

		//	class Funcion { String string;  List<Parametro> parametro;  List<DefVar> defvar;  List<Sent_func> sent_func;  Tipo tipo; }
		public Object visit(Funcion node, Object param) {

			// super.visit(node, param);
			
			System.out.print(node.getString() + "(");

			if (node.getParametro() != null){
				List<Parametro> params = node.getParametro();
				for (int i = 0; i <  params.size(); i++){
					params.get(i).accept(this, param);
					if(i < params.size() - 1) System.out.print(", ");
				}
			}
			
			System.out.print(")");
			
			if (node.getTipo() != null){
				System.out.print(": ");
				node.getTipo().accept(this, param);
			}
			
			System.out.println("{");

			if (node.getDefvar() != null)
				for (DefVar child : node.getDefvar())
					child.accept(this, param);

			if (node.getSent_func() != null)
				for (Sent_func child : node.getSent_func()) {
					child.accept(this, param);
				}
			
			System.out.println("}");

			return null;
		}

		//	class IntType {  }
		public Object visit(IntType node, Object param) {
			System.out.print("int");
			return null;
		}

		//	class RealType {  }
		public Object visit(RealType node, Object param) {
			System.out.print("float");
			return null;
		}

		//	class CharType {  }
		public Object visit(CharType node, Object param) {
			System.out.print("char");
			return null;
		}

		//	class StructType { String string; }
		public Object visit(StructType node, Object param) {
			System.out.print(node.getString());
			return null;
		}

		//	class ArrayType { Tipo tipo;  int size; }
		public Object visit(ArrayType node, Object param) {

			// super.visit(node, param);
			
			System.out.print("[" + node.getSize() + "] ");
			if (node.getTipo() != null)
				node.getTipo().accept(this, param);

			return null;
		}

		//	class Parametro { String string;  Tipo tipo; }
		public Object visit(Parametro node, Object param) {

			System.out.print(node.getString() + ":");
			super.visit(node, param);

			return null;
		}

		//	class If { Expr expr;  List<Sent_func> verdadero;  List<Sent_func> falso; }
		public Object visit(If node, Object param) {

			// super.visit(node, param);

			System.out.print("if( ");
			if (node.getExpr() != null)
				node.getExpr().accept(this, param);
			System.out.println(" ) {");

			if (node.getVerdadero() != null)
				for (Sent_func child : node.getVerdadero()){
					child.accept(this, param);
				}

			if (node.getFalso() != null){
				System.out.println("} else {");
				for (Sent_func child : node.getFalso()){
					child.accept(this, param);
				}
			}
			System.out.println("}");

			return null;
		}

		//	class While { Expr expr;  List<Sent_func> sent_func; }
		public Object visit(While node, Object param) {

			// super.visit(node, param);

			System.out.print("while( ");
			if (node.getExpr() != null)
				node.getExpr().accept(this, param);
			System.out.println(" ){");

			if (node.getSent_func() != null)
				for (Sent_func child : node.getSent_func()){
					child.accept(this, param);
				}
			System.out.println("}");
			

			return null;
		}

		//	class Print { Expr expr; }
		public Object visit(Print node, Object param) {

			System.out.print("print ");
			super.visit(node, param);
			System.out.println(";");

			return null;
		}

		//	class Read { Expr expr; }
		public Object visit(Read node, Object param) {

			System.out.print("read ");
			super.visit(node, param);
			System.out.println(";");

			return null;
		}

		//	class Asignacion { Expr izq;  Expr der; }
		public Object visit(Asignacion node, Object param) {

			// super.visit(node, param);

			if (node.getIzq() != null)
				node.getIzq().accept(this, param);
			
			System.out.print(" = ");

			if (node.getDer() != null)
				node.getDer().accept(this, param);
			
			System.out.println(";");
			
			return null;
		}

		//	class Invocacion { String string;  List<Expr> expr; }
		public Object visit(Invocacion node, Object param) {

			// super.visit(node, param);

			System.out.print(node.getString() +"(");
			if (node.getExpr() != null){
				List<Expr> params =  node.getExpr();
				
				for (int i = 0; i < params.size(); i++){
					params.get(i).accept(this, param);
					if(i < params.size() - 1) System.out.print(", ");
				}
			}
			System.out.print(")");
			

			return null;
		}

		//	class Return { Expr expr; }
		public Object visit(Return node, Object param) {

			System.out.print("return ");
			super.visit(node, param);
			System.out.println(";");
			
			return null;
		}

		//	class Op_bin { Expr izq;  String string;  Expr der; }
		public Object visit(Op_bin node, Object param) {

			// super.visit(node, param);

			if (node.getIzq() != null)
				node.getIzq().accept(this, param);
			
			if(node.getString().contains("["))
				System.out.print(node.getString().charAt(0));
			else
				System.out.print(node.getString());

			if (node.getDer() != null)
				node.getDer().accept(this, param);
			
			if(node.getString().length() > 1 && node.getString().contains("[")){
				System.out.print(node.getString().charAt(1));
			}

			return null;
		}

		//	class Op_un { String string;  Expr der; }
		public Object visit(Op_un node, Object param) {
			
			System.out.print(node.getString().charAt(0));
			super.visit(node, param);
			if(node.getString().length() > 1) {
				System.out.print(node.getString().charAt(1));
			}

			return null;
		}

		//	class Lintent { String string; }
		public Object visit(Lintent node, Object param) {
			System.out.print(node.getString());
			return null;
		}

		//	class Lintreal { String string; }
		public Object visit(Lintreal node, Object param) {
			System.out.print(node.getString());
			return null;
		}

		//	class Lintchar { String string; }
		public Object visit(Lintchar node, Object param) {
			System.out.print(node.getString());
			return null;
		}

		//	class Cast { Tipo tipo;  Expr expr; }
		public Object visit(Cast node, Object param) {

			// super.visit(node, param);
			
			System.out.print("cast<");
			if (node.getTipo() != null)
				node.getTipo().accept(this, param);
			System.out.print(">");
			
			System.out.print("(");
			if (node.getExpr() != null)
				node.getExpr().accept(this, param);
			System.out.print(")");

			return null;
		}

		//	class Var { String string; }
		public Object visit(Var node, Object param) {
			System.out.print(node.getString());
			return null;
		}
	
}
