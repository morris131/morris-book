package com.morris.pattern.singleton;

public class LazySingleton {

    private static LazySingleton singleton;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (null == singleton) { // #1
            singleton = new LazySingleton(); // #2
        }
        return singleton;
    }

}
