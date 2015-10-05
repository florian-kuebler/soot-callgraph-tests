package de.tud.cs.soot.callgraph.tests;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

public class DeclaredCallNotFoundTest extends AbstractDeclaredMethodTest {

	public DeclaredCallNotFoundTest(CallSite callSite, ResolvedMethod resolvedMethod, String caller, int index) {
		super(callSite, resolvedMethod, caller, index);
	}

	@Override
	protected void runTest() throws Throwable {
		throw new RuntimeException("Declared method call not found: " + getResolvedMethod().receiverType() + getCallSite().name());
	}

}
