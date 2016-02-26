package de.tud.cs.soot.callgraph.tests;

import org.opalj.annotations.callgraph.CallSite;
import org.opalj.annotations.callgraph.ResolvedMethod;

public class DeclaredMethodCalledTest extends AbstractDeclaredMethodTest {

	public DeclaredMethodCalledTest( CallSite callSite, ResolvedMethod resolvedMethod, String caller, int index) {
		super(callSite, resolvedMethod, caller, index);
	}

	@Override
	protected void runTest() throws Throwable {
		assertTrue(true);
	}
}
