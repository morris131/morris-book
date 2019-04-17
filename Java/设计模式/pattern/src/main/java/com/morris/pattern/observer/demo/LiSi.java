package com.morris.pattern.observer.demo;

public class LiSi implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("李四收到了消息：" + msg);
    }
}
