package com.morris.pattern.responsibility;

public abstract class Handler {

    protected Handler successor;

    public void setHandler(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(String request);

}
