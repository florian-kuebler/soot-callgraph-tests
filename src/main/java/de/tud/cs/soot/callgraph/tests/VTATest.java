package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;

@RunWith(AllTests.class)
public class VTATest {

	public static TestSuite suite() {
		
		return TestSuiteCreator.createTestSuite(CallGraphAlgorithm.BasicVTA);
		
	}

}
