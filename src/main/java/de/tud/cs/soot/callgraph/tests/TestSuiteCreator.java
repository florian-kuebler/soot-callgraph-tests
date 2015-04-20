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
import de.tud.cs.soot.callgraph.result.DeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.DeclaredMethodNotCalled;
import de.tud.cs.soot.callgraph.result.NotDeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.result.ResultCall;
import de.tud.cs.soot.callgraph.result.ResultClass;
import de.tud.cs.soot.callgraph.result.ResultMethod;
import de.tud.cs.soot.callgraph.targets.Targets;

public class TestSuiteCreator {

	public static TestSuite createTestSuite(CallGraphAlgorithm cga) {
		AnalysisTarget target = Targets.getDefaultTarget();
		final TestSuite suite = new TestSuite("CallCraph Tests");

		BiConsumer<SootMethod, InvokedMethod> miss = (sm, im) -> {
			suite.addTest(new MissInvokeTest(sm, im));
		};

		BiConsumer<SootMethod, InvokedMethod> pass = (sm, im) -> {
			suite.addTest(new PassInvokeTest(sm, im));
		};

		IMethodMatcher matcher = new NameAndRecieverMatcher();

		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(cga, target, matcher);
		Result result = cca.perform();

		for (ResultClass clazz : result.getClasses()) {
			TestSuite classSuite = new TestSuite(clazz.getSootClass().toString());

			for (ResultMethod method : clazz.getMethods()) {
				TestSuite methodSuite = new TestSuite(method.getSootMethod().toString());
				
				for (ResultCall call : method.getCalls()) {
					if (call instanceof DeclaredMethodCalled) {
						methodSuite.addTest(new PassInvokeTest(method.getSootMethod(), ((DeclaredMethodCalled) call).getInvokedMethod()));;
					} else if (call instanceof DeclaredMethodNotCalled) {

						methodSuite.addTest(new MissInvokeTest(method.getSootMethod(), ((DeclaredMethodCalled) call).getInvokedMethod()));
					} else if (call instanceof NotDeclaredMethodCalled) {
						
					} else
						throw new RuntimeException();
				}
				
				classSuite.addTest(methodSuite);
			}
			suite.addTest(classSuite);
		}

		return suite;
	}
}
