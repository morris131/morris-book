package com.morris.jvm.classloader;

public class NameSpaceTest3 {

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = new MyClassLoader();
        ClassLoader classLoader2 = new MyClassLoader();

        Class<?> aClass = classLoader.loadClass("com.morris.jvm.classloader.HelloWorld");
        Class<?> bClass = classLoader2.loadClass("com.morris.jvm.classloader.HelloWorld");

        System.out.println(aClass == bClass); // false
    }
}
