package com.morris.jvm.load;

public class NameSpaceTest2 {

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = new MyClassLoader();
        ClassLoader classLoader2 = new BreakDelegateClassLoader();

        Class<?> aClass = classLoader.loadClass("com.morris.jvm.load.HelloWorld");
        Class<?> bClass = classLoader2.loadClass("com.morris.jvm.load.HelloWorld");

        System.out.println(aClass == bClass); // false
    }
}
