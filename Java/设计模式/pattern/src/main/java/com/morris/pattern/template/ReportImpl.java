package com.morris.pattern.template;

public class ReportImpl extends Report {

    @Override
    public void printTitle() {
        System.out.println("采用一种方式打印表头");
    }

    @Override
    public void printBody() {
        System.out.println("采用一种方式打印正文");
    }

    @Override
    public void printTail() {
        System.out.println("采用一种方式打印表尾");
    }

    @Override
    protected boolean isPrintTail() {
        return false;
    }
}
