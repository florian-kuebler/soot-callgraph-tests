package de.tud.cs.soot.callgraph.tests;

import org.opalj.annotations.callgraph.CallSite;
import org.opalj.annotations.callgraph.ResolvedMethod;

public class DeclaredCallNotFoundTest extends AbstractDeclaredMethodTest {

	public DeclaredCallNotFoundTest(CallSite callSite, ResolvedMethod resolvedMethod, String caller, int index) {
		super(callSite, resolvedMethod, caller, index);
	}

	@Override
	protected void runTest() throws Throwable {
		throw new RuntimeException("Declared method call not found: " + getResolvedMethod().receiverType() + getCallSite().name());
	}

}
