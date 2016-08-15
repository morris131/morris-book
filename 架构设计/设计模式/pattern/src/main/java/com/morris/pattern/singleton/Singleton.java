package com.morris.pattern.singleton;

/**
 * 饿汉式单例类
 * @author morris
 *
 */
public class Singleton {
	
	private static Singleton singleton = new Singleton();
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		return singleton;
	}

}
