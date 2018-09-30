package com.morris.pattern.composite;

public abstract class Company {
    protected String name;//姓名

    abstract void add(Company company);

    abstract void getInfo();
}
