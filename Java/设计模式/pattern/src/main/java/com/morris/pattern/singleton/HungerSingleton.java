package com.morris.pattern.singleton;

public class HungerSingleton {

    private static HungerSingleton singleton = new HungerSingleton();

    private HungerSingleton() {
    }

    public static HungerSingleton getInstance() {
        return singleton;
    }

}
