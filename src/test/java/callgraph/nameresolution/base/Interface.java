package callgraph.nameresolution.base;

public interface Interface {
	
	void voidWithoutParams();
	
	void voidWithParam(B b);
	
	void voidWithParams(B b1, B b2, A a);
	
	void voidWithMultiArrayParam(B[][][] b);
	
	A nonVoidWithoutParams();
	
	A nonVoidWithParams(B b1, B b2);
	
	A[] arrayReturnWithArrayParam(B[] b);
	
	A[][] multiArrayReturnWithArrayParam(B[] b);

}