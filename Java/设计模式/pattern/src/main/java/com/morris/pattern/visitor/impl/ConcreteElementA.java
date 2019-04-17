package com.morris.pattern.visitor.impl;

public class ConcreteElementA implements Element {
    @Override
    public void doSomething() {
        System.out.println("ConcreteElementA doSomething");
    }

    @Override
    public void accept(Visitor v) {
        v.visitConcreteElement(this);
    }
}
