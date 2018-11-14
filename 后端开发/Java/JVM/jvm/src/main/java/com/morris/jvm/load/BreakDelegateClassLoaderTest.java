package com.morris.jvm.load;

public class BreakDelegateClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        BreakDelegateClassLoader myClassLoader = new BreakDelegateClassLoader();
        Class<?> clazz = myClassLoader.loadClass("com.morris.jvm.load.HelloWorld");
        System.out.println(clazz.getClassLoader());
    }
}
