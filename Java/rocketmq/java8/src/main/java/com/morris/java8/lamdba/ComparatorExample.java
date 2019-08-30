package com.morris.java8.lamdba;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
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
        List<Computer> list3 = new ArrayList<>();
        list3.add(new Computer("dell", 3000, 4));
        list3.add(new Computer("dell", 4000, 5));
        list3.add(new Computer("lenovo", 3000, 3));
        list3.add(new Computer("lenovo", 4000, 4));
        list3.add(new Computer("hp", 5000, 6));
        System.out.println("before sort: " + list3);
        list3.sort(Comparator.comparingDouble(Computer :: getPrice));
        System.out.println("after sort: " + list3);

        System.out.println("----------");

        // 对对象的多个属性排序
        List<Computer> list4 = new ArrayList<>();
        list4.add(new Computer("dell", 3000, 4));
        list4.add(new Computer("dell", 4000, 5));
        list4.add(new Computer("lenovo", 3000, 3));
        list4.add(new Computer("lenovo", 4000, 4));
        list4.add(new Computer("hp", 5000, 6));
        System.out.println("before sort: " + list4);
        // 先以速度降序，后以价格升序
        list4.sort(Comparator.comparingInt(Computer::getSpeed).reversed().thenComparingDouble(Computer :: getPrice));
        System.out.println("after sort: " + list4);

    }
}
