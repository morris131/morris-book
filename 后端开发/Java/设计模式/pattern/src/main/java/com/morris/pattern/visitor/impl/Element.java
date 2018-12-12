package com.morris.pattern.visitor.impl;

public interface Element {

    void doSomething();

    void accept(Visitor v);

}
