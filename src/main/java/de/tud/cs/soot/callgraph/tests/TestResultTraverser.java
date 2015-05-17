package de.tud.cs.soot.callgraph.tests;

import soot.SootMethod;
import junit.framework.TestSuite;
import de.tud.cs.soot.callgraph.result.DeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.DeclaredMethodNotCalled;
import de.tud.cs.soot.callgraph.result.IResultTraverser;
import de.tud.cs.soot.callgraph.result.NotDeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.result.ResultCall;
import de.tud.cs.soot.callgraph.result.ResultClass;
import de.tud.cs.soot.callgraph.result.ResultMethod;

public class TestResultTraverser implements IResultTraverser {
	
	TestSuite suite;
	
	public TestResultTraverser(TestSuite suite){
		this.suite = suite;
	}

	@Override
	public void traverse(Result result) {
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
						methodSuite.addTest(new NotDeclaredCallFoundTest(((NotDeclaredMethodCalled) call).getCallEdge())); //TODO
					} else
						throw new RuntimeException("Unexpected ResultCall: " + call);
				}
				
				classSuite.addTest(methodSuite);
			}
			suite.addTest(classSuite);
		}
	}

}
