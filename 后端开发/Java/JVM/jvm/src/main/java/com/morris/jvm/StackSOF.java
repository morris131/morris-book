package com.morris.jvm;

/**
 * VM args: -Xss128k
 * @author Morris
 *
 */
public class StackSOF {
	
	public static void main(String[] args) {
		hello();
	}

	private static void hello() {
		while (true) {
			hello();
		}
	}

}
