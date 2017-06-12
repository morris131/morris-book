package com.morris.pattern.singleton;

/**
 * 
 * 饿汉式单例，类加载时初始化单例对象
 *
 * @author morris	 
 *
 */
public class HungerSingleton {

	private static HungerSingleton singleton = new HungerSingleton();

	private HungerSingleton() {

	}

	public static HungerSingleton getInstance() {

		return singleton;
	}

}
