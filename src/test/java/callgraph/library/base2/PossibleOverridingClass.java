package callgraph.library.base2;

import callgraph.library.base.ConcreteImplementation;
import callgraph.library.base.IBase;

public class PossibleOverridingClass {

	public void changeSth() {
	}

	public boolean checkSth() {
		return false;
	}

	public IBase doSth() {
		return new ConcreteImplementation();
	}

	public void changeSthArg(IBase object) {
		object.changeSth();
	}

	public boolean checkSthArg(IBase object) {
		return object == null;
	}

	public IBase doSthArg(IBase object) {
		return object;
	}

}
