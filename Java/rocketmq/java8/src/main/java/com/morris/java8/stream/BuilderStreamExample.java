package com.morris.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuilderStreamExample {

    public static void main(String[] args) {

        // 从值生成流
        Optional.ofNullable(Stream.of("hello", "morris", "world", "stream").collect(Collectors.joining(","))).ifPresent(System.out::println);

        // 从数组生成流
        Optional.ofNullable(Arrays.stream(new int[]{2, 3, 1, 4}).boxed().map(i -> String.valueOf(i)).collect(Collectors.joining(","))).ifPresent(System.out::println);

        // iterate生成流
        Optional.ofNullable(Stream.iterate(0, n -> n + 2).limit(10).map(i -> String.valueOf(i)).collect(Collectors.joining(","))).ifPresent(System.out::println);

        // generate生成流
        Optional.ofNullable(Stream.generate(Math::random).limit(5).map(d -> String.valueOf(d)).collect(Collectors.joining(","))).ifPresent(System.out::println);

        // 从文件生成流
        buildStreamFromFile();

    }

    public static void buildStreamFromFile() {
        try(Stream<String> stream = Files.lines(Paths.get("D:\\gitPrj\\morris-book\\Java\\rocketmq\\java8\\src\\main\\java\\com\\morris\\java8\\stream\\Trader.java"))) {
            stream.limit(5).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
