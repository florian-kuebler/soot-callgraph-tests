package callgraph.library.base;

public interface IBase {
	void changeSth();
	boolean checkSth();
	IBase doSth();
	
	void changeSthArg(IBase object);
	boolean checkSthArg(IBase object);
	IBase doSthArg(IBase object);
}
