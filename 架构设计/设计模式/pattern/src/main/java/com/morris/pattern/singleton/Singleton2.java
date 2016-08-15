package com.morris.pattern.singleton;

/**
 * 懒汉式线程不安全单例类
 * @author morris
 *
 */
public class Singleton2 {
	
	private static Singleton2 singleton;
	
	private Singleton2() {
		
	}
	
	public static Singleton2 getInstance() {
		if(null == singleton) {
			singleton = new Singleton2();
		}
		return singleton;
	}

}
