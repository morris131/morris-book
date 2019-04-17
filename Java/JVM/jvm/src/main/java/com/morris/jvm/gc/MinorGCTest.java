package com.morris.jvm.gc;

/**
 * VM args:-verbose:gc -Xms20m -Xmx20m -Xmn10m
 *
 */
public class MinorGCTest {
	
	public static int _1MB = 1024 * 1024;
	
	public static void main(String[] args) {
		
		byte[] a1 = new byte[2 * _1MB];
		//byte[] a2 = new byte[2 * _1MB];
		//byte[] a3 = new byte[2 * _1MB];
		//byte[] a4 = new byte[4 * _1MB];
		//byte[] a5 = new byte[2 * _1MB];
		//byte[] a6k = new byte[2 * _1MB];
		
		//System.gc();
		
	}

}
