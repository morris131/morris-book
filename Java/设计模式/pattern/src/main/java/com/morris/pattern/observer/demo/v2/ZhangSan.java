package com.morris.pattern.observer.demo.v2;

import java.util.Observable;
import java.util.Observer;

public class ZhangSan implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("张三收到了消息：" + arg);
    }
}
