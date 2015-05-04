package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestCase;
import soot.SootMethod;

public abstract class AbstractNotDeclaredMethodTest extends TestCase {
	

	private SootMethod sootMethod;
	
	public AbstractNotDeclaredMethodTest(SootMethod method) {
		super(method.toString());
		this.sootMethod = method;
	}

	public SootMethod getSootMethod() {
		return sootMethod;
	}


	
	
	
	
}
