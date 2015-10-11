package callgraph.nameresolution.base;

public abstract class AbstractClass {
	
	public abstract void voidWithoutParams();
	
	public abstract void voidWithParam(B b);
	
	public abstract void voidWithParams(B b1, B b2, A a);
	
	public abstract void voidWithMultiArrayParam(B[][][] b);
	
	public abstract A nonVoidWithoutParams();
	
	public abstract A nonVoidWithParams(B b1, B b2);
	
	public abstract A[] arrayReturnWithArrayParam(B[] b);
	
	public abstract A[][] multiArrayReturnWithArrayParam(B[] b);

}
