package com.morris.pattern.responsibility;

public class Client {

    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();

        handlerA.setHandler(handlerB);

        handlerA.handleRequest("B");

    }
}
