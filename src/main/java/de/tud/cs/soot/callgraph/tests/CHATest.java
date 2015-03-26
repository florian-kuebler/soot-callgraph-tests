package de.tud.cs.soot.callgraph.tests;

import static org.junit.Assert.*;

import java.util.function.BiConsumer;

import org.junit.Test;
import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;
import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.CorrectCallgraphAnalysis;
import de.tud.cs.soot.callgraph.IMethodMatcher;
import de.tud.cs.soot.callgraph.NameAndRecieverMatcher;
import de.tud.cs.soot.callgraph.targets.Targets;
import de.tud.cs.soot.callgraph.util.MethodUtils;

public class CHATest {

	@Test
	public void test() {
		
		AnalysisTarget target = Targets.getDefaultTarget();

		BiConsumer<SootMethod, InvokedMethod> miss = (sm, im) -> {
			fail("Missed Call: " + sm + " -> " + MethodUtils.toSootMethodStyle(im));
		};
		
		BiConsumer<SootMethod, InvokedMethod> pass = (sm, im) -> {
			assertTrue(true);
		};
		
		IMethodMatcher matcher = new NameAndRecieverMatcher();
		
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.CHA, target, matcher, pass, miss);
		cca.perform();
	}

}
