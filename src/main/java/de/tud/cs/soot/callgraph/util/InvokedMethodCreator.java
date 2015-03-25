package de.tud.cs.soot.callgraph.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;
import org.opalj.ai.test.invokedynamic.annotations.TargetResolution;

import soot.tagkit.AnnotationArrayElem;
import soot.tagkit.AnnotationBooleanElem;
import soot.tagkit.AnnotationClassElem;
import soot.tagkit.AnnotationElem;
import soot.tagkit.AnnotationEnumElem;
import soot.tagkit.AnnotationIntElem;
import soot.tagkit.AnnotationStringElem;
import soot.tagkit.AnnotationTag;

public class InvokedMethodCreator {

	private TargetClassLoader cl;
	private EnumConstantCreator ecc;

	public InvokedMethodCreator(TargetClassLoader cl) {
		this.cl = cl;
		this.ecc = new EnumConstantCreator(cl);
	}

	public InvokedMethod create(AnnotationTag at) {
		Map<String, String> stringElems = new HashMap<>();
		Map<String, Class<?>> classElems = new HashMap<>();
		Map<String, Class<?>[]> classAElems = new HashMap<>();
		Map<String, Integer> intElems = new HashMap<>();
		Map<String, Boolean> boolElems = new HashMap<>();
		Map<String, TargetResolution> resElems = new HashMap<>();
		Map<String, CallGraphAlgorithm[]> cgaElems = new HashMap<>();

		for (AnnotationElem elem : at.getElems()) {

			/*
			 * Retrieve receiverType and name
			 */
			if (elem instanceof AnnotationStringElem) {
				AnnotationStringElem sElem = ((AnnotationStringElem) elem);
				stringElems.put(sElem.getName(), sElem.getValue());
			}

			/*
			 * Retrieve returnType
			 */
			else if (elem instanceof AnnotationClassElem) {
				AnnotationClassElem cElem = (AnnotationClassElem) elem;
				String clazzName = TargetClassLoader.toQuallifiedClassName(cElem.getDesc());
				Class<?> clazz = cl.loadClass(clazzName);
				classElems.put(cElem.getName(), clazz);
			}

			/*
			 * Retrieve parameterTypes and isContaintedIn
			 */
			else if (elem instanceof AnnotationArrayElem) {
				AnnotationArrayElem aElem = (AnnotationArrayElem) elem;
				Class<?>[] paramTypes = retrieveParameterType(aElem);
				CallGraphAlgorithm[] cgas = retrieveIsContainedIn(aElem);
				if (cgas != null) {
					cgaElems.put(aElem.getName(), cgas);
				} else if (paramTypes != null) {
					classAElems.put(aElem.getName(), paramTypes);
				} else {
					throw new RuntimeException("Unsupported AnnotationArrayElem");
				}

			}

			/*
			 * Retrieve line and if loading class files also: isStatic and
			 * isReflective
			 */
			else if (elem instanceof AnnotationIntElem) {

				AnnotationIntElem iElem = (AnnotationIntElem) elem;
				intElems.put(iElem.getName(), iElem.getValue());
			}

			/*
			 * Retrieve isStatic and isReflective
			 */
			else if (elem instanceof AnnotationBooleanElem) {
				AnnotationBooleanElem bElem = (AnnotationBooleanElem) elem;
				boolElems.put(bElem.getName(), bElem.getValue());

			}

			/*
			 * Retrieve resolution
			 */
			else if (elem instanceof AnnotationEnumElem) {
				TargetResolution resolution = ecc.create((AnnotationEnumElem) elem);
				resElems.put(elem.getName(), resolution);
			}

			/*
			 * Found an AnnotationElem that is not part of InvokedMethod
			 */
			else {
				throw new RuntimeException("Unexpected AnnotationElem");
			}
		}

		final String receiverType = stringElems.remove("receiverType");
		if (receiverType == null) {
			throw new RuntimeException("receiverType must not be null");
		}

		final String name = stringElems.remove("name");
		if (name == null) {
			throw new RuntimeException("name must not be null");
		}

		Class<?> returnType = classElems.remove("returnType");
		Class<?>[] parameterTypes = classAElems.remove("parameterTypes");
		Integer line = intElems.remove("line");
		Boolean isStatic = retrieveBoolean("isStatic", boolElems, intElems);
		Boolean isReflective = retrieveBoolean("isReflective", boolElems, intElems);
		TargetResolution resolution = resElems.remove("resolution");
		CallGraphAlgorithm[] isContainedIn = cgaElems.remove("isContainedIn");

		if (!classElems.isEmpty() || !classAElems.isEmpty() || !intElems.isEmpty() || !boolElems.isEmpty()
				|| !resElems.isEmpty() || !cgaElems.isEmpty()) {
			throw new RuntimeException("To many AnnotationElems");
		}

		return newInvokedMethod(receiverType, name, returnType, parameterTypes, line, isStatic, isReflective,
				resolution, isContainedIn);
	}

