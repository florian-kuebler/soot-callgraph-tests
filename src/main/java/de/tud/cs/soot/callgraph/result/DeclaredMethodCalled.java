package de.tud.cs.soot.callgraph.result;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

import soot.SootMethod;

public class DeclaredMethodCalled extends ResultCall {

	private SootMethod callee;
	private CallSite callSite;
	private ResolvedMethod resolvedMethod;

	public DeclaredMethodCalled(SootMethod callee, CallSite callSite,
			ResolvedMethod resolvedMethod) {
		this.callee = callee;
		this.callSite = callSite;
		this.resolvedMethod = resolvedMethod;
	}

	public SootMethod getCallee() {
		return callee;
	}

	public CallSite getCallSite() {
		return callSite;
	}

	public ResolvedMethod getResolvedMethod(){
		return resolvedMethod;
	}
}
