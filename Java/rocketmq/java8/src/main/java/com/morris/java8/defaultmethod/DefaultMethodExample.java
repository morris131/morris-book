package com.morris.java8.defaultmethod;

public class DefaultMethodExample {

    public static void main(String[] args) {

        A a = () -> 10;

        System.out.println(a.isEmpty());
    }

    @FunctionalInterface
    interface A {

        int size();

        default boolean isEmpty() {
            return 0 == size();
        }
    }

}
