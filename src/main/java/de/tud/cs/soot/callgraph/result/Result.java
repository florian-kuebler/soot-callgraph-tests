package de.tud.cs.soot.callgraph.result;

import java.util.HashSet;
import java.util.Set;

public class Result {
	private Set<ResultClass> classes;

	public Result() {
		this.classes = new HashSet<>();
	}

	public Set<ResultClass> getClasses() {
		return classes;
	}

	public void addClass(ResultClass clazz) {
		this.classes.add(clazz);
	}

}
