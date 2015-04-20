package de.tud.cs.soot.callgraph.result;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

public class DeclaredMethodNotCalled extends ResultCall {

	private InvokedMethod invokedMethod;

	public DeclaredMethodNotCalled(InvokedMethod invokedMethod) {
		this.invokedMethod = invokedMethod;
	}

	public InvokedMethod getInvokedMethod() {
		return invokedMethod;
	}
}