	private Boolean retrieveBoolean(String name, Map<String, Boolean> boolElems, Map<String, Integer> intElems) {
		Boolean result = null;
		Object tmp = boolElems.remove(name);
		if (tmp != null) {
			result = (Boolean) tmp;
		} else {
			tmp = intElems.remove(name);
			if (tmp != null) {
				result = (((Integer) tmp) == 1) ? true : false;
			}
		}
		return result;
	}

	private InvokedMethod newInvokedMethod(final String receiverType, final String name, final Class<?> returnType,
			final Class<?>[] parameterTypes, final Integer line, final Boolean isStatic, final Boolean isReflective,
			final TargetResolution resolution, final CallGraphAlgorithm[] isContainedIn) {
		return new InvokedMethod() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return InvokedMethod.class;
			}

			@Override
			public Class<?> returnType() {
				if (returnType == null)
					return Void.class;
				return returnType;
			}

			@Override
			public TargetResolution resolution() {
				if (resolution == null)
					return TargetResolution.DEFAULT;
				return resolution;
			}

			@Override
			public String receiverType() {

				return receiverType;
			}

			@Override
			public Class<?>[] parameterTypes() {
				return parameterTypes;
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public int line() {
				if (line == null) {
					return -1;
				}
				return line;
			}

			@Override
			public boolean isStatic() {
				if (isStatic == null)
					return false;
				return isStatic;
			}

			@Override
			public boolean isReflective() {
				if (isReflective == null)
					return false;
				return isReflective;
			}

			@Override
			public CallGraphAlgorithm[] isContainedIn() {
				if (isContainedIn == null) {
					CallGraphAlgorithm[] cgas = { CallGraphAlgorithm.CHA, CallGraphAlgorithm.BasicVTA,
							CallGraphAlgorithm.DefaultVTA, CallGraphAlgorithm.ExtVTA, CallGraphAlgorithm.CFA };
					return cgas;
				}
				return isContainedIn;
			}
		};
	}

	private Class<?>[] retrieveParameterType(AnnotationArrayElem aElem) {
		List<Class<?>> classes = new ArrayList<>();
		for (AnnotationElem e : aElem.getValues()) {
			if (e instanceof AnnotationClassElem) {
				AnnotationClassElem cElem = (AnnotationClassElem) e;
				String clazz = TargetClassLoader.toQuallifiedClassName(cElem.getDesc());
				classes.add(cl.loadClass(clazz));
			} else
				return null;
		}
		return classes.toArray(new Class<?>[classes.size()]);
	}

	private CallGraphAlgorithm[] retrieveIsContainedIn(AnnotationArrayElem aElem) {
		List<CallGraphAlgorithm> cgas = new ArrayList<>();
		for (AnnotationElem e : aElem.getValues()) {
			if (e instanceof AnnotationEnumElem) {
				CallGraphAlgorithm cg = ecc.create((AnnotationEnumElem) e);
				cgas.add(cg);
			} else
				return null;
		}
		return cgas.toArray(new CallGraphAlgorithm[cgas.size()]);
	}



}
