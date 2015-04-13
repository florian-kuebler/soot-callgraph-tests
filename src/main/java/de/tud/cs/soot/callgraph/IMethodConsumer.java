package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public interface IMethodConsumer {

	void foundDeclaredCall(SootMethod sootMethod, InvokedMethod invokedMethod);
	void missedDeclaredCall(SootMethod sootMethod, InvokedMethod invokedMethod);
	void callNotDeclared(SootMethod sootMethod, InvokedMethod invokedMethod);
	
}
