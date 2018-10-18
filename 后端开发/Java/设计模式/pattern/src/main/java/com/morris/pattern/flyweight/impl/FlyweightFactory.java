package com.morris.pattern.flyweight.impl;

import java.util.HashMap;

public class FlyweightFactory {

    private HashMap<String, Flyweight> pool = new HashMap<>();

    public Flyweight getFlyweight(String intrinsicState) {
        if(pool.containsKey(intrinsicState)) {
            return pool.get(intrinsicState);
        } else {
            return new ConcreteFlyweight(intrinsicState);
        }
    }
}
