package com.morris.spring.cycle;

public class B {

	private C c;

	B() {

	}

	B(C c) {
		this.c = c;
	}

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}

}
