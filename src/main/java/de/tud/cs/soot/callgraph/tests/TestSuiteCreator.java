package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestSuite;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;

import soot.SootMethod;
import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.CorrectCallgraphAnalysis;
import de.tud.cs.soot.callgraph.DefaultMethodMatcher;
import de.tud.cs.soot.callgraph.IMethodMatcher;
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

		IMethodMatcher matcher = new DefaultMethodMatcher();

		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(cga, target, matcher);
		
		Result result = cca.perform();

		for (ResultClass clazz : result.getClasses()) {
			TestSuite classSuite = new TestSuite(clazz.getSootClass().toString());

			for (ResultMethod method : clazz.getMethods()) {
				SootMethod sootMethod = method.getSootMethod();
				TestSuite methodSuite = new TestSuite(sootMethod.getName());
				
				for (ResultCall call : method.getCalls()) {
					if (call instanceof DeclaredMethodCalled) {
						DeclaredMethodCalled call2 = (DeclaredMethodCalled) call;					
						methodSuite.addTest(new DeclaredMethodCalledTest(call2.getCallSite(), call2.getResolvedMethod()));;
					} else if (call instanceof DeclaredMethodNotCalled) {
						DeclaredMethodNotCalled call2 = (DeclaredMethodNotCalled) call;	
						methodSuite.addTest(new DeclaredCallNotFoundTest(call2.getCallSite(), call2.getResolvedMethod()));
					} else if (call instanceof NotDeclaredMethodCalled) {
						methodSuite.addTest(new NotDeclaredCallFoundTest(((NotDeclaredMethodCalled) call).getCallee()));
					} else
						throw new RuntimeException("Unexpected ResultCall: " + call);
				}
				
				classSuite.addTest(methodSuite);
			}
			suite.addTest(classSuite);
		}

		return suite;
	}
}
