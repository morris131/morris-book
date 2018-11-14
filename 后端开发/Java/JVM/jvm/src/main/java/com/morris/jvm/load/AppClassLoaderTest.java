package com.morris.jvm.load;

import java.util.Arrays;

public class AppClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("AppClassLoader:" +AppClassLoaderTest.class.getClassLoader());
        System.out.println("AppClassLoader parent:" +AppClassLoaderTest.class.getClassLoader().getParent());
        Arrays.asList(System.getProperty("java.class.path").split(";")).stream().forEach(System.out::println);
    }
}
