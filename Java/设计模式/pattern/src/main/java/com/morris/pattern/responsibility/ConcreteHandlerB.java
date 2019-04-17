package com.morris.pattern.responsibility;

public class ConcreteHandlerB extends  Handler {
    @Override
    public void handleRequest(String request) {
        if ("B".equals(request)) {
            System.out.println("B handle request");
        } else {
            this.successor.handleRequest(request);  //转发请求
        }
    }
}
