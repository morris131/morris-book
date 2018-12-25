package com.morris.pattern.decorator.demo;

public class SugarDecorator extends Decorator {

    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getName() {
        return "糖" + coffee.getName();
    }

    @Override
    public Integer getPrice() {
        return coffee.getPrice() + 3;
    }
}
