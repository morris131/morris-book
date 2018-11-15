package com.morris.jvm.classloader;

public class BreakClassLoader1 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader extClassLoader = BreakClassLoader1.class.getClassLoader().getParent();
        MyClassLoader myClassLoader = new MyClassLoader(extClassLoader);
        Class<?> clazz = myClassLoader.loadClass("com.morris.jvm.classloader.HelloWorld");
        System.out.println(clazz.getClassLoader());
    }
}
