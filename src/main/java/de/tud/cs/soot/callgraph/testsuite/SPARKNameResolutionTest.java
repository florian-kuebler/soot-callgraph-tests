package de.tud.cs.soot.callgraph.testsuite;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithmMode;

import de.tud.cs.soot.callgraph.tests.TestSuiteCreator;


@RunWith(AllTests.class)
public class SPARKNameResolutionTest {

	public static TestSuite suite() {

		return TestSuiteCreator.createTestSuite(CallGraphAlgorithm.SPARK, CallGraphAlgorithmMode.LibraryWithNameResolution, 0);

	}

}
