package de.tud.cs.soot.callgraph.result;

import org.opalj.test.annotations.CallSite;
import org.opalj.test.annotations.ResolvedMethod;

import soot.jimple.toolkits.callgraph.Edge;

public class DeclaredMethodCalled extends ResultCall {

	private Edge callEdge;
	private CallSite callSite;
	private ResolvedMethod resolvedMethod;

	public DeclaredMethodCalled(Edge callEdge, CallSite callSite,
			ResolvedMethod resolvedMethod) {
		this.callEdge = callEdge;
		this.callSite = callSite;
		this.resolvedMethod = resolvedMethod;
	}

	public Edge getCallEdge() {
		return callEdge;
	}

	public CallSite getCallSite() {
		return callSite;
	}

	public ResolvedMethod getResolvedMethod(){
		return resolvedMethod;
	}
}
