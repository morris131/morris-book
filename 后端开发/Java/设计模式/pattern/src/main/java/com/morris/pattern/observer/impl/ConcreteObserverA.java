package com.morris.pattern.observer.impl;

public class ConcreteObserverA implements Observer {
    @Override
    public void update() {
        System.out.println("ConcreteObserverA receive message");
    }
}
