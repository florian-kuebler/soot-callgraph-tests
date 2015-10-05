package callgraph.library;

import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.*;
import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithmMode.*;

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
	public void testPublicArrayField() {
		publicArrayField[0].changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution, containedInMax = RTA) }), })
	public void testPrivateConstantArrayField() {
		privateConstantArrayField[1].changeSth();
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
	public void testSettableArray() {
		privateSettableArrayField[0].changeSth();
	}

}
