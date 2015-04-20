package de.tud.cs.soot.callgraph.result;

import java.util.HashSet;
import java.util.Set;

import soot.SootClass;
import soot.SootMethod;

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

	public ResultMethod addMethod(SootMethod sootMethod) {
		
		ResultMethod method = new ResultMethod(sootMethod);
		
		methods.add(method);

		return method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sootClass == null) ? 0 : sootClass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultClass other = (ResultClass) obj;
		if (sootClass == null) {
			if (other.sootClass != null)
				return false;
		} else if (!sootClass.equals(other.sootClass))
			return false;
		return true;
	}

}
