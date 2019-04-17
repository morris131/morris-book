package com.morris.pattern.iterator.impl;

import java.util.Vector;

public class ConcreteIterator<T> implements Iterator<T> {

    private Vector<T> vector = new Vector<>();

    private int cursor = 0;

    public ConcreteIterator(Vector<T> vector) {
        this.vector = vector;
    }


    @Override
    public T next() {
        return vector.get(cursor++);
    }

    @Override
    public boolean hasNext() {
        return !(cursor == vector.size());
    }


}
