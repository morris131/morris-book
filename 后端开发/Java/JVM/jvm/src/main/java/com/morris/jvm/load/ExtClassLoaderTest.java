package com.morris.jvm.load;

import java.util.Arrays;

public class ExtClassLoaderTest {
    public static void main(String[] args) {
        System.out.println("ExtClassLoader:" + com.sun.nio.zipfs.ZipDirectoryStream.class.getClassLoader());
        System.out.println("ExtClassLoader parent:" + com.sun.nio.zipfs.ZipDirectoryStream.class.getClassLoader().getParent());
        Arrays.asList(System.getProperty("java.ext.dirs").split(";")).stream().forEach(System.out :: println);
    }
}
