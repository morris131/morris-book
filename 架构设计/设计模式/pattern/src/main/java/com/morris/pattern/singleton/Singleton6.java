package com.morris.pattern.singleton;

/**
 * 饿汉式单例类 
 * 双重检查加锁，线程安全
 * 
 * @author morris
 *
 */
public class Singleton6 {

	private static volatile Singleton6 singleton;

	private Singleton6() {

	}

	public static Singleton6 getInstance() {

		// 第一次null检查
		if (singleton == null) {
			synchronized (Singleton6.class) { // 1
				// 第二次null检查
				if (singleton == null) { // 2
					singleton = new Singleton6();// 3
				}
			}
		}
		return singleton;
	}

}
