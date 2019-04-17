package com.morris.jvm.oom;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * VM args:-Xmx20M -XX:MaxDirectMemorySize=10M
 *
 */
@SuppressWarnings("restriction")
public class DirectMemoryOOM {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field field =Unsafe.class.getDeclaredFields()[0];
		field.setAccessible(true);
		
		Unsafe unsafe = (Unsafe) field.get(null);
		
		while(true) {
			unsafe.allocateMemory(1024*1024);
		}
	}
}
