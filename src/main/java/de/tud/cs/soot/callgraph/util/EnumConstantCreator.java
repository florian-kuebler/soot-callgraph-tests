package de.tud.cs.soot.callgraph.util;

import soot.tagkit.AnnotationEnumElem;

public class EnumConstantCreator {
	
	private TargetClassLoader cl;
	

	public EnumConstantCreator(TargetClassLoader cl) {
		this.cl = cl;
	}

	public <T extends Enum<?>> T create(AnnotationEnumElem eElem) {
		String clazzName = MethodUtils.toQuallifiedClassName(eElem.getTypeName());
		Class<?> clazz = cl.loadClass(clazzName);

		T result = null;
		for (Object o : clazz.getEnumConstants()) {
			try {
				@SuppressWarnings("unchecked")
				T t = (T) o;
				if (t.name().equals(eElem.getConstantName())) {
					result = t;
					break;
				}
			} catch (ClassCastException e) {
				throw new RuntimeException();
			}

		}
		if (result == null) {
			throw new RuntimeException();
		}
		return result;
	}


}
