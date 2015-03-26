package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public interface IMethodMatcher {
	
	public boolean match(SootMethod sm, InvokedMethod iv);

}
