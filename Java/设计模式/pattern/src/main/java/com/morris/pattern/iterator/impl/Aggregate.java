package com.morris.pattern.iterator.impl;

public interface Aggregate<T> {

    boolean add(T t);

    Iterator<T> iterator();

}
