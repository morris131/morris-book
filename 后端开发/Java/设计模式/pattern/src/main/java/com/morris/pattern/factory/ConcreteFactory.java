package com.morris.pattern.factory;

public class ConcreteFactory implements Factory {

    @Override
    public Product createProduct() {
        return new ConcreteProduct();
    }

}
