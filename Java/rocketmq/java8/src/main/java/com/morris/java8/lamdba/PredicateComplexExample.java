package com.morris.java8.lamdba;

import com.morris.java8.collector.Dish;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateComplexExample {

    public static void main(String[] args) {

        List<Dish> dishList = Dish.createList();

        // 与-and
        // 找出卡路里>300的蔬菜
        Predicate<Dish> vegetablePredicate = Dish::isVegetarian;
        Predicate<Dish> moreThan300Predicate = d -> d.getCalories() >= 300;
        List<Dish> filter = filter(dishList, vegetablePredicate.and(moreThan300Predicate));
        System.out.println(filter);

        // 非-negate
        // 找出荤菜
        List<Dish> filter1 = filter(dishList, vegetablePredicate.negate());
        System.out.println(filter1);

        // 或-or
        // 找出卡路里大于300或者荤菜
        List<Dish> filter2 = filter(dishList, vegetablePredicate.or(moreThan300Predicate));
        System.out.println(filter2);

    }

    public static List<Dish> filter(List<Dish> dishList, Predicate<Dish> predicate) {
        List<Dish> result = new ArrayList<>();
        for (Dish dish : dishList) {
            if(predicate.test(dish)) {
                result.add(dish);
            }
        }
        return result;
    }
}
