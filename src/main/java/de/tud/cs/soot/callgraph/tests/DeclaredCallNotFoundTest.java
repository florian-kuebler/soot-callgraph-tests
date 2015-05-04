package de.tud.cs.soot.callgraph.tests;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

public class DeclaredCallNotFoundTest extends AbstractDeclaredMethodTest {

	public DeclaredCallNotFoundTest(CallSite callSite, ResolvedMethod resolvedMethod) {
		super(callSite, resolvedMethod);
	}

	@Override
	protected void runTest() throws Throwable {
		fail();
	}

}
