package com.morris.pattern.decorator.demo;

public class Client {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee= new MilkDecorator(coffee);
        coffee= new MochaDecorator(coffee);
        coffee= new SugarDecorator(coffee);
        System.out.println("咖啡：" + coffee.getName() + ",价格:" + coffee.getPrice());
    }
}
