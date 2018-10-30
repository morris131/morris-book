package com.morris.pattern.observer.impl;

public class ConcreteObserverB implements Observer {
    @Override
    public void update() {
        System.out.println("ConcreteObserverB receive message");
    }
}
