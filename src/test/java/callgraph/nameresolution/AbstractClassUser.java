package callgraph.nameresolution;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;

import callgraph.nameresolution.base.A;
import callgraph.nameresolution.base.AbstractClass;
import callgraph.nameresolution.base.B;

public class AbstractClassUser {

	@CallSite(name = "voidWithoutParams", resolvedMethods = { })
	public void useVoidWithoutParams(AbstractClass i) {
		i.voidWithoutParams();
	}
	
	@CallSite(name = "voidWithParam", resolvedMethods = { })
	public void useVoidWithParam(AbstractClass i, B b) {
		i.voidWithParam(b);
	}
	
	@CallSite(name = "voidWithParams", resolvedMethods = { })
	public void useVoidWithParams(AbstractClass i, B b1, B b2, A a) {
		i.voidWithParams(b1, b2, a);
	}
	
	@CallSite(name = "voidWithMultiArrayParam", resolvedMethods = { })
	public void useVoidWithMultiArrayParam(AbstractClass i, B[][][] b) {
		i.voidWithMultiArrayParam(b);
	}
	
	@CallSite(name = "nonVoidWithoutParams", resolvedMethods = { })
	public A useNonVoidWithoutParams(AbstractClass i) {
		return i.nonVoidWithoutParams();
	}
	
	@CallSite(name = "nonVoidWithParams", resolvedMethods = { })
	public A useNonVoidWithParams(AbstractClass i, B b1, B b2) {
		return i.nonVoidWithParams(b1, b2);
	}
	
	@CallSite(name = "arrayReturnWithArrayParam", resolvedMethods = { })
	public A[] useArrayReturnWithArrayParam(AbstractClass i, B[] b) {
		return i.arrayReturnWithArrayParam(b);
	}
	
	@CallSite(name = "multiArrayReturnWithArrayParam", resolvedMethods = { })
	public A[][] useMultiArrayReturnWithArrayParam(AbstractClass i, B[] b) {
		return i.multiArrayReturnWithArrayParam(b);
	}

}
