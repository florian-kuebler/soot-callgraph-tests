package de.tud.cs.soot.callgraph.result;

import java.util.ArrayList;
import java.util.List;

import soot.SootClass;
import soot.SootMethod;

public class ResultClass {

	public ResultClass(SootClass sootClass) {
		this.sootClass = sootClass;
		this.methods = new ArrayList<>();
	}
	
	private SootClass sootClass;
	private List<ResultMethod> methods;
	
	public SootClass getSootClass() {
		return sootClass;
	}
	public List<ResultMethod> getMethods() {
		return methods;
	}
	
	public ResultMethod addMethod(SootMethod sootMethod){
		ResultMethod method = new ResultMethod(sootMethod);
		methods.add(method);
		
		return method;
		
	}
	
}
