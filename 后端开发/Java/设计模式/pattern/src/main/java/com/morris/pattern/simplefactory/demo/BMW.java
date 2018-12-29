package com.morris.pattern.simplefactory.demo;

public class BMW implements Car {
    @Override
    public void run() {
        System.out.println("This is a BMW.");
    }
}
