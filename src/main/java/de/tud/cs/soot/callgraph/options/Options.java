package de.tud.cs.soot.callgraph.options;

import de.tud.cs.peaks.sootconfig.CallGraphPhaseOptions;
import de.tud.cs.peaks.sootconfig.FluentOptions;
import de.tud.cs.peaks.sootconfig.JimpleBodyCreationPhaseOptions;
import de.tud.cs.peaks.sootconfig.TagAggregatorOptions;

public class Options {
	
	private static FluentOptions getBasicFluentOptions(){
		FluentOptions options = new FluentOptions().
				keepLineNumbers().
				fullResolver()
				.noBodiesForExcluded().
				allowPhantomReferences().
				wholeProgramAnalysis().
				outputFormat("none")
				.prependClasspath().
				addPhaseOptions(new JimpleBodyCreationPhaseOptions().useOriginalNames())
				.addPhaseOptions(new TagAggregatorOptions().aggregateLineNumber());
		
		return options;
	}
	
	public static FluentOptions getCHAFluentOptions(){
		FluentOptions options = getBasicFluentOptions();
		
		CallGraphPhaseOptions cg = new CallGraphPhaseOptions().processAllReachable();
		ChaOptions cgCha = new ChaOptions().enable().disableVerboseMode();
		cg.addSubOption(cgCha);
		
		options.addPhaseOptions(cg);
		
		return options;
	}
	
	public static FluentOptions getVTAFluentOptions(){
		FluentOptions options = getBasicFluentOptions();
		
		CallGraphPhaseOptions cg = new CallGraphPhaseOptions().processAllReachable();
		cg.addSubOption(new VTAOptions().enableVTA().disableVerboseMode());
		
		options.addPhaseOptions(cg);
		
		return options;
	}	
	
	public static FluentOptions getRTAFluentOptions(){
		FluentOptions options = getBasicFluentOptions();
		
		CallGraphPhaseOptions cg = new CallGraphPhaseOptions().processAllReachable();
		cg.addSubOption(new RTAOptions().enableRTA().disableVerboseMode());
		
		options.addPhaseOptions(cg);
		
		return options;
	}
}
