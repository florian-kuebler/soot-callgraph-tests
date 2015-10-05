package de.tud.cs.soot.callgraph;

import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.BasicVTA;
import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.CHA;
import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.RTA;
import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.SPARK;
import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithmMode.Library;

import java.io.File;
import java.io.IOException;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.targets.Targets;

public class Main {

	public static void main(String[] args) throws IOException {
		AnalysisTarget target = Targets.getDefaultTarget();
		
		IMethodMatcher matcher = new DefaultMethodMatcher();
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(CHA, Library, target, matcher);
		Result result = cca.perform();
		CSVResultTraverser traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "cha-lib.csv");
		traverser.traverse(result, 0);
		
		cca = new CorrectCallgraphAnalysis(BasicVTA, Library, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "vta-lib.csv");
		traverser.traverse(result, 0);
		
		cca = new CorrectCallgraphAnalysis(RTA, Library, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "rta-lib.csv");
		traverser.traverse(result, 0);
		
		cca = new CorrectCallgraphAnalysis(SPARK, Library, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "spark-lib.csv");
		traverser.traverse(result, 0);
	}

}
