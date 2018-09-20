package com.morris.pattern.singleton;

/**
 * 懒汉式单例类,线程不安全
 */
public class LazySingleton {

    private static LazySingleton singleton;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (null == singleton) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

}
