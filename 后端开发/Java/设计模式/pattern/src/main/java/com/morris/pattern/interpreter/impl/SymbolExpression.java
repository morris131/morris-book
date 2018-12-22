package com.morris.pattern.interpreter.impl;

public abstract class SymbolExpression implements Expression {

    protected Expression left;

    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
