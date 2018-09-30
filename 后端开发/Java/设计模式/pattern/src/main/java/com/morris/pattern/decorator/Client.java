package com.morris.pattern.decorator;

public class Client {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        MilkDecorator milkDecorator = new MilkDecorator(coffee);
        System.out.println("咖啡：" + milkDecorator.getName() + ",价格:" + milkDecorator.getPrice());
    }
}
