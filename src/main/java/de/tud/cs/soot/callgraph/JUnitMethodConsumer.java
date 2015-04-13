package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public class JUnitMethodConsumer implements IMethodConsumer {

	@Override
	public void foundDeclaredCall(SootMethod sootMethod, InvokedMethod invokedMethod) {
		// TODO Auto-generated method stub

	}

	@Override
	public void missedDeclaredCall(SootMethod sootMethod, InvokedMethod invokedMethod) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callNotDeclared(SootMethod sootMethod, InvokedMethod invokedMethod) {
		// TODO Auto-generated method stub

	}

}
