package com.morris.java8.collector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 此类有问题
 */
@Deprecated
public class JoiningCollector2 implements Collector<String, String, String> {


    private String seperator = ",";

    public JoiningCollector2() {
    }

    public JoiningCollector2(String seperator) {
        this.seperator = seperator;
    }

    @Override
    public Supplier<String> supplier() {
        return String::new;
    }

    @Override
    public BiConsumer<String, String> accumulator() {
        return (sb, str) -> {
            sb = sb + str + seperator;
            System.out.println(sb);
        };
    }

    @Override
    public BinaryOperator<String> combiner() {
        return (sb, str) -> {
            System.out.println(sb+"xx");
            System.out.println(str+"yyy");
            return  sb = sb + str;
        };
    }

    @Override
    public Function<String, String> finisher() {
        return c -> {
            if (c.endsWith(seperator)) {
                return c.substring(0, c.length() - 1);
            }
            return c;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>();
    }

    public static void main(String[] args) {
        String collect = Arrays.asList("hello", "world", "java", "stream").stream().collect(new JoiningCollector2("|"));
        System.out.println(collect);
    }

}
