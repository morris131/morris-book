package com.morris.java8.defaultmethod;

public class MultiExtendExample1 {

    public static void main(String[] args) {
        new C().hello();
    }

    public interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }
    public interface B extends A {
        @Override
        default void hello() {
            System.out.println("Hello from B");
        }
    }
    public static class C implements B, A {
    }
}
