package de.tud.cs.soot.callgraph.testsuite;

import static org.opalj.annotations.callgraph.CallGraphAlgorithm.BasicVTA;
import static org.opalj.annotations.callgraph.CallGraphAlgorithm.CHA;
import static org.opalj.annotations.callgraph.CallGraphAlgorithm.RTA;
import static org.opalj.annotations.callgraph.CallGraphAlgorithm.SPARK;
import static org.opalj.annotations.callgraph.CallGraphAlgorithmMode.Library;
import static org.opalj.annotations.callgraph.CallGraphAlgorithmMode.Application;
import static org.opalj.annotations.callgraph.CallGraphAlgorithmMode.LibraryWithNameResolution;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import de.tud.cs.soot.callgraph.tests.TestSuiteCreator;

@RunWith(AllTests.class)
public class ALLTests {
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite("CallGraph Tests");
		int i = 0;
		suite.addTest(TestSuiteCreator.createTestSuite(CHA, Application, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(CHA, Library, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(CHA, LibraryWithNameResolution, i++));

		suite.addTest(TestSuiteCreator.createTestSuite(RTA, Application, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(RTA, Library, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(RTA, LibraryWithNameResolution, i++));
		
		suite.addTest(TestSuiteCreator.createTestSuite(BasicVTA, Application, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(BasicVTA, Library, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(BasicVTA, LibraryWithNameResolution, i++));

		suite.addTest(TestSuiteCreator.createTestSuite(SPARK, Application, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(SPARK, Library, i++));
		suite.addTest(TestSuiteCreator.createTestSuite(SPARK, LibraryWithNameResolution, i++));
		
		return suite;
	}

}
