package de.tud.cs.soot.callgraph.util;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

public class MethodUtils {
	public static String toSootMethodStyle(InvokedMethod m) {
		if (m == null) {
			throw new RuntimeException();
		}
		StringBuilder sb = new StringBuilder("<");
		sb.append(m.receiverType().replace('/', '.'));
		sb.append(": ");
		sb.append(m.returnType().getName());
		sb.append(" ");
		sb.append(m.name());
		sb.append("(");
		Class<?>[] parameterTypes = m.parameterTypes();
		for (int i = 0; i < parameterTypes.length; i++) {
			sb.append(parameterTypes[i].getName());
			
			if(parameterTypes.length > 0 && i < parameterTypes.length - 1){
				sb.append(", ");
			}
		}

		sb.append(")>");

		return sb.toString();
	}

	public static String toQuallifiedClassName(String name) {
		name = name.substring(1, name.length() - 1);
		name = name.replace('/', '.');
		return name;
	}
}
