package com.morris.pattern.singleton;

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
