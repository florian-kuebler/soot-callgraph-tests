package callgraph.library;

import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.*;
import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;
import org.opalj.ai.test.invokedynamic.annotations.ResolvingCondition;

import callgraph.library.base.ConcreteClass;
import callgraph.library.base.ConcreteImplementation;
import callgraph.library.base.IBase;

public class Arrays {

	private IBase[] privateConstantArrayField = { new ConcreteClass(), new ConcreteImplementation() };
	public IBase[] publicArrayField;
	private IBase[] privateSettableArrayField;

	public void setArrayField(IBase[] array) {
		privateSettableArrayField = array;
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(onlyOnLibrary = true, onlyOnNameResolution = true) }),
	})
	public void testPublicArrayField() {
		publicArrayField[0].changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(onlyOnLibrary = true, containedInMax = CHA ) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true, containedInMax = CHA ) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true, containedInMax = RTA ) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true, containedInMax = RTA ) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(onlyOnLibrary = true, containedInMax = RTA ) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(onlyOnLibrary = true, onlyOnNameResolution = true, containedInMax = RTA ) }),
	})
	public void testPrivateConstantArrayField() {
		privateConstantArrayField[1].changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(onlyOnLibrary = true) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(onlyOnLibrary = true, onlyOnNameResolution = true) }),
	})
	public void testSettableArray() {
		privateSettableArrayField[0].changeSth();
	}

}
