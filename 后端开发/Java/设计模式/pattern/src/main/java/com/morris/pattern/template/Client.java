package com.morris.pattern.template;

public class Client {

    public static void main(String[] args) {
        Report report = new ReportImpl1();
        report.print();
    }
}
