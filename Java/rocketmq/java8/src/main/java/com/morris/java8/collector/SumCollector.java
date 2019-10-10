package com.morris.java8.collector;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 此类有问题
 */
@Deprecated
public class SumCollector implements Collector<Integer, Integer, Integer> {


    @Override
    public Supplier<Integer> supplier() {
        return () -> new Integer(0);
    }

    @Override
    public BiConsumer<Integer, Integer> accumulator() {
        return (x, y) -> x = new Integer(x + y);
    }

    @Override
    public BinaryOperator<Integer> combiner() {
        System.out.println("xxxx");
        return Integer::sum;
    }

    @Override
    public Function<Integer, Integer> finisher() {
        System.out.println("yyyy");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>();
    }

    public static void main(String[] args) {
        Integer collect = IntStream.rangeClosed(1, 10).boxed().collect(new SumCollector());
        System.out.println(collect);
    }

}
