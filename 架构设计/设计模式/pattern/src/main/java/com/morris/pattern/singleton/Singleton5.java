package com.morris.pattern.singleton;

/**
 * 饿汉式单例类 
 * 双重检查加锁，不加 volatile ，线程不安全
 * 
 * @author morris
 *
 */
public class Singleton5 {

	private static Singleton5 singleton;

	private Singleton5() {

	}

	public static Singleton5 getInstance() {

		// 第一次null检查
		if (singleton == null) {
			synchronized (Singleton5.class) { // 1
				// 第二次null检查
				if (singleton == null) { // 2
					singleton = new Singleton5();// 3
				}
			}
		}
		return singleton;
	}

}
