package de.tud.cs.soot.callgraph;

import static org.opalj.annotations.callgraph.CallGraphAlgorithm.BasicVTA;
import static org.opalj.annotations.callgraph.CallGraphAlgorithm.CHA;
import static org.opalj.annotations.callgraph.CallGraphAlgorithm.RTA;
import static org.opalj.annotations.callgraph.CallGraphAlgorithm.SPARK;
import static org.opalj.annotations.callgraph.CallGraphAlgorithmMode.Library;

import java.io.File;
import java.io.IOException;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.result.Result;

public class Main {

	public static void main(String[] args) throws IOException {

		if (args.length != 2)
			throw new IllegalArgumentException("First argument must be target, second rt.jar");

		AnalysisTarget target = new AnalysisTarget();
		target.processPath(args[0]);
		target.classPath(args[1]);
		
		IMethodMatcher matcher = new DefaultMethodMatcher();
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(CHA, Library, target, matcher);
		Result result = cca.perform();
		WorkaroundCSVTraverser csvTraverser = new WorkaroundCSVTraverser("../results/res.csv");
		CSVResultTraverser traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "cha-lib.csv");
		traverser.traverse(result, 0);
		csvTraverser.traverse(result, CHA);
		
		cca = new CorrectCallgraphAnalysis(BasicVTA, Library, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "vta-lib.csv");
		traverser.traverse(result, 0);
		csvTraverser.traverse(result, BasicVTA);
		
		cca = new CorrectCallgraphAnalysis(RTA, Library, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "rta-lib.csv");
		traverser.traverse(result, 0);
		csvTraverser.traverse(result, RTA);

		csvTraverser.write();
		
		cca = new CorrectCallgraphAnalysis(SPARK, Library, target, matcher);
		result = cca.perform();
		traverser = new CSVResultTraverser("." + File.separator + "results"  + File.separator + "spark-lib.csv");
		traverser.traverse(result, 0);
	}

}
