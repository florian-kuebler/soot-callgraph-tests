package callgraph.library.base;

public class NeverInstantiatedImplementation implements IBase {

	@Override
	public void changeSth() {
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
		return object == null;
	}

	@Override
	public IBase doSthArg(IBase object) {
		return this;
	}

}
