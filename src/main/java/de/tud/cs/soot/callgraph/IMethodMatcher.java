package de.tud.cs.soot.callgraph;

import org.opalj.test.annotations.CallSite;
import org.opalj.test.annotations.ResolvedMethod;

import soot.SootMethod;

public interface IMethodMatcher {
	
	public boolean match(SootMethod sootMethod, CallSite callSite, ResolvedMethod resolvedMethod);

}
