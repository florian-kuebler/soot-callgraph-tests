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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		ChaOptions that = (ChaOptions) o;

		if (enabled != that.enabled) return false;
		return verbose == that.verbose;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (enabled ? 1 : 0);
		result = 31 * result + (verbose ? 1 : 0);
		return result;
	}
}
