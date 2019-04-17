package com.morris.pattern.visitor.impl;

public interface Visitor {

    void visitConcreteElement(ConcreteElementA a);

    void visitConcreteElement(ConcreteElementB b);

}
