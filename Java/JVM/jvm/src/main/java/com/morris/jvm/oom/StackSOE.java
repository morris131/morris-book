package com.morris.jvm.oom;

public class StackSOE {

	private static int index = 1;
	
	private static void test() {
		index++;
		test();
	}
	
	public static void main(String[] args) {
		try {
			test();
		}catch (Throwable e){
			System.out.println("Stack deep : "+index);
			e.printStackTrace();

		}
	}

}
