package com.morris.pattern.strategy.impl;

public class ConcreteAStrategy implements Strategy {
    @Override
    public void algorithm() {
        System.out.println("ConcreteAStrategy algorithm");
    }
}
