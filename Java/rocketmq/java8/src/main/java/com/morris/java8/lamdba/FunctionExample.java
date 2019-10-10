package com.morris.java8.lamdba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("java", "hello", "world", "function");
        List<Integer> result = getLength(stringList, String::length);
        System.out.println(result);
    }

    public static List<Integer> getLength(List<String> stringList, Function<String, Integer> function) {
        List<Integer> integerList = new ArrayList<>();
        for (String s : stringList) {
            integerList.add(function.apply(s));
        }
        return integerList;
    }
}
