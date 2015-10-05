package callgraph.library;

import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithmMode.*;
import static org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm.*;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;
import org.opalj.ai.test.invokedynamic.annotations.ResolvingCondition;

import callgraph.library.base.AbstractBase;
import callgraph.library.base.BaseProvider;
import callgraph.library.base.ConcreteClass;
import callgraph.library.base.ConcreteImplementation;
import callgraph.library.base.IBase;

public class LibraryClass {
	private class InnerClass extends AbstractBase {

		private long status = 0l;

		@Override
		public void changeSth() {
			status++;
		}

		@Override
		public boolean checkSth() {
			return status % 2 == 0;
		}

		@Override
		public void changeSthArg(IBase object) {
			if (object == null)
				status = 0l;
		}

	}

	private class InnerImplementation implements IBase {

		@Override
		public void changeSth() {
			System.out.println("");
		}

		@Override
		public boolean checkSth() {
			return false;
		}

		@Override
		public IBase doSth() {
			return this;
		}

		@Override
		public void changeSthArg(IBase object) {
			object.changeSth();
		}

		@Override
		public boolean checkSthArg(IBase object) {
			return object == this;
		}

		@Override
		public IBase doSthArg(IBase object) {
			return object;
		}
	}

	private IBase ibase;
	private IBase onlyAbstractBase;
	private IBase unmodifiableIBase;

	protected IBase protectedIBase;
	public IBase publicIBase;
	public AbstractBase abase;
	public ConcreteClass cclass;
	public ConcreteImplementation cimpl;
	public InnerClass iclass;
	public InnerImplementation iimpl;

	public static IBase staticIBase;

	public void init() {
		ibase = BaseProvider.ibase;
		onlyAbstractBase = new ConcreteClass();
		unmodifiableIBase = new ConcreteImplementation();
		abase = new ConcreteClass();
		cclass = new ConcreteClass();
		cimpl = new ConcreteImplementation();
		iclass = new InnerClass();
		iimpl = new InnerImplementation();

	}

	public void setBase(IBase set) {
		ibase = set;
	}

	public void setAbstractBase(AbstractBase set) {
		onlyAbstractBase = set;
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
	public static void callOnArgBase(IBase arg) {
		arg.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public static void callOnArgAbstract(AbstractBase arg) {
		arg.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public static void callOnArgConcrete(ConcreteClass arg) {
		arg.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public static void callOnArgConcreteImplementation(ConcreteImplementation arg) {
		arg.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public static void callOnArgInnerClass(InnerClass arg) {
		arg.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public static void callOnArgInnerImplementation(InnerImplementation arg) {
		arg.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1"),
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
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public void callOnFieldBase() {
		if (ibase != null)
			ibase.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = {
					@ResolvingCondition(mode = LibraryWithNameResolution), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = {
					@ResolvingCondition(mode = LibraryWithNameResolution), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = {
					@ResolvingCondition(mode = LibraryWithNameResolution), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = {
					@ResolvingCondition(mode = LibraryWithNameResolution), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public void callOnFieldAbstract() {
		if (onlyAbstractBase != null)
			onlyAbstractBase.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = {
					@ResolvingCondition(mode = Library, containedInMax = RTA),
					@ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library, containedInMax = RTA),
					@ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library, containedInMax = CHA),
					@ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = {
					@ResolvingCondition(mode = Library, containedInMax = RTA),
					@ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = {
					@ResolvingCondition(mode = Library, containedInMax = CHA),
					@ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = {
					@ResolvingCondition(mode = Library, containedInMax = RTA),
					@ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution, containedInMax = RTA) }), })
	public void callOnFieldUnmodifiable() {
		if (unmodifiableIBase != null)
			unmodifiableIBase.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public void callOnFieldConcrete() {
		cclass.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass"),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public void callOnFieldInnerClass() {
		iclass.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public void callOnFieldInnerImplementation() {
		iimpl.changeSth();
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
	public void callOnFieldPublicIBase() {
		publicIBase.changeSth();
	}

	@CallSite(name = "changeSth", resolvedMethods = {
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteClass"),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = RTA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedClass", iff = {
					@ResolvingCondition(mode = Library), @ResolvingCondition(containedInMax = CHA) }),
			@ResolvedMethod(receiverType = "callgraph/library/base2/PossibleOverridingClass", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/ConcreteImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/LibraryClass$InnerImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/NeverInstantiatedImplementation", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }),
			@ResolvedMethod(receiverType = "callgraph/library/base/BaseProvider$1", iff = { @ResolvingCondition(mode = LibraryWithNameResolution) }), })
	public void callOnFieldPublicAbstractBase() {
		abase.changeSth();
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
	public void callOnFieldProtectedIBase() {
		protectedIBase.changeSth();
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
	public void callOnFieldStaticIBase() {
		staticIBase.changeSth();
	}

}
