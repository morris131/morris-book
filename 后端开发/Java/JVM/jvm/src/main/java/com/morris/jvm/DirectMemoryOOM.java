package com.morris.jvm;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * vm args:-Xmx20M -XX:MaxDirectMemorySize=10M
 * @author morris
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
