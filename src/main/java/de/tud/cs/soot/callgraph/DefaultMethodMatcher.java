package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

import soot.SootMethod;

public class DefaultMethodMatcher implements IMethodMatcher {

	@Override
	public boolean match(SootMethod sootMethod, CallSite callSite,
			ResolvedMethod resolvedMethod) {
		
		return sootMethod.getName().equals(callSite.name())
				&& sootMethod.getDeclaringClass().getName()
						.equals(resolvedMethod.receiverType().replace('/', '.'));
	}
}
