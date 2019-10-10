package com.morris.java8.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class RecursiveActionExample extends RecursiveAction {

    private int start;

    private int end;

    private int[] data;

    public static final int LIMIT = 5;

    public RecursiveActionExample(int[] data) {
        this.data = data;
        this.start = 0;
        this.end = data.length;
    }

    public RecursiveActionExample(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {

        if((end - start) < LIMIT) {
            for(int i = start; i < end; i++) {
                System.out.println(data[i]);
            }
            return;
        }

        int middle = (end - start) / 2;

        RecursiveActionExample leftRecursiveActionExample = new RecursiveActionExample(start, start + middle, data);
        leftRecursiveActionExample.fork();

        RecursiveActionExample rightRecursiveActionExample = new RecursiveActionExample(start + middle, end, data);
        rightRecursiveActionExample.compute();

        leftRecursiveActionExample.join();
    }

    public static void main(String[] args) {
        int[] ints = IntStream.rangeClosed(1, 10).toArray();
        RecursiveActionExample recursiveActionExample = new RecursiveActionExample(ints);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(recursiveActionExample);

    }

}
