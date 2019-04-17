package com.morris.pattern.visitor.impl;

public class ConcreteElementB implements Element {

    @Override
    public void doSomething() {
        System.out.println("ConcreteElementB doSomething");
    }

    @Override
    public void accept(Visitor v) {
        v.visitConcreteElement(this);
    }
}
