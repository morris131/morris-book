package com.morris.java8.stream;


import com.morris.java8.collector.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamSimpleExample {

    public static void main(String[] args) {

        List<Dish> dishList = Dish.createList();

        // filter
        List<Dish> filter = dishList.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(filter);

        // distinct
        List<Integer> distinct = Arrays.asList(1, 5, 6, 5, 8, 1, 9, 10).stream().distinct().collect(Collectors.toList());
        System.out.println(distinct);

        // limit
        List<Integer> limit = Arrays.asList(1, 5, 6, 5, 8, 1, 9, 10).stream().distinct().limit(3).collect(Collectors.toList());
        System.out.println(limit);

        // skip
        List<Integer> skip = Arrays.asList(1, 5, 6, 5, 8, 1, 9, 10).stream().distinct().skip(2).limit(3).collect(Collectors.toList());
        System.out.println(skip);

        // map
        List<String> map = dishList.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(map);

        // flatMap
        List<String> flatMap = dishList.stream().flatMap(d -> Arrays.stream(d.getName().split(""))).distinct().collect(Collectors.toList());
        System.out.println(flatMap);

        // anyMatch
        boolean anyMatch = Arrays.asList(1, 5, 6, 5, 8, 1, 9, 10).stream().anyMatch(i -> i % 2 == 0);
        System.out.println(anyMatch);

        // allMatch
        boolean allMatch = Arrays.asList(1, 5, 6, 5, 8, 1, 9, 10).stream().allMatch(i -> i % 2 == 0);
        System.out.println(allMatch);

        // findAny
        Arrays.asList(1, 5, 6, 5, 8, 1, 9, 10).stream().findAny().ifPresent(System.out::println);

        // findFirst
        Arrays.asList(1, 5, 6, 5, 8, 1, 9, 10).stream().findFirst().ifPresent(System.out::println);

        // sum
        Optional.ofNullable(dishList.stream().map(Dish::getCalories).reduce(0, Integer::sum)).ifPresent(System.out::println);
        dishList.stream().map(Dish::getCalories).reduce(Integer::sum).ifPresent(System.out::println);

        // max
        dishList.stream().map(Dish::getCalories).reduce(Integer::max).ifPresent(System.out::println);

        // min
        dishList.stream().map(Dish::getCalories).reduce(Integer::min).ifPresent(System.out::println);


    }
}
