package callgraph.library;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

import callgraph.library.base.IBase;

public class NativeMethods {
	
	public static native IBase publicNativeStaticMethod();
	
	public native IBase publicNativeMethod();
	
	private native IBase privateNativeMethod();
	
	private native IBase[] getNativeArray();
	
	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation") })
	private void callOnPublicNativeResult() {
		IBase ibase = publicNativeMethod();
		ibase.changeSth();
	}
	
	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation") })
	private void callOnPublicStaticNativeResult() {
		IBase ibase = publicNativeStaticMethod();
		ibase.changeSth();
	}
	
	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation") })
	private void callOnPrivateNativeResult() {
		IBase ibase = privateNativeMethod();
		ibase.changeSth();	
	}
	
	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation") })
	private void callOnNativeResultArray() {
		IBase[] ibase = getNativeArray();
		ibase[0].changeSth();
		
	}
}
