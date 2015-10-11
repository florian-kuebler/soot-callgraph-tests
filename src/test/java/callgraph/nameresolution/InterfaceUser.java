package callgraph.nameresolution;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithmMode;
import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;
import org.opalj.ai.test.invokedynamic.annotations.ResolvingCondition;

import callgraph.nameresolution.base.A;
import callgraph.nameresolution.base.B;
import callgraph.nameresolution.base.Interface;

public class InterfaceUser {

	@CallSite(name = "voidWithoutParams", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public void useVoidWithoutParams(Interface i) {
		i.voidWithoutParams();
	}

	@CallSite(name = "voidWithParam", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public void useVoidWithParam(Interface i, B b) {
		i.voidWithParam(b);
	}

	@CallSite(name = "voidWithParams", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public void useVoidWithParams(Interface i, B b1, B b2, A a) {
		i.voidWithParams(b1, b2, a);
	}

	@CallSite(name = "voidWithMultiArrayParam", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public void useVoidWithMultiArrayParam(Interface i, B[][][] b) {
		i.voidWithMultiArrayParam(b);
	}

	@CallSite(name = "nonVoidWithoutParams", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public A useNonVoidWithoutParams(Interface i) {
		return i.nonVoidWithoutParams();
	}

	@CallSite(name = "nonVoidWithParams", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public A useNonVoidWithParams(Interface i, B b1, B b2) {
		return i.nonVoidWithParams(b1, b2);
	}

	@CallSite(name = "arrayReturnWithArrayParam", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public A[] useArrayReturnWithArrayParam(Interface i, B[] b) {
		return i.arrayReturnWithArrayParam(b);
	}

	@CallSite(name = "multiArrayReturnWithArrayParam", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/ConcreteClass", iff = { @ResolvingCondition(containedInMax = CallGraphAlgorithm.CHA) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/IdenticSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/nameresolution/base/VarianceSignature", iff = { @ResolvingCondition(mode = CallGraphAlgorithmMode.LibraryWithNameResolution) }), })
	public A[][] useMultiArrayReturnWithArrayParam(Interface i, B[] b) {
		return i.multiArrayReturnWithArrayParam(b);
	}

}
