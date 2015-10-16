package de.tud.cs.soot.callgraph.util;

import org.opalj.test.annotations.CallSite;
import org.opalj.test.annotations.ResolvedMethod;

public class MethodUtils {

	public static String toQuallifiedClassName(String name) {
		name = name.substring(1, name.length() - 1);
		name = name.replace('/', '.');
		return name;
	}

	public static String toSootMethodStyle(CallSite callSite,
			ResolvedMethod resolvedMethod) {
		if (callSite == null || resolvedMethod == null) {
			throw new RuntimeException();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(resolvedMethod.receiverType().replace('/', '.'));
		sb.append(": ");
		sb.append(callSite.returnType().getName());
		sb.append(" ");
		sb.append(callSite.name());
		sb.append("(");
		Class<?>[] parameterTypes = callSite.parameterTypes();
		for (int i = 0; i < parameterTypes.length; i++) {
			sb.append(parameterTypes[i].getName());
			
			if(parameterTypes.length > 0 && i < parameterTypes.length - 1){
				sb.append(", ");
			}
		}

		sb.append(")");

		return sb.toString();
	}
}
