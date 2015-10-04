package de.tud.cs.soot.callgraph.tests;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

public class DeclaredMethodCalledTest extends AbstractDeclaredMethodTest {

	public DeclaredMethodCalledTest( CallSite callSite, ResolvedMethod resolvedMethod, String caller) {
		super(callSite, resolvedMethod, caller);
	}

	@Override
	protected void runTest() throws Throwable {
		assertTrue(true);
	}
}
