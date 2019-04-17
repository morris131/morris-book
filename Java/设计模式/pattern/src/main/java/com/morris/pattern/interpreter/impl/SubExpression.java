package com.morris.pattern.interpreter.impl;

import java.util.HashMap;

public class SubExpression extends SymbolExpression {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret(HashMap<String, Integer> ctx) {
        return ctx.get(left.interpret(ctx)) - ctx.get(right.interpret(ctx));
    }
}
