package com.morris.java8.stream;

import com.morris.java8.lamdba.Computer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSimpleExample {

    public static void main(String[] args) {
        List<Computer> list3 = new ArrayList<>();
        list3.add(new Computer("dell", 3000, 4));
        list3.add(new Computer("dell", 4000, 5));
        list3.add(new Computer("lenovo", 3000, 3));
        list3.add(new Computer("lenovo", 4000, 4));
        list3.add(new Computer("hp", 5000, 6));


        List<Double> doubleList = list3.stream().filter(c -> "dell".equals(c.getName())).map(Computer::getPrice).collect(Collectors.toList());
        System.out.println(doubleList);

    }
}
