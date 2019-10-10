package com.morris.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SquareStreamExample {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);

        list1.stream().flatMap(i-> list2.stream().map(j-> new int[]{i,j})).map(x->x[0] + "," + x[1]).forEach(System.out::println);

    }
}
