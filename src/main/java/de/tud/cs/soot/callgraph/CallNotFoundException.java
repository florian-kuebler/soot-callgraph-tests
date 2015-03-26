package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import de.tud.cs.soot.callgraph.util.MethodUtils;
import soot.SootMethod;

public class CallNotFoundException extends Exception{

	private static final long serialVersionUID = -7326408638368829766L;
	
	private SootMethod caller;
	private InvokedMethod expectedCall;

	public CallNotFoundException(SootMethod caller, InvokedMethod expectedCall) {
		super("Missed Call: " + caller + " -> " + MethodUtils.toSootMethodStyle(expectedCall));
		this.caller = caller;
		this.expectedCall = expectedCall;
	}

	public SootMethod getCaller() {
		return caller;
	}

	public InvokedMethod getExpectedCall() {
		return expectedCall;
	}

}
