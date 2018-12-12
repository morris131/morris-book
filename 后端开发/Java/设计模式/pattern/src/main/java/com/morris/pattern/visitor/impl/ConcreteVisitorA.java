package com.morris.pattern.visitor.impl;

public class ConcreteVisitorA implements Visitor {
    @Override
    public void visitConcreteElement(ConcreteElementA a) {
        System.out.println("ConcreteVisitorA visit ConcreteElementA begin...");
        a.doSomething();
        System.out.println("ConcreteVisitorA visit ConcreteElementA end...");
    }

    @Override
    public void visitConcreteElement(ConcreteElementB b) {
        System.out.println("ConcreteVisitorA visit ConcreteElementB begin...");
        b.doSomething();
        System.out.println("ConcreteVisitorA visit ConcreteElementB end...");
    }
}
