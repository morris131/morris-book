package com.morris.pattern.singleton;

/**
 * 懒汉式单例类，同步方法，线程安全，效率很低，99%情况下不需要同步。
 * 
 * @author morris
 *
 */
public class SyncMethodLazySingleton {

	private static SyncMethodLazySingleton singleton;

	private SyncMethodLazySingleton() {

	}

	public static synchronized SyncMethodLazySingleton getInstance() {

		if (null == singleton) {
			singleton = new SyncMethodLazySingleton();
		}
		return singleton;
	}

}
