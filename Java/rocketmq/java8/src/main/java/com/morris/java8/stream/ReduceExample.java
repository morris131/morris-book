package com.morris.java8.stream;

import java.util.stream.IntStream;

public class ReduceExample {

    public static void main(String[] args) {

        Integer sum = IntStream.rangeClosed(1, 1000).boxed().reduce(0, Integer::sum, (x, y) -> {
            // 不会执行，不影响结果
            return 0;
        });
        System.out.println("sum=" + sum);
        System.out.println("-------------");

        Integer sum2 = IntStream.rangeClosed(1, 1000).boxed().parallel().reduce(0, Integer::sum, (x, y) -> {
            System.out.print("thread name: " + Thread.currentThread().getName());
            System.out.print(" x=" + x);
            System.out.println(" y=" + y);
            return x + y;
        });

        System.out.println("sum2=" + sum2);

    }
}
