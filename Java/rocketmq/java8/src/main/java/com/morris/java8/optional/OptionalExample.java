package com.morris.java8.optional;

import com.morris.java8.collector.Dish;

import java.util.Optional;

public class OptionalExample {

    static class Person {
        private Optional<Car> car;
        public Optional<Car> getCar() { return car; }
    }
    static class Car {
        private Optional<Insurance> insurance;
        public Optional<Insurance> getInsurance() { return insurance; }
    }
    static class Insurance {
        private String name;
        public String getName() { return name; }
    }

    public static void main(String[] args) {

        // 声明一个空的Optional
        Optional<Car> optCar1 = Optional.empty();

        Car car = new Car();

        // 依据一个非空值创建Optional，car是一个null，这段代码会立即抛出一个NullPointerException
        Optional<Car> optCar2 = Optional.of(car);

        // 可接受null的Optional
        Optional<Car> optCar3 = Optional.ofNullable(car);

        // 使用map从Optional对象中提取和转换值
        Insurance insurance = new Insurance();
        Optional<String> name = Optional.of(insurance).map(Insurance::getName);

    }

}
