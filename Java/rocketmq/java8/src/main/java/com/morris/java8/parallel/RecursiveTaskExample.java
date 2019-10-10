package com.morris.java8.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class RecursiveTaskExample extends RecursiveTask<Integer> {

    private int[] data;

    private int start;

    private int end;

    public static final int LIMIT = 3;

    public RecursiveTaskExample(int[] data) {
        this.data = data;
        this.start = 0;
        this.end = data.length;
    }

    public RecursiveTaskExample(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        System.out.println(Thread.currentThread().getName());

        if(end - start <= LIMIT) {
            int result = 0;
            for(int i = start; i < end; i++) {
                result += data[i];
            }
            return result;
        }

        int middle = (end - start) / 2;

        RecursiveTaskExample left = new RecursiveTaskExample(data, start, start + middle);
        left.fork();

        // 使用main线程
        RecursiveTaskExample right = new RecursiveTaskExample(data, start + middle, end);
        Integer rightResult = right.compute();

        // 也可另起一个线程
        // RecursiveTaskExample2 right = new RecursiveTaskExample2(data, start + middle, end);
        // right.fork();
        // Integer rightResult = right.join();

        Integer leftResult = left.join();

        return rightResult + leftResult;
    }

    public static void main(String[] args) {

        int[] data = IntStream.rangeClosed(1, 13).toArray();

        ForkJoinPool pool = new ForkJoinPool();
        Integer result = pool.invoke(new RecursiveTaskExample(data));
        System.out.println(result);

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
