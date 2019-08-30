package com.morris.java8.lamdba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateComplexExample {

    public static void main(String[] args) {
        List<Computer> list3 = new ArrayList<>();
        list3.add(new Computer("dell", 3000, 4));
        list3.add(new Computer("dell", 4000, 5));
        list3.add(new Computer("lenovo", 3000, 3));
        list3.add(new Computer("lenovo", 4000, 4));
        list3.add(new Computer("hp", 5000, 6));

        // 找出名字为dell的
        Predicate<Computer> dellPredicate = p -> "dell".equals(p.getName());
        List<Computer> result1 = filter(list3, dellPredicate);
        System.out.println(result1);

        System.out.println("----------");

        // 找出名字为dell,价格为3000的
        Predicate<Computer> pricePredicate = p -> 3000 == p.getPrice();
        List<Computer> result2 = filter(list3, dellPredicate.and(pricePredicate));
        System.out.println(result2);

        System.out.println("----------");

        // 找出名字为dell,价格为3000或者速度为3的
        Predicate<Computer> speedPredicate = p -> 3 == p.getSpeed();
        List<Computer> result3 = filter(list3, dellPredicate.or(speedPredicate).and(pricePredicate));
        System.out.println(result3);

    }

    public static List<Computer> filter(List<Computer> computerList, Predicate<Computer> predicate) {
        List<Computer> result = new ArrayList<>();
        for (Computer computer : computerList) {
            if(predicate.test(computer)) {
                result.add(computer);
            }
        }
        return result;
    }
}
