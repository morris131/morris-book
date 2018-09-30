package com.morris.pattern.composite;

public class Employee extends Company {

    public Employee(String name) {
        this.name = name;
    }

    @Override
    void add(Company company) {
        throw new UnsupportedOperationException();
    }

    @Override
    void getInfo() {
        System.out.println("Employee{" + "name='" + name + '}');
    }
}
