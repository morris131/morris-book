package com.morris.pattern.simplefactory.demo.v2;

import com.morris.pattern.simplefactory.demo.Car;
import com.morris.pattern.simplefactory.demo.CarFactory;

public class Client {

    public static void main(String[] args) {
        Car bmw = CarFactory.createCar("bmw");
        bmw.run();

        Car benz = CarFactory.createCar("benz");
        benz.run();

    }

}
