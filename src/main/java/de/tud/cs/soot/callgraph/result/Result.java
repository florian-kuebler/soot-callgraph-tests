package de.tud.cs.soot.callgraph.result;

import java.util.ArrayList;
import java.util.List;

import soot.SootClass;

public class Result {
	
	
	private List<ResultClass> classes = new ArrayList<>();
	
	
	public List<ResultClass> getClasses() {
		return classes;
	}
	
	public ResultClass addClass(SootClass sootClass){
		ResultClass result = new ResultClass(sootClass);
		this.classes.add(result);
		
		return result;
	}

}
