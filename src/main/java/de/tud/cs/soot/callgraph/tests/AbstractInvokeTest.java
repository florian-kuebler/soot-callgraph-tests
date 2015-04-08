package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestCase;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import de.tud.cs.soot.callgraph.util.MethodUtils;
import soot.SootMethod;

public abstract class AbstractInvokeTest extends TestCase {
	
	private SootMethod sootMethod;
	private InvokedMethod invokedMethod;
	
	public AbstractInvokeTest(SootMethod sootMethod, InvokedMethod invokedMethod) {
		super(invokedMethod.line() + ": " + sootMethod.toString() + " -> " + MethodUtils.toSootMethodStyle(invokedMethod));
		this.sootMethod = sootMethod;
		this.invokedMethod = invokedMethod;
	}

	public SootMethod getSootMethod() {
		return sootMethod;
	}

	public InvokedMethod getInvokedMethod() {
		return invokedMethod;
	}
	
	
	
}
