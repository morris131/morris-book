package com.morris.pattern.simplefactory.demo.v2;

import com.morris.pattern.simplefactory.demo.Car;

public class CarFactory {

    public static Car createCar(String className) {

        try {
            Class<?> clazz = Class.forName(className);
            return (Car)clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
