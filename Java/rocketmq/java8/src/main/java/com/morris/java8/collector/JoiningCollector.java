package com.morris.java8.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class JoiningCollector implements Collector<String, StringBuilder, String> {


    private String seperator = ",";

    public JoiningCollector() {
    }

    public JoiningCollector(String seperator) {
        this.seperator = seperator;
    }

    @Override
    public Supplier<StringBuilder> supplier() {
        return StringBuilder::new;
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return (sb, str) -> sb.append(str).append(seperator);
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return StringBuilder::append;
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        return c -> {
            String ret = c.toString();
            if (ret.endsWith(seperator)) {
                return ret.substring(0, ret.length() - 1);
            }
            return ret;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>();
    }

    public static void main(String[] args) {
        String collect = Arrays.asList("hello", "world", "java", "stream").stream().collect(new JoiningCollector("|"));
        System.out.println(collect);
    }

}
