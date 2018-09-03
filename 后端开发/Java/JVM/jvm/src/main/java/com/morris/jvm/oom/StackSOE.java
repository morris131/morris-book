package com.morris.jvm.oom;

public class StackSOE {
	
	private static void test() {
		test();
	}
	
	public static void main(String[] args) {
		test();
	}

}
