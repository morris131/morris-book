package com.morris.jvm.load;

public class BreakClassLoader2 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader(null);
        Class<?> clazz = myClassLoader.loadClass("com.morris.jvm.load.HelloWorld");
        System.out.println(clazz.getClassLoader());
    }
}
