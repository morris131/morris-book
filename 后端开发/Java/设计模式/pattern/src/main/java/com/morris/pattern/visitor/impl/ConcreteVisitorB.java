package com.morris.pattern.visitor.impl;

public class ConcreteVisitorB implements Visitor {
    @Override
    public void visitConcreteElement(ConcreteElementA a) {
        System.out.println("ConcreteVisitorB visit ConcreteElementA begin...");
        a.doSomething();
        System.out.println("ConcreteVisitorB visit ConcreteElementA end...");
    }

    @Override
    public void visitConcreteElement(ConcreteElementB b) {
        System.out.println("ConcreteVisitorB visit ConcreteElementB begin...");
        b.doSomething();
        System.out.println("ConcreteVisitorB visit ConcreteElementB end...");
    }
}
