package callgraph.library.base;

public class ConcreteImplementation implements IBase {

	private int status;

	@Override
	public void changeSth() {
		status++;
	}

	@Override
	public boolean checkSth() {
		return status % 2 == 0;
	}

	@Override
	public ConcreteImplementation doSth() {
		return this;
	}

	@Override
	public void changeSthArg(IBase object) {
		if (object instanceof ConcreteImplementation)
			status = ((ConcreteImplementation) object).status;
	}

	@Override
	public boolean checkSthArg(IBase object) {
		return object instanceof ConcreteImplementation;
	}

	@Override
	public ConcreteImplementation doSthArg(IBase object) {
		if (object instanceof ConcreteImplementation)
			return (ConcreteImplementation) object;
		return this;
	}

}
