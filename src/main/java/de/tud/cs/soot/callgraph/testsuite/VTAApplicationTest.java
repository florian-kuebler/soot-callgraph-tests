package de.tud.cs.soot.callgraph.testsuite;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.opalj.annotations.callgraph.CallGraphAlgorithm;
import org.opalj.annotations.callgraph.CallGraphAlgorithmMode;

import de.tud.cs.soot.callgraph.tests.TestSuiteCreator;


@RunWith(AllTests.class)
public class VTAApplicationTest {

	public static TestSuite suite() {

		return TestSuiteCreator.createTestSuite(CallGraphAlgorithm.BasicVTA, CallGraphAlgorithmMode.Application , 0);

	}

}
