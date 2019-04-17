package com.morris.pattern.observer.demo;

public class Client {

    public static void main(String[] args) {

        Observer zhangSan = new ZhangSan();
        Observer liSi = new LiSi();

        NewspaperOffice subject = new NewspaperOffice();
        subject.attach(zhangSan);
        subject.attach(liSi);

        subject.publishNews();
    }
}
