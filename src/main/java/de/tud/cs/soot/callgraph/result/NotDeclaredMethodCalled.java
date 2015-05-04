package de.tud.cs.soot.callgraph.result;

import soot.SootMethod;

public class NotDeclaredMethodCalled extends ResultCall {
	
	private SootMethod callee;

	public NotDeclaredMethodCalled(SootMethod sootMethod) {
		this.callee = sootMethod;
	}

	public SootMethod getCallee() {
		return callee;
	}

	
	

}
