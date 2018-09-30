package com.morris.pattern.decorator;

public class MilkDecorator extends Decorator {

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getName() {
        return "牛奶" + coffee.getName();
    }

    @Override
    public Integer getPrice() {
        return coffee.getPrice() + 5;
    }
}
