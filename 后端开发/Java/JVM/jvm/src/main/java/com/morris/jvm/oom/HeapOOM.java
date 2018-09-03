package com.morris.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms 20m -Xmx 20m
 *
 */
public class HeapOOM {
	
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		while(true) {
			list.add(new Object());
		}
	}

}
