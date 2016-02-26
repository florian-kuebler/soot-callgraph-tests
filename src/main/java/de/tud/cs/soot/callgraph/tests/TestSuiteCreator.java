package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestSuite;

import org.opalj.annotations.callgraph.CallGraphAlgorithm;
import org.opalj.annotations.callgraph.CallGraphAlgorithmMode;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.CorrectCallgraphAnalysis;
import de.tud.cs.soot.callgraph.DefaultMethodMatcher;
import de.tud.cs.soot.callgraph.IMethodMatcher;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.targets.Targets;

public class TestSuiteCreator {

	public static TestSuite createTestSuite(CallGraphAlgorithm cga, CallGraphAlgorithmMode mode, int index) {
		AnalysisTarget target = Targets.getDefaultTarget();

		final TestSuite suite = new TestSuite("CallCraph Tests " + cga + " " + mode);
		
		TestResultTraverser traverser = new TestResultTraverser(suite);

		IMethodMatcher matcher = new DefaultMethodMatcher();

		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(cga, mode, target, matcher);
		
		Result result = cca.perform();

		traverser.traverse(result, index);

		return suite;
	}
}
