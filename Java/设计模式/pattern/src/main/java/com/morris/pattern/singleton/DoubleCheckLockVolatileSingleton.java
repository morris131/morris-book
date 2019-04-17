package com.morris.pattern.singleton;

public class DoubleCheckLockVolatileSingleton {

	private static volatile DoubleCheckLockVolatileSingleton singleton;

	private DoubleCheckLockVolatileSingleton() {
	}

	public static DoubleCheckLockVolatileSingleton getSingleton() {
		if (singleton == null) { // #1
			synchronized (DoubleCheckLockVolatileSingleton.class) { // #2
				if (singleton == null) { // #3
					singleton = new DoubleCheckLockVolatileSingleton(); // #4
				}
			}
		}
		return singleton;
	}

}
