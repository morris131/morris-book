package com.morris.jvm.load;

public class ArrayLoadTest {

    public static void main(String[] args) {
        A[] arrays = new A[10]; // // 只不过是在堆内存开辟了4byte*10的空间
    }

    public static class A {
        static {
            System.out.println("class A init.");
        }
    }
}
