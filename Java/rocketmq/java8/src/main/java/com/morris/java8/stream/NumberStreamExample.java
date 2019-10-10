package com.morris.java8.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberStreamExample {

    public static void main(String[] args) {
        // 对象流转数值流
        IntStream intStream = Arrays.asList(1, 3, 4, 6, 9).stream().mapToInt(Integer::intValue);

        // 数值流转对象流
        Stream<Integer> boxed = intStream.boxed();

        // 数值范围
        IntStream.range(1, 5).forEach(System.out::print); // 不包含结束值
        System.out.println();
        IntStream.rangeClosed(1, 5).forEach(System.out::print); // 包含结束值
        System.out.println();

        // 数值流特殊函数
        // min
        IntStream.rangeClosed(1, 5).min().ifPresent(System.out::println);

        // max
        IntStream.rangeClosed(1, 5).max().ifPresent(System.out::println);

        // sum
        System.out.println(IntStream.rangeClosed(1, 5).sum());
    }

}
