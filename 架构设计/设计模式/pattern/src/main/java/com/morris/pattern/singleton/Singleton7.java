package com.morris.pattern.singleton;

/**
 * 懒汉式单例类 登记式(静态内部类实现)
 * 
 * @author morris
 *
 */
public class Singleton7 {

	private static class SingletonHolder {
		private static final Singleton7 INSTANCE = new Singleton7();
	}

	private Singleton7() {
	}

	public static final Singleton7 getInstance() {
		return SingletonHolder.INSTANCE;
	}

}
