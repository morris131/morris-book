package com.morris.pattern.decorator;

public class MochaDecorator extends Decorator {

    public MochaDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getName() {
        return "摩卡" + coffee.getName();
    }

    @Override
    public Integer getPrice() {
        return coffee.getPrice() + 6;
    }
}
