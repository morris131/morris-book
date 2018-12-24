package com.morris.pattern.observer.impl;

public class ConcreteSubject extends Subject {

    public void doSomething() {
        System.out.println("call ConcreteSubject.doSomething()");
        super.notifyObservers("hello");
    }

}
