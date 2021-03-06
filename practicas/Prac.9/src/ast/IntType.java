/**
 * @generated VGen 1.3.2
 */

package ast;

import visitor.*;

//	intType:tipo -> 

public class IntType extends AbstractTipo {

	public int getSize() {
		return 2;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

