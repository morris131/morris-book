package com.morris.pattern.observer.impl;

public class Client {

    public static void main(String[] args) {

        Observer observerA = new ConcreteObserverA();
        Observer observerB = new ConcreteObserverB();

        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(observerA);
        subject.attach(observerB);

        subject.doSomething();

    }
}
