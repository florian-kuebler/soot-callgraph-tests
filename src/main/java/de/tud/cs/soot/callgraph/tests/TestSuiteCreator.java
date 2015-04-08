package de.tud.cs.soot.callgraph.tests;

import java.util.function.BiConsumer;

import junit.framework.TestSuite;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;
import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.CorrectCallgraphAnalysis;
import de.tud.cs.soot.callgraph.IMethodMatcher;
import de.tud.cs.soot.callgraph.NameAndRecieverMatcher;
import de.tud.cs.soot.callgraph.targets.Targets;

public class TestSuiteCreator {
	
	public static TestSuite createTestSuite(CallGraphAlgorithm cga){
		AnalysisTarget target = Targets.getDefaultTarget();
		final TestSuite suite = new TestSuite("CallCraph Tests");

		BiConsumer<SootMethod, InvokedMethod> miss = (sm, im) -> {
			suite.addTest(new MissInvokeTest(sm, im));
		};
		
		BiConsumer<SootMethod, InvokedMethod> pass = (sm, im) -> {
			suite.addTest(new PassInvokeTest(sm, im));
		};
		
		IMethodMatcher matcher = new NameAndRecieverMatcher();
		
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(cga, target, matcher, pass, miss);
		cca.perform();
		
		return suite;
	}

}
