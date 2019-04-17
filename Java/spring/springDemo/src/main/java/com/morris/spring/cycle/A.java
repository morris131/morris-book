package com.morris.spring.cycle;

public class A {

	private B b;

	public A() {
	}

	A(B b) {
		this.b = b;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

}
