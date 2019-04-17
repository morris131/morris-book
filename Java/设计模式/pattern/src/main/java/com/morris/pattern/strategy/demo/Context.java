package com.morris.pattern.strategy.demo;

public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int x, int y) {
        return strategy.calculate(x, y);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
