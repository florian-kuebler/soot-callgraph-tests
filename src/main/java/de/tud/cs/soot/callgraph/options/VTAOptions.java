package de.tud.cs.soot.callgraph.options;


import de.tud.cs.peaks.sootconfig.SparkOptions;

public class VTAOptions extends SparkOptions {
	
	private boolean enabled = false;
	
	public VTAOptions() {
		super();
	}
	
	public VTAOptions enableVTA() {
		this.enable(); // also enable SPARK
		this.enabled = true;
		return this;
	}
	
	public VTAOptions disableVTA() {
		this.enabled = false;
		return this;
	}

	@Override
	protected void pushToOptionSet() {
		super.pushToOptionSet();
		this.addOption("vta", this.enabled ? "true" : "false");
	}	


}
