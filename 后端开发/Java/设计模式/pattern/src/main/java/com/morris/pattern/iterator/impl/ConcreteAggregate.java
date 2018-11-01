package com.morris.pattern.iterator.impl;

import java.util.Vector;

public class ConcreteAggregate<T> implements Aggregate <T> {

    private Vector<T> vector = new Vector<>();

    @Override
    public boolean add(T t) {
        return vector.add(t);
    }

    @Override
    public boolean remove(T t) {
        return vector.remove(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new ConcreteIterator<>(vector);
    }
}
