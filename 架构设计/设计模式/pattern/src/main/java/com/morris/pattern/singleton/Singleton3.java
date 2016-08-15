package com.morris.pattern.singleton;

/**
 * 懒汉式单例类
 * 同步方法，线程安全
 * @author morris
 *
 */
public class Singleton3 {
	
	private static Singleton3 singleton;
	
	private Singleton3() {
		
	}
	
	public static synchronized Singleton3 getInstance() {
		if(null == singleton) {
			singleton = new Singleton3();
		}
		return singleton;
	}

}
