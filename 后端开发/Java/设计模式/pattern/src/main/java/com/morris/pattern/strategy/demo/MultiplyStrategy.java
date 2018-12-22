package com.morris.pattern.strategy.demo;

public class MultiplyStrategy implements Strategy {

    @Override
    public int calculate(int x, int y) {
        return x * y;
    }
}
