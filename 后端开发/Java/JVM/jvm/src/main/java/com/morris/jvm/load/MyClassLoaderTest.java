package com.morris.jvm.load;

public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.loadClass("com.morris.jvm.load.HelloWorld");
        System.out.println(clazz.getClassLoader());
    }
}
