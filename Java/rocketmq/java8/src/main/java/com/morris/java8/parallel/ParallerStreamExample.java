package com.morris.java8.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallerStreamExample {

    public static void main(String[] args) {
        long n = 100_000_000;
        System.out.println("normal:" + recordTime(ParallerStreamExample::normal, n) + " MS");
        System.out.println("iterator:" + recordTime(ParallerStreamExample::iterator, n) + " MS");
        // 太耗时，暂时注释
        // System.out.println("iteratorParallel:" + recordTime(ParallerStreamExample::iteratorParallel, n) + " MS");
        System.out.println("longStream:" + recordTime(ParallerStreamExample::longStream, n) + " MS");
        System.out.println("longStreamParallel:" + recordTime(ParallerStreamExample::longStreamParallel, n) + " MS");
    }

    public static long recordTime(Function<Long, Long> function, long n) {
        long lowestCostTime = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {

            long startTime = System.currentTimeMillis();
            function.apply(n);
            long costTime = System.currentTimeMillis() - startTime;

            if(costTime < lowestCostTime) {
                lowestCostTime = costTime;
            }
        }

        return lowestCostTime;
    }

    public static long normal(long n) {
        long result = 0;
        for(long i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long iterator(long n) {
        return Stream.iterate(1L, t -> t + 1).limit(n).reduce(0L, Long::sum);
    }

    public static long iteratorParallel(long n) {
        return Stream.iterate(1L, t -> t + 1).parallel().limit(n).reduce(0L, Long::sum);
    }

    public static long longStream(long n) {
        return LongStream.rangeClosed(1, n).sum();
    }

    public static long longStreamParallel(long n) {
        return LongStream.rangeClosed(1, n).parallel().sum();
    }
}
