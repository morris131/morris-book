package com.morris.jvm.classloader;

public class BreakDelegateClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        BreakDelegateClassLoader myClassLoader = new BreakDelegateClassLoader();
        Class<?> clazz = myClassLoader.loadClass("com.morris.jvm.classloader.HelloWorld");
        System.out.println(clazz.getClassLoader());
    }
}
