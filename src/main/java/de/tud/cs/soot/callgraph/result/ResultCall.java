package de.tud.cs.soot.callgraph.result;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public abstract class ResultCall {
	
	SootMethod callee;
	InvokedMethod invokedMethod;

}
