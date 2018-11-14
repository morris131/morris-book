package com.morris.jvm.load;

import java.util.Arrays;

public class BootstrapClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("BootstrapClassLoader:" + String.class.getClassLoader());
        Arrays.asList(System.getProperty("sun.boot.class.path").split(";")).stream().forEach(System.out::println);
    }
}
