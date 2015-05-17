package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestCase;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

import de.tud.cs.soot.callgraph.util.MethodUtils;

public abstract class AbstractDeclaredMethodTest extends TestCase {
	
	private CallSite callSite;
	private ResolvedMethod resolvedMethod;
	
	public AbstractDeclaredMethodTest(CallSite callSite, ResolvedMethod resolvedMethod) {
		super(callSite.line() + ": " + MethodUtils.toSootMethodStyle(callSite, resolvedMethod));

		this.callSite = callSite;
		this.resolvedMethod = resolvedMethod;
	}


	public CallSite getCallSite() {
		return callSite;
	}

	public ResolvedMethod getResolvedMethod() {
		return resolvedMethod;
	}
}