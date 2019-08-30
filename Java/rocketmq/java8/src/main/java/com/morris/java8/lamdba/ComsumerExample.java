package com.morris.java8.lamdba;

import java.util.Arrays;
import java.util.List;

public class ComsumerExample {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 3, 5, 4, 8, 9);
        integerList.forEach(i -> System.out.println(i));
    }
}
