package com.morris.pattern.simplefactory.demo;

public class Benz implements Car {
    @Override
    public void run() {
        System.out.println("This is a Benz.");
    }
}
