package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public class NameAndRecieverMatcher implements IMethodMatcher {

	@Override
	public boolean match(SootMethod sm, InvokedMethod iv) {
		return sm.getName().equals(iv.name())
				&& sm.getDeclaringClass().getName().equals(iv.receiverType().replace('/', '.'));
	}
}
