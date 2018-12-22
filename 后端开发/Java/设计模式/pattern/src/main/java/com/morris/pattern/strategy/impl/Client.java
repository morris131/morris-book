package com.morris.pattern.strategy.impl;

public class Client {

    public static void main(String[] args) {

        Context context = new Context(new ConcreteAStrategy());
        context.algorithm();

        context.setStrategy(new ConcreteBStrategy());
        context.algorithm();

    }
}
