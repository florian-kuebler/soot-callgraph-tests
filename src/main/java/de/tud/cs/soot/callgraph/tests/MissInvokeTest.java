package de.tud.cs.soot.callgraph.tests;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;
import de.tud.cs.soot.callgraph.CallNotFoundException;

public class MissInvokeTest extends AbstractInvokeTest {

	public MissInvokeTest(SootMethod sootMethod, InvokedMethod invokedMethod) {
		super(sootMethod, invokedMethod);
	}

	@Override
	protected void runTest() throws Throwable {

		throw new CallNotFoundException(getSootMethod(), getInvokedMethod());
	}

}
