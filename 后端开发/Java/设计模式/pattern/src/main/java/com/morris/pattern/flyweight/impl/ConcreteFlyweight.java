package com.morris.pattern.flyweight.impl;

public class ConcreteFlyweight extends Flyweight {

    /**
     * 内部状态，同一个享元对象其内部状态是一致的
     */
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    protected void operation(String extrinsicState) {
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + extrinsicState);
    }

}
