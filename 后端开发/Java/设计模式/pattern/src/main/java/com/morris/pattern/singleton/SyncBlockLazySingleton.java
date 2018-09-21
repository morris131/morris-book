package com.morris.pattern.singleton;

public class SyncBlockLazySingleton {

	private static SyncBlockLazySingleton singleton;

	private SyncBlockLazySingleton() {
	}

	public static SyncBlockLazySingleton getInstance() {
		if (null == singleton) {
			synchronized (SyncBlockLazySingleton.class) {
				singleton = new SyncBlockLazySingleton();
			}
		}
		return singleton;
	}

}
