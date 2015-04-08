package de.tud.cs.soot.callgraph.tests;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public class PassInvokeTest extends AbstractInvokeTest {

	public PassInvokeTest(SootMethod sootMethod, InvokedMethod invokedMethod) {
		super(sootMethod, invokedMethod);
	}

	@Override
	protected void runTest() throws Throwable {
		assertTrue(true);

	}
}
