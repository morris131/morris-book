package com.morris.pattern.strategy.demo;

public class DivideStrategy implements Strategy {

    @Override
    public int calculate(int x, int y) {
        return x / y;
    }
}
