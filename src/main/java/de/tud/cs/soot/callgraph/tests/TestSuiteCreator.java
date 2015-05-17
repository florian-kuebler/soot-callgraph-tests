package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestSuite;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.CorrectCallgraphAnalysis;
import de.tud.cs.soot.callgraph.DefaultMethodMatcher;
import de.tud.cs.soot.callgraph.IMethodMatcher;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.targets.Targets;

public class TestSuiteCreator {

	public static TestSuite createTestSuite(CallGraphAlgorithm cga) {
		AnalysisTarget target = Targets.getDefaultTarget();

		final TestSuite suite = new TestSuite("CallCraph Tests" + cga);
		
		TestResultTraverser traverser = new TestResultTraverser(suite);

		IMethodMatcher matcher = new DefaultMethodMatcher();

		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(cga, target, matcher);
		
		Result result = cca.perform();

		traverser.traverse(result);

		return suite;
	}
}
