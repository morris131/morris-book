package com.morris.spring.cycle;

public class C {

	private A a;

	C() {

	}

	C(A a) {
		this.a = a;
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

}
