package com.morris.pattern.simplefactory.demo;

public class CarFactory {

    public static Car createCar(String carType) {
        if("bmw".equals(carType)) {
            return new BMW();
        } else if("benz".equals(carType)) {
            return new Benz();
        }
        return null;
    }
}
