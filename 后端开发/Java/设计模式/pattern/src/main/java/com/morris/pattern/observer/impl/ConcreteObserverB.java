package com.morris.pattern.observer.impl;

public class ConcreteObserverB implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("ConcreteObserverB receive message:" + msg);
    }
}
