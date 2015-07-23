package callgraph.library.base;

public class NeverInstantiatedClass extends AbstractBase {

	private boolean status = false;

	@Override
	public void changeSth() {
		status = !status;
	}

	@Override
	public boolean checkSth() {
		return status;
	}

	@Override
	public void changeSthArg(IBase object) {
		status = object.checkSth();

	}

}
