package com.morris.pattern.flyweight.impl;

public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.getFlyweight("a");
        fly.operation("First Call");

        fly = factory.getFlyweight("b");
        fly.operation("Second Call");

        fly = factory.getFlyweight("c");
        fly.operation("Third Call");
    }

}
