package com.morris.jvm.classloader;

public class CustomerClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        CustomerClassLoader myClassLoader = new CustomerClassLoader();
        Class<?> clazz = myClassLoader.loadClass("java.lang.String");
        System.out.println(clazz.getClassLoader());

        Class<?> test = myClassLoader.loadClass("com.morris.jvm.classloader.CustomerClassLoaderTest");
        System.out.println(test.getClassLoader());
    }
}
