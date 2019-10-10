package com.morris.java8.defaultmethod;

public class MultiExtendExample2 {

    public static void main(String[] args) {
        new D().hello();
    }

    public interface A {
        void hello();
    }
    public interface B extends A {
        @Override
        default void hello() {
            System.out.println("Hello from B");
        }
    }
    public interface C extends A {
        @Override
        default void hello() {
            System.out.println("Hello from C");
        }
    }
    public static class D implements B, C {
        @Override
        public void hello() {
            System.out.println("Hello from D");
        }
    }
}
