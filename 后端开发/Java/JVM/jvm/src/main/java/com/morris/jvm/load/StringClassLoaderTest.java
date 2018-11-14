package com.morris.jvm.load;

public class StringClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        BreakDelegateClassLoader myClassLoader = new BreakDelegateClassLoader();
        Class<?> clazz = myClassLoader.loadClass("java.lang.String");
        System.out.println(clazz.getClassLoader());
    }
}
