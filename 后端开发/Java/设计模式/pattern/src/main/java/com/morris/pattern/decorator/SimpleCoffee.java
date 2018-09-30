package com.morris.pattern.decorator;

public class SimpleCoffee extends Coffee {
    @Override
    public String getName() {
        return "咖啡";
    }

    @Override
    public Integer getPrice() {
        return 20;
    }
}
