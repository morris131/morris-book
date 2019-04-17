package com.morris.pattern.iterator.impl;

public class Client {

    public static void main(String[] args) {

        Aggregate<String> aggregate = new ConcreteAggregate<>();
        aggregate.add("a");
        aggregate.add("b");
        aggregate.add("c");
        aggregate.add("d");

        Iterator<String> iterator = aggregate.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
