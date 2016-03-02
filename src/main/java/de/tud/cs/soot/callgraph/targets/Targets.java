package de.tud.cs.soot.callgraph.targets;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.peaks.sootconfig.entrypointcalculator.AllConcreteMethodsEP;

import java.io.File;

public class Targets {
	public static AnalysisTarget getDefaultTarget() {
		AnalysisTarget target = new AnalysisTarget();

		//target.processPath("./../jcg/target/scala-2.10/classes/");
		target.processPath("/Users/floriankubler/Documents/git/jcg/target/scala-2.11/classes");
		target.classPath("/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home/jre/lib/rt.jar");
		System.out.println(target.getClassPath());
		target.setEntryPointCalculator(AllConcreteMethodsEP.v());
		return target;
	}

}
