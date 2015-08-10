package callgraph.library;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

import callgraph.library.base.ConcreteClass;
import callgraph.library.base.IBase;

public class Arrays {
	
	private IBase[] privateConstantArrayField = { new ConcreteClass()};
	public IBase[] publicArrayField;
	private IBase[] privateSettableArrayField;
	
	
	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation") })
	public void testPublicArrayField() {
		publicArrayField[0].changeSth();
	}
	
	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass") })
	public void testPrivateConstantArrayField() {
		privateConstantArrayField[0].changeSth();
	}
	

	public void setArrayField(IBase[] array) {
		privateSettableArrayField = array;
	}
	
	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation") })
	public void testSettableArray() {
		privateSettableArrayField[0].changeSth();
	}

}
