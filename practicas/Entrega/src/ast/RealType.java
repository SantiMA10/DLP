/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	realType:tipo -> 

public class RealType extends AbstractTipo {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

