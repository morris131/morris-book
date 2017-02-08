package com.morris.pattern.singleton;

/**
 * 
 * 懒汉式线程不安全单例类
 * 
 * @author morris
 *
 */
public class LazySingleton {

	private static LazySingleton singleton;

	private LazySingleton() {

	}

	public static LazySingleton getInstance() {

		if (null == singleton) {
			singleton = new LazySingleton();
		}
		return singleton;
	}
}
