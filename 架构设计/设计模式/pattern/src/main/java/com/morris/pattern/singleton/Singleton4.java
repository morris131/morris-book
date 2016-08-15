package com.morris.pattern.singleton;

/**
 * 饿汉式单例类
 * 同步方法块，线程不安全
 * @author morris
 *
 */
public class Singleton4 {

	private static Singleton4 singleton;

	private Singleton4() {

	}

	public static Singleton4 getInstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				singleton = new Singleton4();
			}
		}
		return singleton;
	}

}
