package de.tud.cs.soot.callgraph.tests;

import soot.jimple.toolkits.callgraph.Edge;

public class NotDeclaredCallFoundTest extends AbstractNotDeclaredMethodTest {

	public NotDeclaredCallFoundTest(Edge edge) {
		super(edge);
	}

	@Override
	protected void runTest() throws Throwable {
		fail();
	}

}
