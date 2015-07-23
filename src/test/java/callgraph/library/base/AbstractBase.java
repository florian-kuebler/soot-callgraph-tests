package callgraph.library.base;

public abstract class AbstractBase implements IBase {

	@Override
	public AbstractBase doSth() {
		return this;
	}

	@Override
	public boolean checkSthArg(IBase object) {
		return object == null;
	}

	@Override
	public IBase doSthArg(IBase object) {
		if (System.currentTimeMillis() % 2 == 0)
			return this;
		else
			return object;
	}

}
