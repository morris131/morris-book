package com.morris.pattern.interpreter.impl;

import java.util.HashMap;

public class NumberExpression implements Expression {

    private String key;

    public NumberExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpret(HashMap<String, Integer> ctx) {
        return ctx.get(key);
    }
}
