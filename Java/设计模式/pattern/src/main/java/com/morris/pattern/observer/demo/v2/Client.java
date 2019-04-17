package com.morris.pattern.observer.demo.v2;

import java.util.Observer;

public class Client {

    public static void main(String[] args) {

        Observer zhangSan = new ZhangSan();
        Observer liSi = new LiSi();

        NewspaperOffice subject = new NewspaperOffice();
        subject.addObserver(zhangSan);
        subject.addObserver(liSi);

        subject.publishNews();

    }
}
