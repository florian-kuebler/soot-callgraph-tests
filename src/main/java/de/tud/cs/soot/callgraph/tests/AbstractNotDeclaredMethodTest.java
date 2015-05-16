package de.tud.cs.soot.callgraph.tests;

import java.util.Random;

import junit.framework.TestCase;
import soot.jimple.toolkits.callgraph.Edge;

public abstract class AbstractNotDeclaredMethodTest extends TestCase {
	

	private Edge callEdge;
	
	public AbstractNotDeclaredMethodTest(Edge callEdge) {
		super(callEdge.srcStmt().getJavaSourceStartLineNumber() + ": " + callEdge.tgt()+new Random().nextFloat());
		this.callEdge = callEdge;
	}

	public Edge getCallEdge() {
		return callEdge;
	}
	
}
