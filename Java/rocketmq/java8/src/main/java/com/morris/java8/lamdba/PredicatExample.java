package com.morris.java8.lamdba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicatExample {

    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1, 3, 5, 4, 8, 9);

        List<Integer> result = filterEvenNumber(integerList, i -> i % 2 == 0);

        System.out.println(result);

    }

    public static List<Integer> filterEvenNumber(List<Integer> integerList, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : integerList) {
            if(predicate.test(integer)) {
                result.add(integer);
            }
        }
        return result;
    }

}
