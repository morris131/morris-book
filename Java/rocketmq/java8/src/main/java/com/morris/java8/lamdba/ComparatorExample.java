package com.morris.java8.lamdba;

import com.morris.java8.collector.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {

    public static void main(String[] args) {

        // 对整数列表升序排序
        List<Integer> list1 = Arrays.asList(1, 11, 3, 9, 7);
        System.out.println("before sort: " + list1);
        list1.sort(Comparator.naturalOrder());
        System.out.println("after natural order sort: " + list1);

        System.out.println("----------");

        // 对整数列表降序排序
        List<Integer> list2 = Arrays.asList(1, 11, 3, 9, 7);
        System.out.println("before sort: " + list2);
        list2.sort(Comparator.reverseOrder());
        System.out.println("after reverse order sort: " + list2);

        System.out.println("----------");

        // 对对象的单个属性排序
        List<Dish> list3 = Dish.createList();
        System.out.println("before sort: " + list3);
        list3.sort(Comparator.comparingInt(Dish :: getCalories));
        System.out.println("after sort: " + list3);

        System.out.println("----------");

        // 对对象的多个属性排序
        List<Dish> list4 = Dish.createList();
        System.out.println("before sort: " + list4);
        // 先以卡路里降序，后以名字升序
        list4.sort(Comparator.comparingInt(Dish::getCalories).reversed().thenComparing(Dish :: getName));
        System.out.println("after sort: " + list4);

    }
}
