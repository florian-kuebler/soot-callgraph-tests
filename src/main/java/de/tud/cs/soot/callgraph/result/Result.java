package de.tud.cs.soot.callgraph.result;

import java.util.HashSet;
import java.util.Set;

import soot.SootClass;

public class Result {
	private Set<ResultClass> classes;

	public Result() {
		this.classes = new HashSet<>();
	}

	public Set<ResultClass> getClasses() {
		return classes;
	}

	public ResultClass addClass(SootClass sootClass) {
		ResultClass result = new ResultClass(sootClass);
		this.classes.add(result);

		return result;
	}

}
