package callgraph.library;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

public class CombiningMultipleTargets {
	@CallSite(name = "instantiate", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/CombiningMultipleTargets")})
	public Object foo(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(name);
		Object instance = instantiate(clazz);
		return instance;
	}

	@CallSite(name = "newInstance", resolvedMethods = {
			@ResolvedMethod(receiverType = "java/lang/reflect/Constructor")})
	@CallSite(name = "getConstructor", resolvedMethods = {
			@ResolvedMethod(receiverType = "java/lang/Class")})
	private Object instantiate(Class<?> clazz) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<?>[] constructors = clazz.getConstructors();
		Object instance = constructors[0].newInstance();
		return instance;
	}

}
