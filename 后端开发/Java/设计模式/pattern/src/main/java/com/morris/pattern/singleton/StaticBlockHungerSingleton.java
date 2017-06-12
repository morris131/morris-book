package com.morris.pattern.singleton;

/**
 * 
 * 饿汉式单例，静态模块
 *
 * @author morris
 *
 */
public class StaticBlockHungerSingleton {

	private static StaticBlockHungerSingleton singleton = null;
	static {
		singleton = new StaticBlockHungerSingleton();
	}

	private StaticBlockHungerSingleton() {
	}

	public static StaticBlockHungerSingleton getInstance() {

		return singleton;
	}

}
