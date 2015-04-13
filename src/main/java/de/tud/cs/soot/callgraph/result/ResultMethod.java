package de.tud.cs.soot.callgraph.result;

import java.util.ArrayList;
import java.util.List;

import soot.SootMethod;

public class ResultMethod {

	public ResultMethod(SootMethod sootMethod) {
		this.sootMethod = sootMethod;
		this.calls = new ArrayList<>();
	}

	private SootMethod sootMethod;
	private List<ResultCall> calls;

	public SootMethod getSootMethod() {
		return sootMethod;
	}

	public List<ResultCall> getCalls() {
		return calls;
	}

	public void addCall(ResultCall call) {
		this.calls.add(call);
	}
}
