package com.morris.pattern.flyweight.impl;

public abstract class Flyweight {
    /**
     * 外部状态在使用时由外部设置，不保存在享元对象中，即使是同一个对象，在每一次调用时也可以传入不同的外部状态
     * @param extrinsicState 外部状态
     */
    protected abstract void operation(String extrinsicState);
}
