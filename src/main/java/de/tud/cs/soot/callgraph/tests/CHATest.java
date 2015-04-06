package de.tud.cs.soot.callgraph.tests;

import java.util.function.BiConsumer;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;
import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.CorrectCallgraphAnalysis;
import de.tud.cs.soot.callgraph.IMethodMatcher;
import de.tud.cs.soot.callgraph.NameAndRecieverMatcher;
import de.tud.cs.soot.callgraph.targets.Targets;
import de.tud.cs.soot.callgraph.util.MethodUtils;

@RunWith(AllTests.class)
public class CHATest {

	
	
	public static TestSuite suite() {
		
		AnalysisTarget target = Targets.getDefaultTarget();
		final TestSuite suite = new TestSuite("CallCraph Tests");

		BiConsumer<SootMethod, InvokedMethod> miss = (sm, im) -> {
			String name = sm.toString() + " -> " + MethodUtils.toSootMethodStyle(im);
			TestSuite its = new TestSuite(name);
			its.addTest(new MissInvokeTest());
			suite.addTest(its);
		};
		
		BiConsumer<SootMethod, InvokedMethod> pass = (sm, im) -> {
			String name = sm.toString() + " -> " + MethodUtils.toSootMethodStyle(im);
			TestSuite its = new TestSuite(name);
			its.addTest(new PassInvokeTest());
			suite.addTest(its);
		};
		
		IMethodMatcher matcher = new NameAndRecieverMatcher();
		
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.CHA, target, matcher, pass, miss);
		cca.perform();
		
		return suite;
		
	}

}
