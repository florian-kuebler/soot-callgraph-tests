package callgraph.nameresolution.base;

public class WrongSubtyping {
	
	public void voidWithoutParamz() {
		System.out.println();
	}
	
	public void voidWithParam(C b) {
		System.out.println(b);
	}
	
	public void voidWithParams(B b1, C b2) {
		System.err.println(b1);
		System.out.println(b2);
	}
	
	public void voidWithMultiArrayParam(C[][][] b) {
		System.out.println(b[0][0][0]);
	}
	
	public SuperA nonVoidWithoutParams() {
		return new A();
	}
	
	public SuperA nonVoidWithParams(B b1, B b2) {
		A a = new A();
		
		return a;
	}
	
	public SuperA[] arrayReturnWithArrayParam(C[] b) {
		return b;
	}
	
	public SuperA[][] multiArrayReturnWithArrayParam(B[] b) {
		A[][] a = new A[1][b.length];
		
		a[0] = b;
		
		return a;
	}

}
