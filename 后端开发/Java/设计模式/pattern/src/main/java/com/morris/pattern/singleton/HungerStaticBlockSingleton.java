package com.morris.pattern.singleton;

public class HungerStaticBlockSingleton {

    private static HungerStaticBlockSingleton singleton;

    static {
        singleton = new HungerStaticBlockSingleton();
    }

    private HungerStaticBlockSingleton() {
    }

    public static HungerStaticBlockSingleton getInstance() {
        return singleton;
    }

}
