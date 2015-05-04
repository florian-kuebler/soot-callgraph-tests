package de.tud.cs.soot.callgraph.result;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

public class DeclaredMethodNotCalled extends ResultCall {

	private CallSite callSite;
	private ResolvedMethod resolvedMethod;

	public DeclaredMethodNotCalled(CallSite callSite,
			ResolvedMethod resolvedMethod) {
		this.callSite = callSite;
		this.resolvedMethod = resolvedMethod;
	}

	public CallSite getCallSite() {
		return callSite;
	}

	public ResolvedMethod getResolvedMethod() {
		return resolvedMethod;
	}
}
