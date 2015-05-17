package de.tud.cs.soot.callgraph;

import java.io.File;
import java.io.IOException;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.targets.Targets;

public class Main {

	public static void main(String[] args) throws IOException {
		AnalysisTarget target = Targets.getDefaultTarget();
		
		IMethodMatcher matcher = new DefaultMethodMatcher();
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.CHA, target, matcher);
		Result result = cca.perform();
		CSVResultTraverser traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "cha.csv");
		traverser.traverse(result);
		
		
		cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.BasicVTA, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "vta.csv");
		traverser.traverse(result);
		
		cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.RTA, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "rta.csv");
		traverser.traverse(result);
		
		cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.SPARK, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "spark.csv");
		traverser.traverse(result);
	}

}
