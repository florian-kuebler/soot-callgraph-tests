package de.tud.cs.soot.callgraph.result;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public class DeclaredMethodCalled extends ResultCall {

	private SootMethod callee;
	private InvokedMethod invokedMethod;

	public DeclaredMethodCalled(SootMethod callee, InvokedMethod invokedMethod) {
		this.callee = callee;
		this.invokedMethod = invokedMethod;
	}

	public SootMethod getCallee() {
		return callee;
	}

	public InvokedMethod getInvokedMethod() {
		return invokedMethod;
	}

}
