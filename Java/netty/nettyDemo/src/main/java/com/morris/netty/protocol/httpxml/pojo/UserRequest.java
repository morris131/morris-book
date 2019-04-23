package com.morris.netty.protocol.httpxml.pojo;

import java.io.Serializable;

public class UserRequest implements Serializable {

    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
