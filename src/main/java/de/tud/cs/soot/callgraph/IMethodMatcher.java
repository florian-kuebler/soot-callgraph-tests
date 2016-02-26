package de.tud.cs.soot.callgraph;

import org.opalj.annotations.callgraph.CallSite;
import org.opalj.annotations.callgraph.ResolvedMethod;

import soot.SootMethod;

public interface IMethodMatcher {
	
	public boolean match(SootMethod sootMethod, CallSite callSite, ResolvedMethod resolvedMethod);

}
