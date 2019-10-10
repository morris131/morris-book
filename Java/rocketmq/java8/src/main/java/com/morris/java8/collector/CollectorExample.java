package com.morris.java8.collector;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorExample {

    public static void main(String[] args) {

        List<Dish> menu = Dish.createList();

        Optional.ofNullable(menu.stream().collect(averagingInt(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(averagingDouble(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(averagingLong(Dish::getCalories))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(collectingAndThen(toList(), Collections::unmodifiableList))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().map(Dish::getName).collect(collectingAndThen(joining(","), t->"menu:"+t))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().filter(Dish::isVegetarian).collect(counting())).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().filter(Dish::isVegetarian).count()).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(groupingBy(Dish::getType))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(groupingBy(Dish::getType, averagingInt(Dish::getCalories)))).ifPresent(System.out::println);

        TreeMap<Dish.Type, Double> collect = menu.stream().collect(groupingBy(Dish::getType, TreeMap::new, averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);


        Optional.ofNullable(menu.stream().collect(groupingByConcurrent(Dish::getType))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(groupingByConcurrent(Dish::getType, averagingInt(Dish::getCalories)))).ifPresent(System.out::println);

        ConcurrentSkipListMap<Dish.Type, Double> collect2 = menu.stream().collect(groupingBy(Dish::getType, ConcurrentSkipListMap::new, averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect2).ifPresent(System.out::println);
        Optional.ofNullable(collect2.getClass()).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining())).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining(","))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "[", "]"))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(mapping(Dish::getName, joining(",")))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories)))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(minBy(Comparator.comparingInt(Dish::getCalories)))).ifPresent(System.out::println);


        Optional.ofNullable(menu.stream().collect(partitioningBy(Dish::isVegetarian))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(partitioningBy(Dish::isVegetarian, averagingInt(Dish::getCalories)))).ifPresent(System.out::println);


        menu.stream().collect(mapping(Dish::getCalories, reducing((x,y)->x+y))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(mapping(Dish::getCalories, reducing(0, Integer::sum)))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(summarizingInt(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(summarizingDouble(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(summarizingLong(Dish::getCalories))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(summingInt(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(summingDouble(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(summingLong(Dish::getCalories))).ifPresent(System.out::println);

    }
}
