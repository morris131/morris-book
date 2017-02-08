package com.morris.pattern.singleton;

/**
 * 静态内部类，单例，Singleton类被装载了，instance不一定被初始化，达到懒加载。
 *
 * @author morris
 *
 */
public class StaticInnerClassSingleton {

	private static class SingletonHolder {

		private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
	}

	private StaticInnerClassSingleton() {
	}

	public static final StaticInnerClassSingleton getInstance() {

		return SingletonHolder.INSTANCE;
	}
}
