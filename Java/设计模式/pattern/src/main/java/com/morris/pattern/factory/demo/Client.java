package com.morris.pattern.factory.demo;

public class Client {

    public static void main(String[] args) {

        Car bmw = new BMWFactory().createCar();
        bmw.run();

        Car benz = new BenzFactory().createCar();
        benz.run();

    }

}
