package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

import soot.SootMethod;

public interface IMethodMatcher {
	
	public boolean match(SootMethod sootMethod, CallSite callSite, ResolvedMethod resolvedMethod);

}
