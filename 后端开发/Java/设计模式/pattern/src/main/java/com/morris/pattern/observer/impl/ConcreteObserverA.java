package com.morris.pattern.observer.impl;

public class ConcreteObserverA implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("ConcreteObserverA receive message: " + msg);
    }
}
