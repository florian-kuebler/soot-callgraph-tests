package de.tud.cs.soot.callgraph.tests;

import soot.SootMethod;

public class NotDeclaredCallFoundTest extends AbstractNotDeclaredMethodTest {

	public NotDeclaredCallFoundTest(SootMethod sootMethod) {
		super(sootMethod);
	}

	@Override
	protected void runTest() throws Throwable {
		fail();
	}

}
