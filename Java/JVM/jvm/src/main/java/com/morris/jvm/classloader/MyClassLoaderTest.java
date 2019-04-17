package com.morris.jvm.classloader;

public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.loadClass("com.morris.jvm.classloader.HelloWorld");
        System.out.println(clazz.getClassLoader());
    }
}
