package de.tud.cs.soot.callgraph;

import java.util.function.BiConsumer;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;
import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.soot.callgraph.targets.Targets;

public class Main {

	public static void main(String[] args) {
		AnalysisTarget target = Targets.getDefaultTarget();
		

		BiConsumer<SootMethod, InvokedMethod> pass = (sm, im) -> {
		};
		
		BiConsumer<SootMethod, InvokedMethod> miss = (sm, im) -> {
			new CallNotFoundException(sm, im).printStackTrace();
		};
		
		IMethodMatcher matcher = new NameAndRecieverMatcher();
		
		CorrectCallgraphAnalysis cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.CHA, target, matcher, pass, miss);
		cca.perform();
		
		
		cca = new CorrectCallgraphAnalysis(CallGraphAlgorithm.BasicVTA, target, matcher, pass, miss);
		cca.perform();
	}

}
