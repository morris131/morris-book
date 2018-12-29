package com.morris.pattern.simplefactory.demo;

public class Client {

    public static void main(String[] args) {
        Car bmw = CarFactory.createCar("bmw");
        bmw.run();

        Car benz = CarFactory.createCar("benz");
        benz.run();

    }

}
