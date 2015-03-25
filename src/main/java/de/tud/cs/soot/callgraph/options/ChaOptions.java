package de.tud.cs.soot.callgraph.options;

import de.tud.cs.peaks.sootconfig.CallGraphPhaseSubOptions;

public class ChaOptions extends CallGraphPhaseSubOptions {
	
	private boolean enabled = false;
	private boolean verbose = false;
	
	public ChaOptions enable() {
		this.enabled = true;
		return this;
	}
	
	public ChaOptions disable() {
		this.enabled = false;
		return this;
	}
	
	public ChaOptions enableVerboseMode() {
		this.verbose = true;
		return this;		
	}
	
	public ChaOptions disableVerboseMode() {
		this.verbose = true;
		return this;		
	}

	public ChaOptions() {
		super("cha");
	}

	@Override
	protected void pushToOptionSet() {
		this.addOption("enabled", this.enabled ? "true" : "false");
		this.addOption("verbose", this.verbose ? "true" : "false");
	}	


}
