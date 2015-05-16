package de.tud.cs.soot.callgraph.result;

import java.util.HashSet;
import java.util.Set;

import soot.SootClass;

public class ResultClass {
	private SootClass sootClass;
	private Set<ResultMethod> methods;

	public ResultClass(SootClass sootClass) {
		this.sootClass = sootClass;
		this.methods = new HashSet<>();
	}

	public SootClass getSootClass() {
		return sootClass;
	}

	public Set<ResultMethod> getMethods() {
		return methods;
	}

	public void addMethod(ResultMethod method) {
		methods.add(method);
	}

	public void addMethods(Set<ResultMethod> methods) {
		this.methods.addAll(methods);
	}

}
