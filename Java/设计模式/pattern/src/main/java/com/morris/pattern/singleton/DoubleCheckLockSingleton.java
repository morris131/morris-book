package com.morris.pattern.singleton;

public class DoubleCheckLockSingleton {

	private static DoubleCheckLockSingleton singleton;

	private DoubleCheckLockSingleton() {
	}

	public static DoubleCheckLockSingleton getSingleton() {
		if (singleton == null) { // #1
			synchronized (DoubleCheckLockSingleton.class) { // #2
				if (singleton == null) { // #3
					singleton = new DoubleCheckLockSingleton(); // #4
				}
			}
		}
		return singleton;
	}

}
