package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;

@RunWith(AllTests.class)
public class ALLTests {
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite("CallGraph Tests");
		
		suite.addTest(TestSuiteCreator.createTestSuite(CallGraphAlgorithm.CHA));
		
		suite.addTest(TestSuiteCreator.createTestSuite(CallGraphAlgorithm.BasicVTA));
		
		suite.addTest(TestSuiteCreator.createTestSuite(CallGraphAlgorithm.RTA));
		
		suite.addTest(TestSuiteCreator.createTestSuite(CallGraphAlgorithm.SPARK));
		
		return suite;
	}

}
