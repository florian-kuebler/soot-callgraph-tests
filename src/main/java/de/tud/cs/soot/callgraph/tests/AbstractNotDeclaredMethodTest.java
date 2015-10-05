package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestCase;
import soot.jimple.toolkits.callgraph.Edge;

public abstract class AbstractNotDeclaredMethodTest extends TestCase {
	

	private Edge callEdge;
	
	public AbstractNotDeclaredMethodTest(Edge callEdge, int index) {
		super(callEdge.srcStmt().getJavaSourceStartLineNumber() + ": " + callEdge.src().getName() + " -> " +  callEdge.tgt().toString().replaceAll("<|>", "")  + " (" + index + ")");
		this.callEdge = callEdge;
	}

	public Edge getCallEdge() {
		return callEdge;
	}
	
}
