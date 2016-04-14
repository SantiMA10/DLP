/**
 * @generated VGen 1.3.2
 */

package ast;

import visitor.*;

//	realType:tipo -> 

public class RealType extends AbstractTipo {

	public int getSize() {
		return 4;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

