package de.tud.cs.soot.callgraph.targets;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.peaks.sootconfig.entrypointcalculator.AllConcreteMethodsEP;

public class Targets {
	public static AnalysisTarget getDefaultTarget() {
		AnalysisTarget target = new AnalysisTarget();

		target.processPath("./../jcg/target/scala-2.10/classes/");
		target.setEntryPointCalculator(AllConcreteMethodsEP.v());
		return target;
	}

}
