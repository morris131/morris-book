package com.morris.jvm.classloader;

public class BreakClassLoader2 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader(null);// 设置父类加载器为null
        Class<?> clazz = myClassLoader.loadClass("com.morris.jvm.classloader.HelloWorld");
        System.out.println(clazz.getClassLoader());
    }
}
