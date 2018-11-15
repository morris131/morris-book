package com.morris.jvm.classloader;

import java.util.Random;

public class ConstantTest {

    public static void main(String[] args) {
        System.out.println(A.MAX);
        System.out.println(A.RANDOM);
    }

    public static class A {
        public static final int MAX = 10;  // 引用类的静态常量不会导致类的初始化

        public static final int RANDOM = new Random().nextInt(); // 只有在初始化后才能得到具体值，会导致了类的初始化

        static {
            System.out.println("class A init.");
        }
    }
}
