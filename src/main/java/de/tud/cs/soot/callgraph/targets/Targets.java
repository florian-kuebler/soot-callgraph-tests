package de.tud.cs.soot.callgraph.targets;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.peaks.sootconfig.entrypointcalculator.AllConcreteMethodsEP;

public class Targets {
	public static AnalysisTarget getDefaultTarget() {
		AnalysisTarget target = new AnalysisTarget();

		target.processPath("./target/test-classes");
		target.setEntryPointCalculator(new AllConcreteMethodsEP());		
		return target;
	}

}
