package com.morris.jvm.load;

public class NameSpaceTest1 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 获取系统类加载器
        ClassLoader classLoader = NameSpaceTest1.class.getClassLoader();

        Class<?> aClass = classLoader.loadClass("com.morris.jvm.load.HelloWorld");
        Class<?> bClass = classLoader.loadClass("com.morris.jvm.load.HelloWorld");

        System.out.println(aClass == bClass); // true
    }
}
