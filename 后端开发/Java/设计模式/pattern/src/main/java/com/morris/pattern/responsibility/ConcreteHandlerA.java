package com.morris.pattern.responsibility;

public class ConcreteHandlerA extends  Handler {
    @Override
    public void handleRequest(String request) {
        if ("A".equals(request)) {
            System.out.println("A handle request");
        } else {
            this.successor.handleRequest(request);  //转发请求
        }
    }
}
