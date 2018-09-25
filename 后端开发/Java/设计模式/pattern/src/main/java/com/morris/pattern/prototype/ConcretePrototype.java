package com.morris.pattern.prototype;

public class ConcretePrototype implements Cloneable{

    @Override
    protected Object clone() {

        ConcretePrototype concretePrototype = null;
        try {
            concretePrototype = (ConcretePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return concretePrototype;
    }
}
