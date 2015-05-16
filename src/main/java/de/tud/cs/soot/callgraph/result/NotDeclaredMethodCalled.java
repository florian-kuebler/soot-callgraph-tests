package de.tud.cs.soot.callgraph.result;

import soot.jimple.toolkits.callgraph.Edge;

public class NotDeclaredMethodCalled extends ResultCall {
	
	private Edge callEdge;

	public NotDeclaredMethodCalled(Edge callEdge) {
		this.callEdge = callEdge;
	}

	public Edge getCallEdge() {
		return callEdge;
	}

	
	

}
