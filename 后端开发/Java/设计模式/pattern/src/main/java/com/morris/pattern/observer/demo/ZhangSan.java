package com.morris.pattern.observer.demo;

public class ZhangSan implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("张三收到了消息：" + msg);
    }
}
