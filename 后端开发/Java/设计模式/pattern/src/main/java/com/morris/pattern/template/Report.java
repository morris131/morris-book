package com.morris.pattern.template;

public abstract class Report {

    public void print() {
        printTitle();
        printBody();
        printTail();
    }

    protected boolean isPrintTail() {
        return true;
    }

    protected abstract void printBody();

    protected abstract void printTail();

    protected abstract void printTitle();

}
