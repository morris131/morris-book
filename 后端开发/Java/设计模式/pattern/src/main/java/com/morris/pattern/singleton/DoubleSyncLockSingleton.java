package com.morris.pattern.singleton;

/**
 * 
 * 双重同步锁
 *
 * @author morris 	 
 *
 */
public class DoubleSyncLockSingleton {

	private volatile static DoubleSyncLockSingleton singleton;

	private DoubleSyncLockSingleton() {
	}

	public static DoubleSyncLockSingleton getSingleton() {

		if (singleton == null) {
			synchronized (DoubleSyncLockSingleton.class) {
				if (singleton == null) {
					singleton = new DoubleSyncLockSingleton();
				}
			}
		}
		return singleton;
	}

}
