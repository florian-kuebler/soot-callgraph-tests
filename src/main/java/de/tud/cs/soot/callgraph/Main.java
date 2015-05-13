package de.tud.cs.soot.callgraph;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.targets.Targets;

public class Main {

	public static void main(String[] args) {
		AnalysisTarget target = Targets.getDefaultTarget();
		
		IMethodMatcher matcher = new DefaultMethodMatcher();
		
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.CHA, target, matcher);
		cca.perform();
		
		
		cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.BasicVTA, target, matcher);
		cca.perform();
	}

}
