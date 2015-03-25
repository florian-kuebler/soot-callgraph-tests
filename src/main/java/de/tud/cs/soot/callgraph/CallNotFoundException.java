package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public class CallNotFoundException extends Exception{

	private static final long serialVersionUID = -7326408638368829766L;

	public CallNotFoundException(SootMethod sm, InvokedMethod expectedCall) {
		super(sm.toString() + ": Expected call to " + expectedCall.receiverType() + "." + expectedCall.name());
	}

}
