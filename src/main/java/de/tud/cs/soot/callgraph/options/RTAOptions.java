package de.tud.cs.soot.callgraph.options;


import de.tud.cs.peaks.sootconfig.SparkOptions;

public class RTAOptions extends SparkOptions {
	
	private boolean enabled = false;
	
	public RTAOptions() {
		super();
	}
	
	public RTAOptions enableRTA() {
		this.enabled = true;
		enable();
		return this;
	}

	public RTAOptions disableRTA() {
		this.enabled = false;
		return this;
	}

	@Override
	protected void pushToOptionSet() {
		super.pushToOptionSet();
		this.addOption("rta", this.enabled ? "true" : "false");
	}	


}
