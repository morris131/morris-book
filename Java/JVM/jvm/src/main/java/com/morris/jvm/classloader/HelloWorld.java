package com.morris.jvm.classloader;

public class HelloWorld {
    static {
        System.out.println("HelloWorld Class is initialized.");
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
