package callgraph.nameresolution.base;

public class ConcreteClass implements Interface{
	public void voidWithoutParams() {
		System.out.println();
	}
	
	public void voidWithParam(B b) {
		System.out.println(b);
	}
	
	public void voidWithParams(B b1, B b2, A a) {
		System.err.println(b1);
		System.out.println(b2);
		System.out.println(a);
	}
	
	public void voidWithMultiArrayParam(B[][][] b) {
		System.out.println(b[0][0][0]);
	}
	
	public A nonVoidWithoutParams() {
		return new A();
	}
	
	public A nonVoidWithParams(B b1, B b2) {
		A a = new A();
		voidWithParams(b1, b2, a);
		
		return a;
	}
	
	public A[] arrayReturnWithArrayParam(B[] b) {
		return b;
	}
	
	public A[][] multiArrayReturnWithArrayParam(B[] b) {
		A[][] a = new A[1][b.length];
		
		a[0] = b;
		
		return a;
	}
}
