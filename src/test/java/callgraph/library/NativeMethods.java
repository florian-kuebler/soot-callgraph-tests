package callgraph.library;

import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.*;
import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithmMode.*;
import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;
import org.opalj.ai.test.invokedynamic.annotations.ResolvingCondition;

import callgraph.library.base.IBase;

public class NativeMethods {

	public static native IBase publicNativeStaticMethod();

	public native IBase publicNativeMethod();

	private native IBase privateNativeMethod();

	private native IBase[] getNativeArray();

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	@CallSite(name = "publicNativeMethod", resolvedMethods = { @ResolvedMethod(receiverType = "callgraph/library/NativeMethods") })
	private void callOnPublicNativeResult() {
		IBase ibase = publicNativeMethod();
		ibase.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	@CallSite(name = "publicNativeStaticMethod", resolvedMethods = { @ResolvedMethod(receiverType = "callgraph/library/NativeMethods") })
	private void callOnPublicStaticNativeResult() {
		IBase ibase = publicNativeStaticMethod();
		ibase.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	@CallSite(name = "privateNativeMethod", resolvedMethods = { @ResolvedMethod(receiverType = "callgraph/library/NativeMethods") })
	private void callOnPrivateNativeResult() {
		IBase ibase = privateNativeMethod();
		ibase.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	@CallSite(name = "getNativeArray", resolvedMethods = { @ResolvedMethod(receiverType = "callgraph/library/NativeMethods") })
	public void callOnNativeResultArray() {
		IBase[] ibase = getNativeArray();
		ibase[0].changeSth();

	}
}
