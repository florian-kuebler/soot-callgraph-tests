package de.tud.cs.soot.callgraph.options;

import org.opalj.test.annotations.CallGraphAlgorithm;
import org.opalj.test.annotations.CallGraphAlgorithmMode;
import de.tud.cs.peaks.sootconfig.CallGraphPhaseOptions;
import de.tud.cs.peaks.sootconfig.FluentOptions;
import de.tud.cs.peaks.sootconfig.JimpleBodyCreationPhaseOptions;
import de.tud.cs.peaks.sootconfig.SparkOptions;
import de.tud.cs.peaks.sootconfig.TagAggregatorOptions;

public class Options {

	private static FluentOptions getBasicFluentOptions() {
		FluentOptions options = new FluentOptions().keepLineNumbers().fullResolver().noBodiesForExcluded()
				.allowPhantomReferences().wholeProgramAnalysis().outputFormat("jimple").prependClasspath()
				.addPhaseOptions(new JimpleBodyCreationPhaseOptions().useOriginalNames())
				.addPhaseOptions(new TagAggregatorOptions().aggregateLineNumber());

		return options;
	}
	
	public static FluentOptions getFluentOptions(CallGraphAlgorithm cga, CallGraphAlgorithmMode mode) {
		FluentOptions options = getBasicFluentOptions();
		
		CallGraphPhaseOptions cg = new CallGraphPhaseOptions().processAllReachable();
		
		switch (mode) {
		case Application:
			cg.applicationMode();
			break;
		case Library:
			cg.libraryMode();
			break;
		case LibraryWithNameResolution:
			cg.libraryModeWithNameResolution();
			break;
		}
		
		switch (cga) {
		case CHA:
			cg.addSubOption(new ChaOptions().enable());
			break;
		case BasicVTA:
			cg.addSubOption(new VTAOptions().enableVTA());
			break;
		case SPARK:
			cg.addSubOption(new SparkOptions().enable());
			break;
		case RTA:
			cg.addSubOption(new RTAOptions().enableRTA());
			break;
		default:
			throw new RuntimeException("CallGraphAlgorithm: " + cga.name() + " is not yet implemented");
		}
		
		options.addPhaseOptions(cg);
		
		return options;
	}
}
