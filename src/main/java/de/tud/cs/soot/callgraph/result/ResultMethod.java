package de.tud.cs.soot.callgraph.result;

import java.util.HashSet;
import java.util.Set;

import soot.SootMethod;

public class ResultMethod {

	private SootMethod sootMethod;
	private Set<ResultCall> calls;

	public ResultMethod(SootMethod sootMethod) {
		this.sootMethod = sootMethod;
		this.calls = new HashSet<>();
	}

	public SootMethod getSootMethod() {
		return sootMethod;
	}

	public Set<ResultCall> getCalls() {
		return calls;
	}

	public void addCall(ResultCall call) {
		this.calls.add(call);
	}
}
