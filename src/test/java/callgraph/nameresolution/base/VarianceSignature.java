package callgraph.nameresolution.base;

public class VarianceSignature {
	
	public void voidWithoutParams() {
		System.out.println();
	}
	
	public void voidWithParam(A b) {
		System.out.println(b);
	}
	
	public void voidWithParams(A b1, A b2, A a) {
		System.err.println(b1);
		System.out.println(b2);
		System.out.println(a);
	}
	
	public void voidWithMultiArrayParam(A[][][] b) {
		System.out.println(b[0][0][0]);
	}
	
	public B nonVoidWithoutParams() {
		return new B();
	}
	
	public B nonVoidWithParams(A b1, A b2) {
		B a = new B();
		voidWithParams(b1, b2, a);
		
		return a;
	}
	
	public A[] arrayReturnWithArrayParam(A[] b) {
		return b.clone();
	}
	
	public B[][] multiArrayReturnWithArrayParam(B[] b) {
		B[][] a = new B[1][b.length];
		
		a[0] = b;
		
		return a;
	}
}
