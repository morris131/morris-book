package com.morris.dubbo.xml;

public class DemoServiceImpl implements IDemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
