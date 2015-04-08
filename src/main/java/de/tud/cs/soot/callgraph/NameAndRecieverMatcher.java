package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public class NameAndRecieverMatcher implements IMethodMatcher {

	@Override
	public boolean match(SootMethod sootMethod, InvokedMethod invokedMethod) {
		return sootMethod.getName().equals(invokedMethod.name())
				&& sootMethod.getDeclaringClass().getName().equals(invokedMethod.receiverType().replace('/', '.'));
	}
}
